Promises
========

My attempt at functional java, at MITHacks

There are of course many implementations of Promises in Java. However, I don't like them. 

This was a test of whether or not I could actually MAKE this, and of course whether I could bring functional coding functionality to a non-functional language in a way such that people (such as android devs) that do NOT use functional languages can have the benefits in an easy to understand way.

So, an example of using this Library for promises:

        Promise<Object> promise = new Promise<Object>();
        
		promise.add(new PromiseListener<Object>(){
			@Override
			public void succeeded(Object result) {
				//...
			}
			
			@Override
			public void failed(Exception reason) {
				//...
			}
			
			//you can also override cancelled, done, and faildOrCancelled
		});

As I had said before, there are alternatives.

A very popular promise / deferred library [jDeferred](http://jdeferred.org/) has this example snippet:

        Deferred deferred = new DeferredObject();
        Promise promise = deferred.promise();
        promise.done(new DoneCallback() {
                public void onDone(Object result) {
                        ...
                }
        }).fail(new FailCallback() {
                public void onFail(Object rejection) {
                        ...
                }
        }).progress(new ProgressCallback() {
                public void onProgress(Object progress) {
                        ...
                }
        }).always(new AlwaysCallback() {
                public void onAlways(State state, Object result, Object rejection) {
                        ...
                }
        });

Using this Promises library, we can actually add multiple listeners to the promise, and they all get triggered during an event on the promise.
Also, this method of adding listeners means that you can actually add a listener to a previously resolved Promise, and it will give you what you want. 

##HOW DO

Okay so for an example:

       Promise<Object> promise = new Promise<Object>();
        
		promise.add(new PromiseListener<Object>(){
			@Override
			public void succeeded(Object result) {
				//...
			}
			
			@Override
			public void failed(Exception reason) {
				//...
			}
			
			//you can also override cancelled, done, and faildOrCancelled
		});
		
And to resolve:

        promise.finish();
        
or to resolve with an object:

        promise.finish(Object);
        
or to fail, cancel, etc:

        promise.fail();
        
        promis.fail(Exception);
        
        promise.cancel();
        
etc...

If your fail or cancel your promise, it will automatically also hit failedOrCancelled


So, if you do a .finish(), it calls your promise's succeed callback, and so on and so forth for fail/cancel.


###Why not scala?
Because I don't feel like learning an entirely new language just for one functional feature? Plus, I'm an Android dev, and Scala + android is _really_ cool but not quite where it needs to be.

And, Im doing this for its _simplicity_. Is just a new Object type. You don't need to know how it works. Its _so_ straightforward.



##Progress

Update 1: So far, I've created the general listener and an interface for promises. The first commit will be chunky because I didnt have wifi for a while.

Update 2: So, the listener will look out for these events: Failed, Cancelled, (and failedOrCancelled for the lazy), and succeed. 

Update 3: It works! I'm killing memory with all the promises laying around though.
