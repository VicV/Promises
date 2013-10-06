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
				
			}
			
		});
		
		lateAdder(5, 6, myPromise);
		
	}
	
	private static void lateAdder(int a, int b, Promise<Integer> c){
		
		int d = a + b;
		
		c.finish(d);
	}
	
	
	private static void newFunct(){
		
		Promise<Object> promise = new Promise<Object>();
		
		promise.add(new PromiseListener<Object>(){
			@Override
			public void succeeded(Object result) {
				//...
			}
			
			@Override
			public void failed(Exception reason) {
				//...
			}
			//you can also override cancelled, done, and failedOrCancelled
		});
	}
}
