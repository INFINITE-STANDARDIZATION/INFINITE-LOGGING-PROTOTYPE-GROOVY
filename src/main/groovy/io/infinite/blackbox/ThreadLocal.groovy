package io.infinite.blackbox

import java.util.concurrent.ConcurrentHashMap

/*Application servers interfere with normal Java ThreadLocal*/

class ThreadLocal {

    public static ConcurrentHashMap<Thread, Object> objectsByThread = new ConcurrentHashMap<Thread, Object>()

    static void set(Object iObject){
        objectsByThread.put(Thread.currentThread(), iObject)
    }

    static Object get(){
        objectsByThread.get(Thread.currentThread())
    }

}