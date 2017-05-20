package week06.minijvm.constant;

public class ClassInfo extends ConstantInfo{
	private int type = ConstantInfo.CLASS_INFO;
	
	private int utf8Index;
	
	public ClassInfo(ConstantPool constantPool){
		super(constantPool);
	}
	
	public int getUtf8Index() {
		return utf8Index;
	}

	public void setUtf8Index(int utf8Index) {
		this.utf8Index = utf8Index;
	}

	@Override
	public int getType() {
		return type;
	}
	
	public String getClassName(){
		UTF8Info utf8Info = (UTF8Info) constantPool.getConstantInfo(getUtf8Index());
		return utf8Info.getValue();
	}
	
}
