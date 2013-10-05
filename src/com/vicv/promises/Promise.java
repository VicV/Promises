package com.vicv.promises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Promise<T> {

	public final int PROMISE_FINISHED = 0;
	public final int PROMISE_FAILED = 1;
	public final int PROMISE_CANCELLED = 2;
	public final int PROMISE_NOT_DONE = 3;

	// We're gonna strap listeners to every promise we make. So we can keep
	// track of them here. So many threads!
	private CopyOnWriteArrayList<PromiseListener<T>> _allListeners = new CopyOnWriteArrayList<PromiseListener<T>>();

	// Tried to use a boolean? bleh. Creds to Jesse for teaching me this one.
	private Object _completionLock = new Object();

	// The thing we give back!
	private T _returnable;

	// State yo
	private int _state = PROMISE_NOT_DONE;

	// Whether or not this promise is done
	private boolean _complete = false;

	// Why you gotta break, man?
	private Exception _exception;

	// Clear up the promisess!
	private void finishPromise(int state, T returnable, Exception exception) {
		
		List<PromiseListener<T>> listeners = _allListeners;
		
		synchronized (_completionLock) {
			if (!_complete) {
				_allListeners.clear();
				_state = state;
				_exception = exception;
				_complete = true;
				_returnable = returnable;
			} else {
				return;
			}
		}
		

		for (PromiseListener<T> listener : listeners) {
			reactToListener(listener, state);
		}
	}

	// Something happened! Yay!
	// TODO: Refactor. This is ugly.
	private void reactToListener(PromiseListener<T> currentListener, int state) {

		if (state == PROMISE_FINISHED) {
			currentListener.succeeded(_returnable);
			currentListener.succeeded();
		} else if (state == PROMISE_FAILED) {
			currentListener.failed(_exception);
			currentListener.failed();
			currentListener.failedOrCancelled(_exception);
			currentListener.failedOrCancelled();
		} else if (state == PROMISE_CANCELLED) {
			currentListener.cancelled();
			currentListener.failedOrCancelled(_exception);
			currentListener.failedOrCancelled();
		}

	}

	public boolean hasListeners() {
		return _allListeners.size() > 0;
	}

	// Okay so now what we WANT To happen, is that when we strap a listener to
	// promise, its going to wait for a call. But if the promise has already
	// completed? Then we go through the finish process.
	public <TypedPromiseListener extends PromiseListener<T>> TypedPromiseListener add(
			TypedPromiseListener newListener) {
		if (_complete) {
			// This is neato:
			// If the promise is done, and we try to listen to it, we'll return
			// the final results
			reactToListener(newListener, _state);
		} else {
			_allListeners.add(newListener);
		}

		return newListener;
	}

	public void finish(T result) {
		finishPromise(PROMISE_FINISHED, result, null);
	}

	public void fail(Exception e) {
		finishPromise(PROMISE_FAILED, null, e);
	}

	public void cancel() {
		finishPromise(PROMISE_CANCELLED, null, null);
	}
}
