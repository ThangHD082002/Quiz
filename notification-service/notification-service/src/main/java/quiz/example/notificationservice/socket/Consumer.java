package quiz.example.notificationservice.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;

public class Consumer {
    @MessageMapping("/faildTopic")
    public void listenToFailedTopic(String message) {
        System.out.println("Received message from failedTopic: " + message);
        // Handle message received from failedTopic
    }

    @MessageMapping("/successTopic")
    public void listenToSuccessTopic(String message) {
        System.out.println("Received message from successTopic: " + message);
        // Handle message received from successTopic
    }
}
