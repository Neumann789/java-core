package com.java.loader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class MyClassLoader extends ClassLoader{
	
	private String fileDirPath;
	
	public MyClassLoader(String fileDirPath) {
		this.fileDirPath = fileDirPath;
	}
	
	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		
		Class clazz = this.findLoadedClass(className);
		
		if(clazz == null){
			try {
				String classFile = getClassFile(className);
				FileInputStream fis = new FileInputStream(classFile);
				FileChannel fc = fis.getChannel();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				WritableByteChannel wbc = Channels.newChannel(baos);
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				while(true){
					int i = fc.read(buffer);
					if(i == 0 || i == -1){
						break;
					}
					buffer.flip();
					wbc.write(buffer);
					buffer.clear();
				}
				fis.close();
				byte[] bytes = baos.toByteArray();
				clazz = defineClass(className, bytes, 0, bytes.length);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return clazz;
	}
	
	private String getClassFile(String className){
		
		String newFileDirPath = "";
		String classFilePath = ""; 
		
		if(!fileDirPath.endsWith("/")){
			newFileDirPath = fileDirPath.concat("/");
		}else{
			newFileDirPath = fileDirPath;
		}
		
		className = className.replaceAll("\\.", "/");
		
		classFilePath = newFileDirPath.concat(className).concat(".class");
		
		return classFilePath;
	}

}
