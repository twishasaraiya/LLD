package service;

import consumer.ConsumerNotifier;
import consumer.IConsumer;
import consumer.TopicConsumer;
import model.Message;
import model.Topic;

import java.util.HashMap;
import java.util.Map;

public class Queue {
    private final Map<String, TopicManager> topicManagerMap;

    public Queue() {
        this.topicManagerMap = new HashMap<>();
    }

    public Topic createTopic(String topicName){
        Topic topic = new Topic(topicName);
        TopicManager topicManager = new TopicManager(topic);
        topicManagerMap.put(topic.getId(), topicManager);
        System.out.println("Created topic: " + topicName);
        return topic;
    }

    public void subscribe(IConsumer consumer, Topic topic){
        TopicConsumer topicConsumer = new TopicConsumer(consumer);
        topic.subscribe(topicConsumer);
        System.out.println(consumer.getId()  + " subscribed to: " + topic.getName());
    }

    public void publish(Topic topic, Message message){
        System.out.println("Publishing " + message.getMessage() +" to: " + topic.getName());
        topic.addMessage(message);
        topicManagerMap.get(topic.getId()).publish();
//        topic.addMessage(message);
//        ConsumerNotifier notifier = new ConsumerNotifier(topic);
//        notifier.publish(message);
    }
}
