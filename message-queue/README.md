## Distributed Message Queue | Kafka

### Problem Link
https://workat.tech/machine-coding/practice/design-distributed-queue-cuudq0sk0v14

### Solution Link
https://www.youtube.com/watch?v=4BEzgPlLKTo

### Requirements
- The queue should be in-memory and should not require access to the file system.
- There can be multiple topics in the queue.
- A (string) message can be published on a topic by a producer/publisher and consumers/subscribers can subscribe to the topic to receive the messages.
- There can be multiple producers and consumers.
- A producer can publish to multiple topics.
- A consumer can listen to multiple topics.
- The consumer should print "<consumer_id> received <message>" on receiving the message.
- The queue system should be multi-threaded, i.e., messages can be produced or consumed in parallel by different producers/consumers.
### Optional Requirements
- Allow consumer groups. A consumer group can have multiple consumers and consumers mention their consumer group while subscribing to a topic. A message can be consumed by only one consumer per consumer group.

### Input/Output
- You do not need to take input from the command-line.
- Create 2 topics: topic1 and topic2
- Create 2 producers: producer1, and producer2
- Create 5 consumers: consumer1, consumer2, consumer3, consumer4, and consumer5
- Make all 5 consumers subscribe to topic1
- Make consumers 1, 3, and 4 subscribe to topic2
- Make producer1 publish message "Message 1" to topic1
- Make producer1 publish message "Message 2" to topic1
- Make producer2 publish message "Message 3" to topic1
- Make producer1 publish message "Message 4" to topic2
- Make producer2 publish message "Message 5" to topic2


### Entities
- Queue
- Producer
- Consumer