package model;

import consumer.TopicConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Topic {
    private String id;
    private String name;
    private List<TopicConsumer> topicConsumers;
    private List<Message> messages;
//    private ArrayBlockingQueue<Message> messageQueue;
    /*
    If we use blocking queue then addMessage() method need not to be synchronized,
    but we can't access messages in O(1) time
    */

    public Topic(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.topicConsumers = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TopicConsumer> getConsumers() {
        return List.copyOf(topicConsumers); // Immutable
    }

    public List<Message> getMessages(){
        return List.copyOf(messages);
    }

    public void subscribe(TopicConsumer topicConsumer){
        topicConsumers.add(topicConsumer);
    }

    public synchronized void addMessage(Message message){
        messages.add(message);
    }
}
