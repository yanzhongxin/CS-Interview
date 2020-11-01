public class test {
    public static void main(String[] args) throws Exception {

        synchronized (test.class){
            System.out.println("test");
            test.class.wait();
        }
    }


}
