package com.vicv.promises.test;

import com.vicv.promises.Promise;
import com.vicv.promises.PromiseListener;

public class PromiseTests {


	public static void main(String[] args) {
		
		Promise<Integer> myPromise = new Promise<Integer>();
		
		myPromise.add(new PromiseListener<Integer>(){
			@Override
			public void succeeded(Integer returnable) {
			
				System.out.print(returnable);
				
				super.succeeded(returnable);
			}
			
			@Override
			public void failed() {
				super.failed();
			}
		});
		
		lateAdder(5, 6, myPromise);
		
	}
	
	private static void lateAdder(int a, int b, Promise<Integer> c){
		
		int d = a + b;
		
		c.finish(d);
	}
	
}
