import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerApp {

    public static final Logger logger = LoggerFactory.getLogger(KafkaProducerApp.class);

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> myProducer = new KafkaProducer<>(props);

        String topic = Topics.MY_TOPIC.getTopic();

        try (myProducer) {

            for (int i = 0; i < 150; i++) {
                String key = Integer.toString(i);
                String value = "My Message " + Integer.toString(i);
                ProducerRecord<String, String> myRecord = new ProducerRecord<>(topic, key, value);
                myProducer.send(myRecord).get();
            }

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }

}