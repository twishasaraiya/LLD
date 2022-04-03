package startup;

import consumer.Consumer;
import consumer.IConsumer;
import model.Message;
import model.Publisher;
import service.Queue;
import model.Topic;
import service.TopicManager;

public class ApplicationDemo {
    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue();

        Topic topic1 = queue.createTopic("topic-1");
        Topic topic2 = queue.createTopic("topic-2");

        IConsumer consumer1 = new Consumer("consumer-1");
        IConsumer consumer2 = new Consumer("consumer-2");
        IConsumer consumer3 = new Consumer("consumer-3");
        IConsumer consumer4 = new Consumer("consumer-4");
        IConsumer consumer5 = new Consumer("consumer-5");

        queue.subscribe(consumer1, topic1);
        queue.subscribe(consumer2, topic1);
        queue.subscribe(consumer3, topic1);
        queue.subscribe(consumer4, topic1);
        queue.subscribe(consumer5, topic1);

        queue.subscribe(consumer1, topic2);
        queue.subscribe(consumer3, topic2);
        queue.subscribe(consumer4, topic2);

        for(int i=1; i<=10; i++){
            Message message = new Message("msg-"+i);
            if(i%5 == 0){
                queue.publish(topic1, message);
            }
            else{
                queue.publish(topic2, message);
            }
            Thread.sleep(800);

        }

    }
}
