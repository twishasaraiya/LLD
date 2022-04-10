package service;

import consumer.ConsumerWorker;
import model.Message;
import model.Topic;
import consumer.TopicConsumer;

import java.util.HashMap;
import java.util.Map;

public class TopicManager {

    private final Topic topic;
    private final Map<String, ConsumerWorker> workerMap;


    public TopicManager(Topic topic) {
        this.topic = topic;
        workerMap = new HashMap<>();
    }

    public void publish(){
        for (TopicConsumer topicConsumer: topic.getConsumers()){
            String consumerId = topicConsumer.getConsumer().getId();
            ConsumerWorker consumerWorker;
            if(!workerMap.containsKey(consumerId)){
                consumerWorker = new ConsumerWorker(topic, topicConsumer);
                workerMap.put(consumerId, consumerWorker);
                new Thread(consumerWorker).start();
            }
            else{
                consumerWorker = workerMap.get(consumerId);
                consumerWorker.notifyWorker();
            }
        }
    }
}
