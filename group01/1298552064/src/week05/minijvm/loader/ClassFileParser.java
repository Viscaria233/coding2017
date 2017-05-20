package week05.minijvm.loader;

import java.io.UnsupportedEncodingException;

import week05.minijvm.clz.AccessFlag;
import week05.minijvm.clz.ClassFile;
import week05.minijvm.clz.ClassIndex;
import week05.minijvm.constant.ClassInfo;
import week05.minijvm.constant.ConstantPool;
import week05.minijvm.constant.FieldRefInfo;
import week05.minijvm.constant.MethodRefInfo;
import week05.minijvm.constant.NameAndTypeInfo;
import week05.minijvm.constant.NullConstantInfo;
import week05.minijvm.constant.StringInfo;
import week05.minijvm.constant.UTF8Info;

public class ClassFileParser {
	public ClassFile parse(byte[] codes){
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iterator = new ByteCodeIterator(codes);
		
		String magicNumber = iterator.nextU4ToHexString();
		if(!"cafebabe".equals(magicNumber)){
			return null;
		}
		
		clzFile.setMinorVersion(iterator.nextU2ToInt());
		clzFile.setMajorVersion(iterator.nextU2ToInt());
		
		//读取常量池
		ConstantPool pool = paraseConstantPool(iterator);
		clzFile.setConstantPool(pool);
		
		//accessFlags
		AccessFlag accessFlag = new AccessFlag(iterator.nextU2ToInt());
		clzFile.setAccessFlag(accessFlag);
		
		//this_class 、 super_class
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iterator.nextU2ToInt());
		classIndex.setSuperClassIndex(iterator.nextU2ToInt());
		clzFile.setClassIndex(classIndex);
		
		//interfaces_count 、 interfaces
		
		//fields_count 、 fields
		
		//methods_count 、 methods
		
		//attributes_count 、 attributes
			
		return clzFile;
	}

	private ConstantPool paraseConstantPool(ByteCodeIterator iterator) {
		int constantPoolCount = iterator.nextU2ToInt();
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		
		for(int i = 1; i <= constantPoolCount - 1; i++){
			int tag = iterator.nextU1ToInt();
			
			if(tag == 7){
				//class Info
				ClassInfo classInfo = new ClassInfo(pool);
				
				classInfo.setUtf8Index(iterator.nextU2ToInt());
				
				pool.addConstantInfo(classInfo);
				
			} else if(tag == 1){
				// UTF-8 String
				UTF8Info utf8Info = new UTF8Info(pool);
				int len = iterator.nextU2ToInt();
				
				String value = null;
				try {
					byte[] data = iterator.getBytes(len);
					value = new String(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				utf8Info.setLength(len);
				utf8Info.setValue(value);
				
				pool.addConstantInfo(utf8Info);
				
			} else if(tag == 10){
				MethodRefInfo methodInfo = new MethodRefInfo(pool);
				
				methodInfo.setClassInfoIndex(iterator.nextU2ToInt());
				methodInfo.setNameAndTypeIndex(iterator.nextU2ToInt());
				
				pool.addConstantInfo(methodInfo);
				
			} else if(tag == 12){
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				nameAndTypeInfo.setNameIndex(iterator.nextU2ToInt());
				nameAndTypeInfo.setDescriptorIndex(iterator.nextU2ToInt());
				
				pool.addConstantInfo(nameAndTypeInfo);
				
			} else if(tag == 9){
				FieldRefInfo fieldInfo = new FieldRefInfo(pool);
				fieldInfo.setClassInfoIndex(iterator.nextU2ToInt());
				fieldInfo.setNameAndTypeIndex(iterator.nextU2ToInt());
				
				pool.addConstantInfo(fieldInfo);
			} else if(tag == 8){
				StringInfo stringInfo = new StringInfo(pool);
				
				stringInfo.setIndex(iterator.nextU2ToInt());
				
				pool.addConstantInfo(stringInfo);
			}else{
				throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
			}
		}
		
		System.out.println("Finished reading Constant pool");
		
		return pool;
	}
}
