package quiz.example.notificationservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic successTopic(){
        return TopicBuilder.name("successTopic")
                .build();
    }

    @Bean
    public NewTopic faildTopic(){
        return TopicBuilder.name("faildTopic")
                .build();
    }

}
