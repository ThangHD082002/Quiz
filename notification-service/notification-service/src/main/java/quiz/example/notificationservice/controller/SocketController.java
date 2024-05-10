package quiz.example.notificationservice.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import quiz.example.notificationservice.socket.Producer;


@RestController
@RequestMapping("/api/v1/socket")
public class SocketController {
     private final Producer producer;

    public SocketController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping("/send-faild")
    public ResponseEntity<String> sendToFailedTopic(@RequestParam("message") String message) {
        try {
            String id = UUID.randomUUID().toString();
            producer.sendMessageToFailedTopic(message + " ( " + id  +" ) ");
            return ResponseEntity.ok("Message sent to success topic");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message to success topic");
        }
    }

    @PostMapping("/send-success")
    public ResponseEntity<String> sendToSuccessTopic(@RequestParam("message") String message) {
        try {
            String id = UUID.randomUUID().toString();
            producer.sendMessageToSuccessTopic(message + " ( " + id  +" ) ");
            return ResponseEntity.ok("Message sent to success topic");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message to success topic");
        }
    }
}
