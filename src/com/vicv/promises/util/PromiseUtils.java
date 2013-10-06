package com.vicv.promises.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.vicv.promises.Promise;

public class PromiseUtils {

	private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

	// Fails a promise after a set time.
	//http://stackoverflow.com/questions/3072173/how-to-call-a-method-after-a-delay
	public static <T> Promise<T> timeoutPromise(final Promise<T> initialPromise, int millis) {
		
		Promise<T> failingPromise = initialPromise;
		
		Runnable futureTask = new Runnable() {
			
			@Override
			public void run() {
				initialPromise.fail(new TimeoutException());
			}
		};
		
		worker.schedule(futureTask, millis, TimeUnit.MILLISECONDS);
		
		return initialPromise; 
	}
}
