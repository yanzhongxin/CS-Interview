package 高并发;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author www.yanzhongxin.com
 * @date 2020/11/8 15:07
 */
public class AtomicReferenceTest {
    private static AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class,"old");
    public static void main(String[] args) {
        // 设置柯南的年龄是10岁
        User conan = new User("conan",10);
        // 柯南长了一岁，但是仍然会输出旧的年龄
        System.out.println(a.getAndIncrement(conan));//原子性更新conan对象的，old年龄属性。
        // 输出柯南现在的年龄
        System.out.println(a.get(conan));//get获取新数值
    }
    public static class User {
        private String name;
        public volatile int old;//待原子性更新的字段
        public User(String name,int old) {
            this.name = name;
            this.old = old;
        }
        public String getName() {
            return name;
        }
        public int getOld() {
            return old;
        }
    }
}