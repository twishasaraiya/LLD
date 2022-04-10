package consumer;

import consumer.IConsumer;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicConsumer {
    private AtomicInteger offSet;
    private IConsumer consumer;

    public TopicConsumer(IConsumer consumer) {
        this.offSet = new AtomicInteger(0);
        this.consumer = consumer;
    }

    public AtomicInteger getOffSet() {
        return offSet;
    }

    public IConsumer getConsumer() {
        return consumer;
    }
}
