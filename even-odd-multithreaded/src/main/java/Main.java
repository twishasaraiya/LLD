import lombok.SneakyThrows;


public class Main {
    public static void main(String[] args) {
        State state = new State();
        Thread even = new Thread(new Runner(true, state));
        Thread odd = new Thread(new Runner(false, state));

        even.start();
        odd.start();
    }
}

class State{

    int count;
    boolean printEven;

    public State() {
        this.count = 0;
        this.printEven = true;
    }

    public void increment(){
        synchronized (this){
//            System.out.println(Thread.currentThread().getName() + "  increment count");
            this.count++;
            this.printEven = !this.printEven;
        }
    }
}

class Runner implements Runnable{

    private boolean isEven;
    private State state;

    public Runner(boolean isEven, State state) {
        this.isEven = isEven;
        this.state = state;
    }

    @SneakyThrows
    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName() + " enters run");
        while(state.count < 10) {
//            System.out.println(Thread.currentThread().getName() + " tries to lock state");
            synchronized (state) {
//                System.out.println(Thread.currentThread().getName() + " isEven = " + this.isEven + " state = " + state.printEven);
                while (this.isEven != state.printEven) {
                    try {
//                        System.out.println(Thread.currentThread().getName() + " going to wait");
                        state.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " = " + state.count);
                state.increment();
                state.notifyAll();
            }
        }
    }
}


