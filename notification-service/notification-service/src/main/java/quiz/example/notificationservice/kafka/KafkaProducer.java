package quiz.example.notificationservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private KafkaTemplate<String,String> kafkaTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageSuccess(String message){
        LOGGER.info(String.format("Message sent %s", message));
        kafkaTemplate.send("successTopic", message);
    }

    public void sendMessageFaild(String message){
        LOGGER.info(String.format("Message sent %s", message));
        kafkaTemplate.send("faildTopic", message);
    }
}
