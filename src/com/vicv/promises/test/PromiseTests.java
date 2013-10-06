package com.vicv.promises.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeoutException;

import javax.security.sasl.SaslException;

import org.junit.Test;

import com.vicv.promises.Promise;
import com.vicv.promises.PromiseListener;

public class PromiseTests {

	@Test
	public void basicPromiseSuccess() {

		String successString = "succeeded";
		Promise<String> promise = new Promise<String>();

		promise.add(new PromiseListener<String>() {
			@Override
			public void succeeded() {
				super.succeeded();
			}
		});

		promise.finish("succeeded");

		assertEquals(successString, promise.getResult());
		assertEquals(Promise.State.finished, promise.getState());

	}

	@Test
	public void basicPromiseFailure() {

		String failedString = "failed";
		Promise<String> promise = new Promise<String>();

		promise.add(new PromiseListener<String>() {
			@Override
			public void succeeded() {
				super.succeeded();
			}
		});

		promise.fail(new SaslException());

		assertEquals(promise.getResult(), null);
		assertTrue(promise.getException() instanceof SaslException);
		assertEquals(promise.getState(), Promise.State.failed);

	}

	@Test
	public void basicPromiseCancellation() {

		Promise<String> promise = new Promise<String>();

		promise.cancel();

		assertEquals(promise.getResult(), null);
		assertEquals(promise.getState(), Promise.State.cancelled);

	}

	// Tests timeout. Should get a failure.
	@Test
	public void promiseTimoutTest() {

		Promise<String> promise = new Promise<String>();

		promise.timeout(1000);

		try {
			Thread.sleep(1100);
		} catch (InterruptedException ex) {
			// Thread.currentThread().interrupt();
		}

		assertTrue(promise.getException() instanceof TimeoutException);

	}
	
	// Sets a timeout, then finishes the promise. Should not fail.
		@Test
		public void promiseSuccessTimoutTest() {

			Promise<String> promise = new Promise<String>();

			promise.timeout(1000);

			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				// Thread.currentThread().interrupt();
			}
			
			promise.finish();

			assertEquals(promise.getState(), Promise.State.finished);
			
			assertEquals(promise.getException(), null);


		}


}
