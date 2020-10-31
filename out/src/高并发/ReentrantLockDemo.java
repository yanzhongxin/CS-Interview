package 高并发;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author www.yanzhongxin.com
 * @date 2020/10/30 12:21
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {

    }

    public static void testReentrantlock(){
        ReentrantLock  lock=new ReentrantLock();
        Object ob=new Object();
        lock.lock();


        lock.unlock();
    }
}
