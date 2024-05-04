package quiz.example.notificationservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import quiz.example.notificationservice.kafka.KafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {
    private KafkaProducer kafkaProducer;



    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    // http:localhost:3009/api/v1/kafka/publish?message=hello world
    // @GetMapping("/publish")
    // public ResponseEntity< String> publish(@RequestParam("message") String message) {
    //     kafkaProducer.sendMessage(message);
    //     return ResponseEntity.ok("Message sented to the topic");

    // }

    @GetMapping("/say")
    public String hello() {
        return "hello notification";
    }

    @PostMapping("/send-success")
    public ResponseEntity<String> sendSuccessNotification(@RequestParam("message") String message) {
        try {
            String id = UUID.randomUUID().toString();
            kafkaProducer.sendMessageSuccess(message + " ( " + id  +" ) ");
            return ResponseEntity.ok("Message sent to success topic");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message to success topic");
        }
    }

    @PostMapping("/send-fail")
    public ResponseEntity<String> sendFailNotification(@RequestParam("message") String message) {
        try {
            String id = UUID.randomUUID().toString();
            kafkaProducer.sendMessageFaild(message + " ( " + id  +" ) ");
            return ResponseEntity.ok("Message sent to fail topic");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message to fail topic");
        }
    }
}
