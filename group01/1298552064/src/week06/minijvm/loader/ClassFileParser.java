package week06.minijvm.loader;

import java.io.UnsupportedEncodingException;

import week06.minijvm.clz.AccessFlag;
import week06.minijvm.clz.ClassFile;
import week06.minijvm.clz.ClassIndex;
import week06.minijvm.constant.ClassInfo;
import week06.minijvm.constant.ConstantPool;
import week06.minijvm.constant.FieldRefInfo;
import week06.minijvm.constant.MethodRefInfo;
import week06.minijvm.constant.NameAndTypeInfo;
import week06.minijvm.constant.NullConstantInfo;
import week06.minijvm.constant.StringInfo;
import week06.minijvm.constant.UTF8Info;
import week06.minijvm.field.Field;
import week06.minijvm.method.Method;



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
		ClassIndex classIndex = parseClassIndex(iterator);
		clzFile.setClassIndex(classIndex);
		
		//interfaces_count 、 interfaces
		parseInterfaces(iterator);
		
		//fields_count 、 fields
		parseFields(clzFile,iterator);
		
		//methods_count 、 methods
		parseMethods(clzFile,iterator);
		
		//attributes_count 、 attributes
		parseAttributes(clzFile,iterator);	
		return clzFile;
	}

	private void parseAttributes(ClassFile clzFile, ByteCodeIterator iterator) {
		// TODO Auto-generated method stub
		
	}

	private void parseMethods(ClassFile clzFile, ByteCodeIterator iterator) {
		int methodCount = iterator.nextU2ToInt();
		for(int i=0; i < methodCount; i++){
			Method method = Method.parse(clzFile, iterator);
			clzFile.addMethod(method);
		}
		
	}

	private void parseFields(ClassFile clzFile, ByteCodeIterator iterator) {
		int fieldCount = iterator.nextU2ToInt();

		for(int i=0; i < fieldCount; i++){
			Field field = Field.parse(clzFile.getConstantPool(), iterator);
			clzFile.addField(field);
		}
		
	}

	private void parseInterfaces(ByteCodeIterator iterator) {
		int interfaceCount = iterator.nextU2ToInt();
		
		System.out.println("iterfaceCount:" + interfaceCount);
		
		//TODO : 如果实现了interface，这里需要解析
		
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iterator) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iterator.nextU2ToInt());
		classIndex.setSuperClassIndex(iterator.nextU2ToInt());
		return classIndex;
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
