package com.vicv.promises.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.vicv.promises.Promise;

public class PromiseUtils {

	private static final ScheduledExecutorService worker = Executors
			.newSingleThreadScheduledExecutor();
	
	
	private volatile static ScheduledFuture<?> futureTask;

	// Fails a promise after a set time. (I miss android handlers!)
	// http://stackoverflow.com/questions/3072173/how-to-call-a-method-after-a-delay
	// http://stackoverflow.com/questions/7269294/how-to-stop-a-runnable-scheduled-for-repeated-execution-after-a-certain-number-o
	public static <T> Promise<T> timeoutPromise(
			final Promise<T> initialPromise, int millis) {


		 futureTask = worker.schedule(new Runnable() {

			@Override
			public void run() {
				initialPromise.fail(new TimeoutException());
			}
		}, millis, TimeUnit.MILLISECONDS);

		return initialPromise;
	}
}
