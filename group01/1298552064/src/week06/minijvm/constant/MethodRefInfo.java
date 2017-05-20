package week06.minijvm.constant;

public class MethodRefInfo extends ConstantInfo{
	private int type = ConstantInfo.METHOD_REF_INFO;
	private int classInfoIndex;
	private int nameAndTypeIndex;
	
	public MethodRefInfo(){}
	
	public MethodRefInfo(ConstantPool constantPool){
		this.constantPool = constantPool;
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
		ConstantPool constantPool = this.getConstantPool();
		ClassInfo classInfo = (ClassInfo) constantPool.getConstantInfo(this.getClassInfoIndex());
		return classInfo.getClassName();
	}
	
	public String getMethodName(){
		ConstantPool constantPool = this.getConstantPool();
		NameAndTypeInfo nameAdnTypeInfo = (NameAndTypeInfo) constantPool.getConstantInfo(this.getNameAndTypeIndex());
		return nameAdnTypeInfo.getName();
	}
	
	public String getParamAndReturnType(){
		ConstantPool constantPool = this.getConstantPool();
		NameAndTypeInfo nameAdnTypeInfo = (NameAndTypeInfo) constantPool.getConstantInfo(this.getNameAndTypeIndex());
		return nameAdnTypeInfo.getTypeInfo();
	}
	
	@Override
	public String toString() {
		return "MethodRefInfo [type=" + type + ", classInfoIndex=" + classInfoIndex + ", nameAndTypeIndex="
				+ nameAndTypeIndex + "]";
	}

	
}
