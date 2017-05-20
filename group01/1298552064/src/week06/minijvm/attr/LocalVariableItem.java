package week06.minijvm.attr;

public class LocalVariableItem {
	//局部变量的索引都在[start_pc,start_pc + length)中
	private int startPC;
	private int length;
	
	//变量名索引(常量池中)
	private int nameIndex;
	
	//变量描述索引(常量池中)
	private int descIndex;
	
	//此局部变量在当前栈帧的局部变量表中的索引
	private int index;

	public int getStartPC() {
		return startPC;
	}

	public void setStartPC(int startPC) {
		this.startPC = startPC;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getDescIndex() {
		return descIndex;
	}

	public void setDescIndex(int descIndex) {
		this.descIndex = descIndex;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
