package quizz.example.initialquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class NotificationExcelService {
     private final String NOTIFICATION_SERVICE_URL = "http://localhost:4003/api/v1/kafka";

    private final RestTemplate restTemplate;

    @Autowired
    public NotificationExcelService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> sendSuccessNotification(String message) {
        String url = NOTIFICATION_SERVICE_URL + "/send-success?message=" + message;
        // ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                null,
                String.class);
        return responseEntity;
    }

    public ResponseEntity<String> sendFailNotification(String message) {
        String url = NOTIFICATION_SERVICE_URL + "/send-fail?message=" + message;
        // ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                null,
                String.class);
        return responseEntity;



        // return ResponseEntity.ok().build();
    }
}
