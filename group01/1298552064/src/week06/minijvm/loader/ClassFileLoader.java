package week06.minijvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import week06.minijvm.clz.ClassFile;


public class ClassFileLoader {
	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {
		if(className == null){
			return null;
		}
		
		File file = null;
		String classPath = className.replace(".", "\\"); 
		for(int i = 0 ; i < clzPaths.size(); i++){
			String basePath = clzPaths.get(i);
			file = new File(basePath + File.separator + classPath + ".class");
			
			if(file.exists()){
				break;
			}
		}
		
		
		//读取字节码文件到数组
		FileInputStream in = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			in = new FileInputStream(file);
			
			byte [] rs = new byte[1024];
			int len = 0;
			while((len = in.read(rs)) != -1){
				bos.write(rs, 0, len);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				bos.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("readBinaryCode:" + " file size = " + file.length());
		return bos.toByteArray();
	}

	public void addClassPath(String path) {
		if(! clzPaths.contains(path)){
			clzPaths.add(path);
		}
	}

	public String getClassPath() {
		StringBuffer buffer = new StringBuffer();
		for(int i = 0;i < clzPaths.size();i++){
			buffer.append(clzPaths.get(i));
			if(i != clzPaths.size() - 1){
				buffer.append(";");
			}
		}
		return buffer.toString();
	}
	
	public ClassFile loadClass(String className){
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
	}
}
