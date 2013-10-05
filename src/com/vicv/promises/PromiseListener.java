package com.vicv.promises;

public class PromiseListener<T> {
	
	public void failed(){
		
	}

	public void failed(Throwable reason) {

	}

	public void cancelled() {

	}

	public void failedOrCancelled(Throwable reason) {

	}

	public void failedOrCancelled() {

	}

	public void finished() {

	}
	
	public void succeeded(T returnable){
		
	}
	
	public void succeeded(){
		
	}

}
