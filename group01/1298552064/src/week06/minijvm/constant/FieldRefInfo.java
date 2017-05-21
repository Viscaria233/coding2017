package week06.minijvm.constant;

public class FieldRefInfo extends ConstantInfo{
	private int type = ConstantInfo.FIELD_REF_INFO;
	
	private int classInfoIndex;
	private int nameAndTypeIndex;
	
	public FieldRefInfo(){}
	
	public FieldRefInfo(ConstantPool constantPool){
		super(constantPool);
	}
	
	public int getClassInfoIndex() {
		return classInfoIndex;
	}

	public void setClassInfoIndex(int classInfoIndex) {
		this.classInfoIndex = classInfoIndex;
	}

	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}

	public void setNameAndTypeIndex(int nameAndTypeIndex) {
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public String getClassName(){
		ClassInfo classInfo = (ClassInfo) this.getConstantInfo(this.getClassInfoIndex());
		return classInfo.getClassName();
	}
	
	public String getFieldName(){
		NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) getConstantInfo(this.getNameAndTypeIndex());
		return nameAndTypeInfo.getName();
	}

	public String getFieldType(){
		NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) getConstantInfo(this.getNameAndTypeIndex());
		return nameAndTypeInfo.getTypeInfo();
	}
	
	@Override
	public String toString() {
		return "FieldRefInfo [type=" + type + ", classInfoIndex=" + classInfoIndex + ", nameAndTypeIndex="
				+ nameAndTypeIndex + "]";
	}
	
	

}
