package week06.minijvm.attr;

import java.util.ArrayList;
import java.util.List;

import week06.minijvm.loader.ByteCodeIterator;


public class LocalVariableTable extends AttributeInfo{
	List<LocalVariableItem> items = new ArrayList<>();
	
	public LocalVariableTable(int attrNameIndex,int attrLen){
		super(attrNameIndex, attrLen);
	}
	
	private void addLocalVariableItem(LocalVariableItem item){
		this.items.add(item);
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iterator){
		int attrNameIndex = iterator.nextU2ToInt();
		int attrLen = iterator.nextU4ToInt();
		
		LocalVariableTable localVariableTable = new LocalVariableTable(attrNameIndex, attrLen);
		
		int tableLen = iterator.nextU2ToInt();
		
		for(int i=0; i < tableLen; i++){
			LocalVariableItem item = new LocalVariableItem();
			item.setStartPC(iterator.nextU2ToInt());
			item.setLength(iterator.nextU2ToInt());
			item.setNameIndex(iterator.nextU2ToInt());
			item.setDescIndex(iterator.nextU2ToInt());
			item.setIndex(iterator.nextU2ToInt());
			
			localVariableTable.addLocalVariableItem(item);
		}
		
		return localVariableTable;
	}
}
