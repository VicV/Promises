Promises
========

My attempt at functional java, at MITHacks



So, the things I am doing are based off of memory from an implemetation of something similar at Kik.

Luckily, in my last few weeks there I had to work with their implementation a bunch so I think I know how to do this.

But, I just remember how it _works_. I don't remember their implementation at all..

###What I want it to do

basically what SHOULD happen is:

        Promise myPromise = new Promise<T>().add(new PromiseListener{

          @Override
            succeeded(T returnable){
              //dostuff
            }
        }


        


So... Here goes nothing.


Notes: I'm doing this mainly because at Kik, the implentation was far more beautiful than any others available. For people that aren't deep into java but do Android dev, this will be the easiest way for them to add promises. Plus, when the promise is an object, you get to have wayyy more fun. Passing around threads like candy? Yeah, think about it.

##Progress

Update 1: So far, I've created the general listener and an interface for promises. The first commit will be chunky because I didnt have wifi for a while.

Update 2: So, the listener will look out for these events: Failed, Cancelled, (and failedOrCancelled for the lazy), and succeed. 
