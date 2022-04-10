package consumer;

import model.Message;
import model.Topic;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerNotifier {

    private final Topic topic;

    public ConsumerNotifier(Topic topic) {
        this.topic = topic;
    }

    public void publish(Message message){
        List<TopicConsumer> consumers = topic.getConsumers();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(consumers.size()); //

        for (TopicConsumer consumer: consumers) {
            fixedThreadPool.execute(new MessageConsumer(consumer, message));
        }
    }
}
