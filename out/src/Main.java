public class Main {
    public static void main(String[] args) {
        Main.syn();
    }

    public static  synchronized  void  syn(){
        System.out.println("test");
    }

    public static void printOjbectHead(){
        Object o=new Object();
        System.out.println(ClassL.parseInstance(o).toPrintable());
    }
}
