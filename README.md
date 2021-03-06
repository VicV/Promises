Promises (for Java and Android)
================================================

My attempt at functional java, at HackMIT. Not using any APIs or anything for a prize, just having fun.

There are of course many implementations of Promises in Java (jdeffered, functionaljava). However, I don't like them. 

Also, Im making this off of my memory of how a place I used to work does it. Kind of reverse engineering it. 

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

Obviously, you dont need to override things you arent using.

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

Using my Promises library over this, we can actually add multiple listeners to the promise, and they all get triggered during an event on the promise.
Also, this method of adding listeners means that you can actually add a listener to a previously resolved Promise, and it will give you what you want. 

## HOW TO USE

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
			
			//you can also override cancelled, done, and failedOrCancelled
		});
		
### Resolving a promise
		
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

### Timeouts

You can also force a promise to timeout by simply doing 

	promise.timeout(milliseconds);
	
And after that time, the promise will automatically fail. However, the timeout will not fail the promise if the promise is resolved within that time.

## BUT WAIT, what is a promise anyway?

So, its a callback but fancier. If you're asking what a promise is, this is perfect for you so I wont go into the technical detail of what a promise is.

Essentially, you are promising something that you will finish it later. Lets say you make your friend a promise that if you ever discover what the king of norway's shoes look like, you will paint your hat blue.

So, you have created a promise, and you have a 'success' action (painting your hat blue). This success action is what the success _callback_ is. 

The beauty of it though is that you don't have to finish your promise now. You can fulfill this promise years in the future so long as you remember it (ie, store it in a map).


## WHY WOULD I EVEN USE THIS, MAN

I dunno? Its cool having callbacks. I would mainly use this for Android though.

Possible applications
 * HTTP requests
 * Caching
 * Lazyloading
 * Like, these are callbacks, use them for everything. Even my timeout USES promises to act as a timeout.
 
Promises are used so we don't block any threads while performing a task. 

For example, as seen in [Alerta](https://github.com/ajwootto/alerta) (which is a hack at hackMIT that actually makes use of this library)..


###Why not Scala?
Because I don't feel like learning an entirely new language just for one functional feature? Plus, I'm an Android dev, and Scala + android is _really_ cool but not quite where it needs to be.

And, Im doing this for its _simplicity_. Is just a new Object type. You don't need to know how it works. Its _so_ straightforward.



## Progress

Update 1: So far, I've created the general listener and an interface for promises. The first commit will be chunky because I didnt have wifi for a while.

Update 2: So, the listener will look out for these events: Failed, Cancelled, (and failedOrCancelled for the lazy), and succeed. 

Update 3: It works! I'm killing memory with all the promises laying around though.

Update 4: Cleaned up some code. About to get into some neat stuff like timeout promises and whatnot. Yay!

Update 5: I added timeouts and some quick unit tests.
