package quiz.example.notificationservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);


    @KafkaListener(topics="javaguides", groupId="myGroup")
    public void consume(String message){
        LOGGER.info(String.format("Message recieved -> %s", message));
    }
}
