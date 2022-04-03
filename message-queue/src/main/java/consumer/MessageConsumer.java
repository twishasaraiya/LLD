package consumer;

import model.Message;

public class MessageConsumer implements Runnable{

    private final TopicConsumer topicConsumer;
    private final Message message;

    public MessageConsumer(TopicConsumer topicConsumer, Message message) {
        this.topicConsumer = topicConsumer;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            topicConsumer.getConsumer().consumeMessage(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
