package consumer;

import model.Message;

public interface IConsumer {

    String getId();
    void consumeMessage(Message message) throws InterruptedException;
}
