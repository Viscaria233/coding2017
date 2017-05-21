package week05.minijvm.clz;

import week05.minijvm.constant.ClassInfo;
import week05.minijvm.constant.ConstantPool;

public class ClassFile {
	private int minorVersion;
	private int majorVersion;

	private AccessFlag accessFlag;
	private ClassIndex classIndex;
	private ConstantPool constantPool;

	public int getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	public AccessFlag getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	}

	public ClassIndex getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(ClassIndex classIndex) {
		this.classIndex = classIndex;
	}

	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public void setConstantPool(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}

	public void print(){
		if(this.accessFlag.isPublicClass()){
			System.out.println("Access flag : public ");
		}
		
		System.out.println("Class name:" + getClassName());
	}
	
	public String getClassName(){
		int thisClassIndex = this.classIndex.getThisClassIndex();
		ClassInfo classInfo = (ClassInfo) getConstantPool().getConstantInfo(thisClassIndex);
		return classInfo.getClassName();
	}
	
	public String getSuperClassName(){
		int superClassIndex = this.classIndex.getSuperClassIndex();
		ClassInfo classInfo = (ClassInfo) getConstantPool().getConstantInfo(superClassIndex);
		return classInfo.getClassName();
	}
	
}
