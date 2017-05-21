package week06.minijvm.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {
	private List<ConstantInfo> constantInfos = new ArrayList<>();
	
	public ConstantPool(){}
	
	public void addConstantInfo(ConstantInfo constantInfo){
		this.constantInfos.add(constantInfo);
	}
	
	public ConstantInfo getConstantInfo(int index){
		return this.constantInfos.get(index);
	}
	
	public int getSize(){
		return constantInfos.size();
	}
	
	public String getUTF8String(int index){
		return ((UTF8Info)constantInfos.get(index)).getValue();
	}
}
