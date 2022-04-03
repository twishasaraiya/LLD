package consumer;

import model.Message;

public class Consumer implements IConsumer{

    private String id;

    public Consumer(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consumeMessage(Message message) throws InterruptedException {
        System.out.println( id + " received " + message.getMessage());
        Thread.sleep(2000);
    }
}
