package com.java.bytecode;

public class SynchronizeTest {
	
	public synchronized String sayHello(String name){
		
		return "hello -> "+name;
		
	}
	
	
	public synchronized String sayHello2(String name){
		synchronized (this) {
			name = "jack";
		}
		return "hello -> "+name;
		
	}

}
