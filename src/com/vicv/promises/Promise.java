package com.vicv.promises;

import java.util.ArrayList;
import java.util.List;

public class Promise<T> {

	public enum State {
		finished, failed, cancelled, notDone
	}

	// We're gonna strap listeners to every promise we make. So we can keep
	// track of them here. So many threads!
	private List<PromiseListener<T>> _allListeners = new ArrayList<PromiseListener<T>>();

	// Tried to use a boolean? bleh. Creds to Jesse for teaching me this one.
	private Object _completionLock = new Object();

	// The thing we give back!
	private T _result;

	// State yo
	private State _state = State.notDone;

	// Whether or not this promise is done
	private boolean _complete = false;

	// Why you gotta break, man?
	private Exception _exception;

	// Clear up the promisess!
	private void finishPromise(State state, T returnable, Exception exception) {

		List<PromiseListener<T>> listeners = new ArrayList<>(_allListeners);

		synchronized (_completionLock) {
			if (!_complete) {
				_allListeners.clear();
				_state = state;
				_exception = exception;
				_complete = true;
				_result = returnable;
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
	private void reactToListener(PromiseListener<T> currentListener, State state) {

		switch (state) {
		case finished:
			currentListener.succeeded(_result);
			currentListener.succeeded();
			break;
		case failed:
			currentListener.failed(_exception);
			currentListener.failed();
			currentListener.failedOrCancelled(_exception);
			currentListener.failedOrCancelled();
			break;
		case cancelled:
			currentListener.cancelled();
			currentListener.failedOrCancelled(_exception);
			currentListener.failedOrCancelled();
			break;
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
		finishPromise(State.finished, result, null);
	}

	public void fail(Exception e) {
		finishPromise(State.failed, null, e);
	}

	public void cancel() {
		finishPromise(State.cancelled, null, null);
	}
}
