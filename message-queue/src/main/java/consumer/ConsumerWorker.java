package consumer;

import model.Message;
import model.Topic;

public class ConsumerWorker implements Runnable {
    private final Topic topic;
    private final TopicConsumer topicConsumer;

    public ConsumerWorker(Topic topic, TopicConsumer topicConsumer) {
        this.topic = topic;
        this.topicConsumer = topicConsumer;
    }

    @Override
    public void run() {
        synchronized (topicConsumer){
            do{
                int consumerOffset = topicConsumer.getOffSet().get();
                while(consumerOffset >= topic.getMessages().size()){
                    try {
                        topicConsumer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message message = topic.getMessages().get(consumerOffset);
                try {
                    topicConsumer.getConsumer().consumeMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                topicConsumer.getOffSet().compareAndSet(consumerOffset, consumerOffset+1);
            }while (true);
        }
    }

    synchronized public void notifyWorker(){
        synchronized (topicConsumer){
            topicConsumer.notify();
        }
    }
}
