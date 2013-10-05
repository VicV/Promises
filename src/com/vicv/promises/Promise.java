package com.vicv.promises;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Promise<T>{

	public final int PROMISE_FINISHED = 0;
	public final int PROMISE_FAILED = 1;
	public final int PROMISE_CANCELLED= 2;
	public final int PROMISE_NOT_DONE=3;
	
	
	//We're gonna strap listeners to every promise we make. So we can keep track of them here. So many threads!
	private CopyOnWriteArrayList<PromiseListener<T>> _allListeners = new CopyOnWriteArrayList<PromiseListener<T>>();

	private T _returnable;
	private int _state = PROMISE_NOT_DONE;
	private boolean _complete = false;
	
	
}
