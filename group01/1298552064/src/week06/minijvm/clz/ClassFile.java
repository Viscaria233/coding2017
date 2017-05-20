package week06.minijvm.clz;

import java.util.ArrayList;
import java.util.List;

import week06.minijvm.constant.ClassInfo;
import week06.minijvm.constant.ConstantPool;
import week06.minijvm.field.Field;
import week06.minijvm.method.Method;



public class ClassFile {
	private int minorVersion;
	private int majorVersion;

	private AccessFlag accessFlag;
	private ClassIndex classIndex;
	private ConstantPool constantPool;
	private List<Field> fields = new ArrayList<>();
	private List<Method> methods = new ArrayList<>();

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

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void addField(Field field){
		this.fields.add(field);
	}
	
	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	
	public void addMethod(Method method) {
		this.methods.add(method);
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
	
	@Override
	public String toString() {
		return "ClassFile [minorVersion=" + minorVersion + ", majorVersion=" + majorVersion + ", accessFlag="
				+ accessFlag + ", classIndex=" + classIndex + ", constantPool=" + constantPool + ", fields=" + fields
				+ ", methods=" + methods + "]";
	}

}
