package com.java.loader;

import java.lang.reflect.Method;

/**
 * 
 * ClassName: LoopRun <br/>
 * Function: 实现了热替换. <br/>
 * Date: 2018年2月24日 上午9:48:45 <br/>
 *
 * @author fanghuabao
 * @version 
 * @since JDK 1.7
 */
public class LoopRun {
	
	public static void main(String[] args) {
		
		while(true){
			
			try {
				
				MyClassLoader loader = new MyClassLoader("D:/loader");
				Class cls = loader.loadClass("com.java.loader.DemoA");
				Object demo = cls.newInstance();
				Method method = demo.getClass().getMethod("hot", new Class[]{});
				method.invoke(demo, new Object[]{});
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
