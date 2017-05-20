package week06.minijvm.util;

public class Util {
	public static String byteToHexString(byte[] codes){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < codes.length; i++){
			byte b = codes[i];
			int value = b & 0xFF;
			String strHex = Integer.toHexString(value);
			if(strHex.length() < 2){
				strHex = "0" + strHex;
			}
			sb.append(strHex);
		}
		return sb.toString();
	}
	
	public static int byteToInt(byte[] codes){
		String s = byteToHexString(codes);
		return Integer.valueOf(s, 16).intValue();
	}
}
