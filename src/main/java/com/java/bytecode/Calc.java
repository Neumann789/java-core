package com.java.bytecode;

public class Calc {
	
	public int calc(){
		int a = 500;
		int b = 200;
		int c = 50;
		
		{
			int d = 100;
			//System.out.println(d / c);
		}
		return (a + b) / c;
	}

}
