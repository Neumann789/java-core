package com.java.core.map;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * ClassName: ConcurrentHashMapTest <br/>
 * Function: 测试ConcurrentHashMap. <br/>
 * Date: 2017年12月9日 上午11:34:25 <br/>
 *
 * @author fanghuabao
 * @version 
 * @since JDK 1.7
 */
public class ConcurrentHashMapTest {
	
	public static ConcurrentHashMap<String, Object> LOCAL_CACHE = new ConcurrentHashMap<>();
	
	static{
		LOCAL_CACHE.put("name", "hello");
	}
	
	public static void main(String[] args) throws Throwable {
		
		List<String> list = new ArrayList<>();
		list.add("11111");
		list.add("22222");
		list.add("33333");
		LOCAL_CACHE.put("list", list);
		
		new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						//System.out.println("name:"+LOCAL_CACHE.get("name"));
						System.out.println("list:"+((List<String>)LOCAL_CACHE.get("list")).size());
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException();
					}
					
				}
			}
		}.start();
		
		Thread.sleep(10000);
		System.out.println("等待10秒......");
		
		//LOCAL_CACHE.remove("name");
		LOCAL_CACHE.remove("list");
		System.out.println("删除成功");
		
		/*new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						LOCAL_CACHE.remove("name");
						Thread.sleep(500);
					} catch (Exception e) {
					}
					
				}
			}
		}.start();*/
		
		System.in.read();
	}

}
