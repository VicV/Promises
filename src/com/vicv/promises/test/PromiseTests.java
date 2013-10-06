package com.vicv.promises.test;

import org.junit.Test;

import com.vicv.promises.Promise;
import com.vicv.promises.PromiseListener;

public class PromiseTests {
	
	public static void main(String[] args) {
		promiseTimoutTest();
	}

	@Test
	public void basicPromiseSuccess() {

	}

	private static void promiseTimoutTest() {

		Promise<String> promise = new Promise<String>();

		promise.add(new PromiseListener<String>() {
			@Override
			public void failed() {
				System.out.println("yo bro we failed");
			}
			
			@Override
			public void succeeded() {
				System.out.println("yo bro we succeeded");
			}
		});
			
		promise.timeout(5000);

	}

}
