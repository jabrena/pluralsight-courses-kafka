import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class KafkaConsumerApp2 {

    public static final Logger logger = LoggerFactory.getLogger(KafkaConsumerApp2.class);

    public static void main(String[] args) {

        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test2");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        int counter = 0;
        try (consumer) {

            String topic = Topics.MY_TOPIC.getTopic();
            ArrayList<String> topics = new ArrayList<>();
            topics.add(topic);

            consumer.subscribe(topics);
            while (true) {

                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    counter++;
                    System.out.println(String.format("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                }
                System.out.println(counter);
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }

}