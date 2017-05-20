package week06.minijvm.attr;

import java.util.List;

import week06.minijvm.clz.ClassFile;
import week06.minijvm.loader.ByteCodeIterator;



public class CodeAttr extends AttributeInfo{
	
	private int maxStack;
	private int maxLocals;
	private int codeLen;
	private String code;
	
	private LineNumberTable lineNumTable;
	private LocalVariableTable localVarTable;
	private StackMapTable stackMapTable;
	private List<ExceptionTable> exceptionTables;
	
	private static class ExceptionTable{
		int startPC;
		int endPC;
		int handlerPC;
		int catchType;
		
		public ExceptionTable(int startPC,int endPC,int handlerPC,int catchType){
			this.startPC = startPC;
			this.endPC = endPC;
			this.handlerPC = handlerPC;
			this.catchType = catchType;
		}
		
		public int getStartPC() {
			return startPC;
		}
		
		public int getEndPC() {
			return endPC;
		}
		
		public int getHandlerPC() {
			return handlerPC;
		}
	
		public int getCatchType() {
			return catchType;
		}
		
	}
	
	public void addExceptionTable(ExceptionTable exptionTable){
		this.exceptionTables.add(exptionTable);
	}
	
	public CodeAttr(int attrNameIndex, int attrLen,int maxStack,int maxLocals,int codeLen,String code) {
		super(attrNameIndex, attrLen);
		this.maxStack = maxStack;
		this.maxLocals = maxLocals;
		this.codeLen = codeLen;
		this.code = code;
	}
	
	public void setLineNumberTable(LineNumberTable t){
		this.lineNumTable = t;
	}
	
	public void setLocalVariableTable(LocalVariableTable t){
		this.localVarTable = t;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public static CodeAttr parse(ClassFile clzFile,ByteCodeIterator iterator){
		int attrNameIndex = iterator.nextU2ToInt();
		int attrLen = iterator.nextU4ToInt();
		int maxStack = iterator.nextU2ToInt();
		int maxLocals = iterator.nextU2ToInt();
		int codeLen = iterator.nextU4ToInt();
		
		String code = iterator.nextUxToHexString(codeLen);
		
		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code);
		
		int exceptionTableLen = iterator.nextU2ToInt();
		
		//对Exception进行处理
		for(int i=0; i < exceptionTableLen; i++){
			int startPC = iterator.nextU2ToInt();
			int endPC = iterator.nextU2ToInt();
			int handlerPC = iterator.nextU2ToInt();
			int catchType = iterator.nextU2ToInt();
			ExceptionTable exceptionTable = new ExceptionTable(startPC,endPC,handlerPC,catchType);
			codeAttr.addExceptionTable(exceptionTable);
		}
		
		//解析子属性
		int subAttrCount = iterator.nextU2ToInt();
		for(int i=0; i < subAttrCount; i++){
			int subAttrIndex = iterator.nextU2ToInt();
			String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrIndex);
			
			iterator.back(2);
			
			if(AttributeInfo.LINE_NUMBER_TABLE.equals(subAttrName)){
				LineNumberTable t = LineNumberTable.parse(iterator);
				codeAttr.setLineNumberTable(t);
			}else if(AttributeInfo.LOCAL_VARIABLE_TABLE.equals(subAttrName)){
				LocalVariableTable t = LocalVariableTable.parse(iterator);
				codeAttr.setLocalVariableTable(t);
			}else if(AttributeInfo.STACK_MAP_TABLE.equals(subAttrName)){
				StackMapTable t = StackMapTable.parse(iterator);
				codeAttr.setStackMapTable(t);
			}else{
				throw new RuntimeException("Need code to process " + subAttrName);
			}
		}
		
		return codeAttr;
	}
	
	public void setStackMapTable(StackMapTable t){
		this.stackMapTable = t;
	}
}
