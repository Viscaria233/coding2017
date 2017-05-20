package week05.minijvm.loader;

import java.util.Arrays;

import week05.minijvm.util.Util;

public class ByteCodeIterator {
	private byte[] codes;
	private int pos;
	
	public ByteCodeIterator(byte[] codes){
		this.codes = codes;
	}

	public int nextU1ToInt(){
		return Util.byteToInt(new byte[]{codes[pos++]});
	}
	
	public int nextU2ToInt() {
		return Util.byteToInt(new byte[]{codes[pos++],codes[pos++]});
	}
	
	public int nextU4ToInt(){
		return Util.byteToInt(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}
	
	public String nextU4ToHexString() {
		return Util.byteToHexString(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}
	
	
	public byte[] getBytes(int len){
		if(pos + len >= codes.length){
			throw new ArrayIndexOutOfBoundsException();
		}
		
		byte [] data = Arrays.copyOfRange(codes, pos, pos + len);
		pos += len;
		return data;
	}
	public String nextUxToHexString(int len){
		byte[] bytes = new byte[len];
		for(int i = 0 ; i < len; i++){
			bytes[i] = codes[pos++];
		}
		return Util.byteToHexString(bytes).toLowerCase();
	}
	
	public void back(int n){
		this.pos -= n;
	}
}
