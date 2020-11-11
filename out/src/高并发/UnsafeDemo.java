package 高并发;

import sun.nio.ch.DirectBuffer;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author www.yanzhongxin.com
 * @date 2020/11/6 9:46
 */
public class UnsafeDemo {
    public static void main(String[] args) {

        HashMap hashMap=new HashMap();
        hashMap.put("a","b");
        hashMap.get("a");
    }
    public static void testDireceByteBuffer(){
        HashMap hashMap=new HashMap();
        hashMap.put("a","b");
        hashMap.get("a");

    }
}
