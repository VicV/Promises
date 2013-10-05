package com.vicv.promises;

/**
 * Our listener for promises. The most important stuff, right here. So, this is
 * what a promise can listen for.
 * 
 * @author V
 * 
 * @param <T>
 */
public class PromiseListener<T> {

	// If something fucks up
	public void failed() {

	}

	// If something fucks up and we wanna talk about it
	public void failed(Throwable reason) {

	}

	// If someone decided to pull the plug
	public void cancelled() {

	}

	// At this point we're just lazy, maybe I should just call this 'ended'
	public void failedOrCancelled(Throwable reason) {

	}

	public void failedOrCancelled() {

	}

	// Woo everything looks good.
	public void succeeded(T returnable) {

	}

	public void succeeded() {

	}

}
