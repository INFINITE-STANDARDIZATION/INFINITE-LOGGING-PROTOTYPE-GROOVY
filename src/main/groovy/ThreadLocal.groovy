package groovy

import java.util.concurrent.ConcurrentHashMap

/*Application servers interfere with normal Thread Locals*/

class ThreadLocal {

    public static ConcurrentHashMap<Thread, ConcurrentHashMap<Class, Object>> objectsByThreadByClass = new ConcurrentHashMap<Thread, ConcurrentHashMap<Class, Object>>()

    static void remove(Class iClass){
        if (objectsByThreadByClass.get(Thread.currentThread()) != null) {
            objectsByThreadByClass.get(Thread.currentThread()).remove(iClass)
            if (objectsByThreadByClass.get(Thread.currentThread()).size() == 0) {
                objectsByThreadByClass.remove(Thread.currentThread())
            }
        }
    }

    static void set(Object iObject){
        if (objectsByThreadByClass.get(Thread.currentThread()) != null) {
            objectsByThreadByClass.get(Thread.currentThread()).put(iObject.getClass(), iObject)
        } else {
            objectsByThreadByClass.put(Thread.currentThread(), new ConcurrentHashMap<Class, Object>())
            objectsByThreadByClass.get(Thread.currentThread()).put(iObject.getClass(), iObject)
        }
    }

    static Object get(Class iClass){
        objectsByThreadByClass.get(Thread.currentThread())?.get(iClass)
    }

}