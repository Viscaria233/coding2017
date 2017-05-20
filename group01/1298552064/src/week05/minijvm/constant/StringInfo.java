package week05.minijvm.constant;

public class StringInfo extends ConstantInfo{
	private int type = ConstantInfo.STRING_INFO;
	private int index;
	
	public StringInfo(ConstantPool constantPool){
		super(constantPool);
	}
	
	@Override
	public int getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "StringInfo [type=" + type + ", index=" + index + "]";
	}

}
