package 高并发;

import sun.misc.Unsafe;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author www.yanzhongxin.com
 * @date 2020/10/30 12:21
 */
public class ReentrantLockDemo {
   static ReentrantLock  lock=new ReentrantLock();
    public static void main(String[] args) {

        testLockSupport();

    }




    public static void test读写锁(){

        ReentrantReadWriteLock lock=new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        readLock.lock();
        readLock.unlock();

        writeLock.lock();
        writeLock.unlock();
    }

        public static void testLockSupport(){
        LockSupport.park();
        LockSupport.park(new Object());
    }

    public static void testReentrantlock(){
        ReentrantLock  lock=new ReentrantLock();
        Object ob=new Object();
        lock.tryLock();

    }

    public static void testUnsafe(){


        Unsafe unsafe=Unsafe.getUnsafe();
        LockSupport.park();

    }
    public static void test多线程(){
        /*Thread t1= new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(100000);
            }catch (Exception e){

            }

            lock.unlock();


        });
        t1.setName("t1");

        Thread t2= new Thread(()->{

            lock.lock();


            lock.unlock();

        });
        t2.setName("t2");*/
    }
}
