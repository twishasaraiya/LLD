public class Demo {
    public static void main(String[] args) {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        Thread one = new Thread(() -> {
           synchronized (resourceA){
               // one has access to resourceA and requires access to resourceB as well
               System.out.println(Thread.currentThread().getName() + " Acquired access to resource A");
               synchronized (resourceB){
                   System.out.println(Thread.currentThread().getName() + " Acquired access to resource B");
               }
           }
        });

        Thread two = new Thread(() -> {
            synchronized (resourceB){
                // one has access to resourceA and requires access to resourceB as well
                System.out.println(Thread.currentThread().getName() + " Acquired access to resource B");
                synchronized (resourceA){
                    System.out.println(Thread.currentThread().getName() + " Acquired access to resource A");
                }
            }
        });

        one.start();
        two.start();
    }
}

class ResourceA{
    private int i;

    public ResourceA() {
        this.i = 10;
    }

    public int getI() {
        return i;
    }
}

class ResourceB{
    private int j;

    public ResourceB(){
        this.j = 20;
    }

    public int getJ() {
        return j;
    }
}
