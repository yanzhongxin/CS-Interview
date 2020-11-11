import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

public class test {
    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        test测试内存分配(unsafe);

    }

    public static void unsafe操作数组(Unsafe unsafe){
        String[] strings = new String[]{"1","2","3"};
        long i = unsafe.arrayBaseOffset(String[].class);
        System.out.println("string[] base offset is :"+i);
//every index scale
        long scale = unsafe.arrayIndexScale(String[].class);
        System.out.println("string[] index scale is "+scale);
//print first string in strings[]
        System.out.println("first element is :"+unsafe.getObject(strings, i));
//set 100 to first string
        unsafe.putObject(strings,i+scale*0,"100");
//print first string in strings[] again
        System.out.println("after set ,first element is :"+unsafe.getObject(strings, i+scale*0));
    }

    public static void unsafe直接分配对象操作对象(Unsafe unsafe){
        try {
            User user = (User) unsafe.allocateInstance(User.class); //根据class直接分配对象
            //simple set username
            user.setUsername("123");
            System.out.println("simple set,allocate Intstance user name："+user.getUsername());
            Field usernameField = User.class.getDeclaredField("username");
            long objectFieldOffset = unsafe.objectFieldOffset(usernameField);
            //use unsafe set
            unsafe.putObject(user,objectFieldOffset,"456");
            System.out.println("use unsafe,allocate Intstance user name："+user.getUsername());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void test测试内存分配(Unsafe unsafe){
        //分配一个8byte的内存
        long address = unsafe.allocateMemory(8L);
//初始化内存填充0
        unsafe.setMemory(address,8L,(byte) 0);
//测试输出
        System.out.println("add byte to memory:"+unsafe.getInt(address));
//设置0-3 4个byte为0x7fffffff
        unsafe.putInt(address,0x7fffffff);
//设置4-7 4个byte为0x80000000
        unsafe.putInt(address+4,0x80000000);
//int占用4byte
        System.out.println("add byte to memory:0-3个字节"+unsafe.getInt(address));
        System.out.println("add byte to memory:4-7个字节"+unsafe.getInt(address+4));
    }




}
 class User {
    private String username;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}