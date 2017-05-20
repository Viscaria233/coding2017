package week06.minijvm.constant;

public class NameAndTypeInfo extends ConstantInfo{
	private int type = ConstantInfo.NAME_AND_TYPE_INFO;
	
	private int nameIndex;
	private int descriptorIndex;
	
	public NameAndTypeInfo(){}
	
	public NameAndTypeInfo(ConstantPool constantPool){
		super(constantPool);
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

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public String getName(){
		ConstantPool constantPool = this.constantPool;
		UTF8Info utf8Info_name = (UTF8Info) constantPool.getConstantInfo(nameIndex);
		return utf8Info_name.getValue();
	}
	
	public String getTypeInfo(){
		ConstantPool constantPool = this.constantPool;
		UTF8Info utf8Info_descriptor = (UTF8Info) constantPool.getConstantInfo(descriptorIndex);
		return utf8Info_descriptor.getValue();
	}
	
	@Override
	public String toString() {
		return "NameAndTypeInfo [type=" + type + ", nameIndex=" + nameIndex + ", descriptorIndex=" + descriptorIndex
				+ "]";
	}
}
