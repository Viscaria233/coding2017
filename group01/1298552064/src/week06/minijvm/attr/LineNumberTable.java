package week06.minijvm.attr;

import java.util.ArrayList;
import java.util.List;

import week06.minijvm.loader.ByteCodeIterator;



public class LineNumberTable extends AttributeInfo {
	List<LineNumberItem> items = new ArrayList<>();

	public LineNumberTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}

	public void addLineNumberItem(LineNumberItem item){
		this.items.add(item);
	}
	
	public static LineNumberTable parse(ByteCodeIterator iterator){
		int attrNameIndex = iterator.nextU2ToInt();
		int attrLen = iterator.nextU4ToInt();
		
		LineNumberTable t = new LineNumberTable(attrNameIndex, attrLen);
		
		int lineNumberTableLen = iterator.nextU2ToInt();
		for(int i=0; i < lineNumberTableLen; i++){
			int startPC = iterator.nextU2ToInt();
			
			int lineNumber = iterator.nextU2ToInt();
			
			LineNumberItem item = new LineNumberItem(startPC,lineNumber);
			
			t.addLineNumberItem(item);
		}
		return t;
	}
	
	private static class LineNumberItem {
		//start_pc 项的值必须是code[]数组的一个索引
		int startPC;
		
		//源文件的行号
		int lineNum;

		public LineNumberItem(int startPC,int lineNum){
			this.startPC = startPC;
			this.lineNum = lineNum;
		}
		
		public int getStartPC() {
			return startPC;
		}

		public void setStartPC(int startPC) {
			this.startPC = startPC;
		}

		public int getLineNum() {
			return lineNum;
		}

		public void setLineNum(int lineNum) {
			this.lineNum = lineNum;
		}
	}

}
