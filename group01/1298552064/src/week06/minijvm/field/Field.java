package week06.minijvm.field;


import week06.minijvm.constant.ConstantPool;
import week06.minijvm.loader.ByteCodeIterator;

public class Field{
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	private ConstantPool pool;
	
	public Field(int accessFlag, int nameIndex,int descriptorIndex,ConstantPool pool){
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}
	
	public static Field parse(ConstantPool pool, ByteCodeIterator iterator){
		int accessFlag = iterator.nextU2ToInt();
		int nameIndex = iterator.nextU2ToInt();
		int descriptorIndex = iterator.nextU2ToInt();
		int attribCount = iterator.nextU2ToInt();
		
		
		Field field = new Field(accessFlag, nameIndex, descriptorIndex, pool);
		
		if(attribCount > 0){
			throw new RuntimeException("Field Attribute has not been implemented.");
		}
		
		return field;
	}
	
	@Override
	public String toString(){
		String name = pool.getUTF8String(nameIndex);
		
		String desc = pool.getUTF8String(descriptorIndex);
	
		return name + ":" + desc;
	}
}
