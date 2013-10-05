Promises
========

My attempt at functional java, at MITHacks



So, the things I am doing are based off of memory from an implemetation of something similar at Kik (meaning that I really don't think I deserve to win anything because this is something that already exists and there are alternatives.)

Also, Kik, open source that stuff, my implementation is gonna be terribad.

Luckily, in my last few weeks there I had to work with their implementation a bunch so I think I know how to do this.

But, I just remember how it _works_. I don't remember their implementation at all..


###Why am I doing this?
I'm doing this mainly because at Kik, the implentation was far more beautiful than any others available. For people that aren't deep into java but do Android dev, this will be the easiest way for them to add promises. Plus, when the promise is an object, you get to have wayyy more fun. Passing around threads like candy? Yeah, think about it.

Also, since I stopped working at Kik, I nearly died not being able to use them again (mHacks was painful due to this). 

And, I wanted to do a Java hack, a useful hack, (and I'm _realllly_ bad at java/android multithreading stuff so I want to learn).
###What I want it to do

basically what SHOULD happen is:

        Promise myPromise = new Promise<T>().add(new PromiseListener{

          @Override
            succeeded(T returnable){
              //dostuff
            }
        }
        
        
So, for those that use Javascript (or any other functional language), you'll know that callbacks are beautiful, useful, and amazing. I wanted to bring the simplicity of them to Java.

###Why not scala?
Because I don't feel like learning an entirely new language just for one functional feature? Plus, I'm an Android dev, and Scala + android is _really_ cool but not quite where it needs to be.

And, Im doing this for its _simplicity_. Is just a new Object type. You don't need to know how it works. Its _so_ straightforward.





##Progress

Update 1: So far, I've created the general listener and an interface for promises. The first commit will be chunky because I didnt have wifi for a while.

Update 2: So, the listener will look out for these events: Failed, Cancelled, (and failedOrCancelled for the lazy), and succeed. 
