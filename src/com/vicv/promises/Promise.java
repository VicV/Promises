package com.vicv.promises;

public class Promise<T> extends PromiseStub {

	public final int PROMISE_FINISHED = 0;
	public final int PROMISE_FAILED = 1;
	public final int PROMISE_CANCELLED= 2;
	public final int PROMISE_NOT_DONE=3;

	private T _returnable;
	private int _state = PROMISE_NOT_DONE;
	private boolean _complete = false;
}
