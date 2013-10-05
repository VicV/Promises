package com.vicv.promises;

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
	private Object _completeLock = new Object();

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
		synchronized (_completeLock) {
			if (!_complete) {

				for (PromiseListener<T> listener : _allListeners) {
					// uhh I think we need to execute our callbacks finally
					// here?
				}
				_allListeners.clear();

				_state = state;
				_returnable = null;
				_exception = exception;
				_complete = true;
			} else {
				return;
			}
		}
	}

	public boolean hasListeners() {
		return _allListeners.size() > 0;
	}

}
