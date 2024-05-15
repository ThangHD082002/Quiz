package quizz.example.initialquiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import quizz.example.initialquiz.request.Question;
import quizz.example.initialquiz.request.QuizRequestModel;

@Service
public class UploadQuizExcelService{

    private final String QUIZ_SERVICE_URL = "http://localhost:4001/api/v1/quiz";

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    public UploadQuizExcelService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> callQuizService() {
        String url = QUIZ_SERVICE_URL + "/hello-quiz";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;
    }


    public ResponseEntity<String> createQuiz(QuizRequestModel model) {
        String url = QUIZ_SERVICE_URL + "/create";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<QuizRequestModel> requestEntity = new HttpEntity<>(model, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        
        return response;
    }

    // public ResponseEntity<List<Question>> uploadExcelFile(MultipartFile file) {
    //     // Tạo đường dẫn URL của service quiz
    //     String quizServiceUrl = "http://localhost:4001/api/v1/quiz/upload-excel"; // Thay đổi URL theo địa chỉ của service quiz

    //     try {
    //         // Tạo đối tượng MultiValueMap để truyền file Excel

    //         // Tạo đối tượng Resource từ MultipartFile
    //         ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
    //             @Override
    //             public String getFilename() {
    //                 return file.getOriginalFilename();
    //             }
    //         };

    //         MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    //         body.add("file", resource);

    //         // Tạo HttpHeaders
    //         HttpHeaders headers = new HttpHeaders();
    //         headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    //         // Tạo HttpEntity với body và headers
    //         HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

    //         // Gửi yêu cầu POST tới service quiz và nhận phản hồi
    //         ResponseEntity<List<Question>> responseEntity = restTemplate.exchange(quizServiceUrl, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Question>>() {});

    //         // Trả về phản hồi từ service quiz
    //         return responseEntity;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    //     }
    // }

    
}
