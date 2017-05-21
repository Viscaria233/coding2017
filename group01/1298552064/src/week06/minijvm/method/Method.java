package week06.minijvm.method;

import week06.minijvm.attr.AttributeInfo;
import week06.minijvm.attr.CodeAttr;
import week06.minijvm.clz.ClassFile;
import week06.minijvm.loader.ByteCodeIterator;

public class Method {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	private CodeAttr codeAttr;
	
	private ClassFile clzFile;
	
	public int getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(int accessFlag) {
		this.accessFlag = accessFlag;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public void setDescriptorIndex(int descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}

	public void setCodeAttr(CodeAttr codeAttr){
		this.codeAttr = codeAttr;
	}
	
	public CodeAttr getCodeAttr(){
		return this.codeAttr;
	}
	
	public Method(ClassFile clzFile, int accessFlag,int nameIndex,int descriptorIndex){
		this.clzFile = clzFile;
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}
	
	public static Method parse(ClassFile clzFile,ByteCodeIterator iterator){
		int accessFlag = iterator.nextU2ToInt();
		int nameIndex = iterator.nextU2ToInt();
		int descIndex = iterator.nextU2ToInt();
		int attributeCount = iterator.nextU2ToInt();
		
		Method method = new Method(clzFile,accessFlag,nameIndex,descIndex);
		
		for(int i=0; i < attributeCount; i++){
			int attrNameIndex = iterator.nextU2ToInt();
			String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
			
			iterator.back(2);
			
			if(AttributeInfo.CODE.equals(attrName)){
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iterator);
				method.setCodeAttr(codeAttr);
			}else{
				throw new RuntimeException("Only CODE attribute is implemented,please implement the " + attributeCount);
			}
		}
		return method;
	}
}
