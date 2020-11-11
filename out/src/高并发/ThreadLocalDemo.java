package 高并发;

/**
 * @author www.yanzhongxin.com
 * @date 2020/11/1 14:53
 */
public class ThreadLocalDemo {
    static ThreadLocal<Person> threadLocal=new ThreadLocal<>();
    public static void main(String[] args)  throws Exception {
/*
        new Thread(()->{
            try {
                Thread.sleep(2000);
            }catch (Exception e){

            }
            System.out.println("t1 get "+threadLocal.get());

        }).start();*/

       /* new Thread(()->{
            try {
                Thread.sleep(500);
            }catch (Exception e){

            }
            Person p=new Person();
            ///threadLocal.set();
            System.out.println("t2 put "+p);
        }).start();*/

        System.in.read();
    }

}
