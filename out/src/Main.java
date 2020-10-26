public class Main {
    private static final Object obj=new Object();
    public static void main(String[] args) {

    }

    public static void test(){

        synchronized (obj){
            synchronized (obj){
                System.out.println("synchronized");
            }
        }
    }



    public void lockEliminate(){

        synchronized (obj){
            // to do
        }
    }
}
