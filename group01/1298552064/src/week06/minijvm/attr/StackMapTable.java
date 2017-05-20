package week06.minijvm.attr;

import week06.minijvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo {

	private String originalCode;

	public StackMapTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}

	private void setOriginalCode(String code) {
		this.originalCode = code;
	}

	public static StackMapTable parse(ByteCodeIterator iterator) {
		int nameIndex = iterator.nextU2ToInt();
		int length = iterator.nextU4ToInt();

		StackMapTable t = new StackMapTable(nameIndex, length);
		t.setOriginalCode(iterator.nextUxToHexString(length));

		return t;
	}

}
