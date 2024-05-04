package quizz.example.initialquiz.service;

import java.io.IOException;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import quizz.example.initialquiz.request.Question;

@Service
public class VerificationExcelService {
    private final String VERIFICATION_SERVICE_URL = "http://localhost:4005/api/v1/verification";

    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    public VerificationExcelService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> checkExcelFile(MultipartFile file) throws IOException {
        String url = VERIFICATION_SERVICE_URL + "/check-excel";


        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);

        // Tạo HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<String>() {
                });
        return responseEntity;

    }

    public ResponseEntity<String> checkExcelData(MultipartFile file) throws IOException {
        String url = VERIFICATION_SERVICE_URL + "/check-excel-data";
        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);

        // Tạo HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<String>() {
                });
        return responseEntity;

    }

    public ResponseEntity<List<Question>> uploadExcelFile(MultipartFile file) {
        // Tạo đường dẫn URL của service quiz
        String url = VERIFICATION_SERVICE_URL + "/upload-excel"; // Thay đổi URL theo địa chỉ của service quiz

        try {
            // Tạo đối tượng MultiValueMap để truyền file Excel

            // Tạo đối tượng Resource từ MultipartFile
            ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", resource);

            // Tạo HttpHeaders
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Tạo HttpEntity với body và headers
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // Gửi yêu cầu POST tới service quiz và nhận phản hồi
            ResponseEntity<List<Question>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<Question>>() {});

            // Trả về phản hồi từ service quiz
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
