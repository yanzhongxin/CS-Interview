package 高并发;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.ref.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author www.yanzhongxin.com
 * @date 2020/10/31 16:16
 */
public class JavaThreadDemo {
    static Object lock=new Object();
    static List list=new ArrayList();
    static ReferenceQueue queue=new ReferenceQueue();
    static ThreadLocal<Object> threadLocal=new ThreadLocal<>();
    public static void main(String[] args) throws  Exception{


        PhantomReference phantomReference=new PhantomReference(new OverFinalizeObject(), queue);
        new Thread(()->{

            while (true){
                list.add(new byte[1024*1024]);//不断添加数据，导致堆内存溢出，发生gc,回收虚引用
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("获取虚幻引用"+phantomReference.get());
            }

        }).start();

        new Thread(()->{
            while (true){
                Reference<? extends OverFinalizeObject> poll=queue.poll();
                if (poll!=null){//被立即回收的虚引用会首先加入到队列中
                    System.out.println("这是虚引用回收的东西"+poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void testSoftReference() throws Exception{
        //软引用指向了10M堆内存
        SoftReference<byte[]> softReference=new SoftReference(new Byte[1024*1024*10]);
        System.out.println(softReference.get());
        System.gc();
        Thread.sleep(500);
        System.out.println(softReference.get());//内存充足，软引用对象保留
        //强引用指向了15M堆内存。－Xmx:20。堆内存最大
        byte[] b=new byte[1024*1024*15];//20-15=5，堆内存不足
        System.out.println("内存不足 "+softReference.get());

    }

    public static void testThreatStatus() throws Exception{
        Thread.sleep(1000000);
    }
    public static void printallThread(){
        // 获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.
                    getThreadName());
        }
    }
}
class  MyThread extends Thread{
    @Override
    public void run() {
        synchronized (JavaThreadDemo.lock){
            while (true){
                System.out.println("i get lock");
            }
        }

    }
}

class OverFinalizeObject{
    protected void finalize() throws Throwable {
        System.out.println("我是OverFinalizeObject对象，即将被gc回收");
    }
}
class Person{
    String name;
}
