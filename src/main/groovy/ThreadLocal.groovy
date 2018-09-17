package groovy

import java.util.concurrent.ConcurrentHashMap

/*Apache tomcat interferes with normal Thread locals.*/

class ThreadLocal<Type> {

    public static ConcurrentHashMap<Thread, ConcurrentHashMap<Class, Type>> objectsByThreadByClass = new ConcurrentHashMap<Thread, ConcurrentHashMap<Class, Type>>()

    static void remove(Class iClass){
        if (objectsByThreadByClass.get(Thread.currentThread()) != null) {
            objectsByThreadByClass.get(Thread.currentThread()).remove(iClass)
            if (objectsByThreadByClass.get(Thread.currentThread()).size() == 0) {
                objectsByThreadByClass.remove(Thread.currentThread())
            }
        }
    }

    static void set(Type iObject){
        if (objectsByThreadByClass.get(Thread.currentThread()) != null) {
            objectsByThreadByClass.get(Thread.currentThread()).put(iObject.getClass(), iObject)
        } else {
            objectsByThreadByClass.put(Thread.currentThread(), new ConcurrentHashMap<Class, Type>())
            objectsByThreadByClass.get(Thread.currentThread()).put(iObject.getClass(), iObject)
        }
    }

    static Type get(Class iClass){
        objectsByThreadByClass.get(Thread.currentThread())?.get(iClass)
    }

}