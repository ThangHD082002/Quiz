package quiz.example.notificationservice.socket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private final SimpMessagingTemplate messagingTemplate;

    public Producer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessageToFailedTopic(String message) {
        messagingTemplate.convertAndSend("/faildTopic", message);
    }

    public void sendMessageToSuccessTopic(String message) {
        messagingTemplate.convertAndSend("/successTopic", message);
    }
}
