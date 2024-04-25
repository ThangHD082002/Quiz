package quizz.example.initialquiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/initial-quiz")
public class InitialQuiz {
    @GetMapping("/import-quiz")
    public String getMethodName(@RequestParam String entity) {
        
    }

    @PostMapping("/create-quiz")
    public String postMethodName(@RequestBody String entity) {

    }
    
    
}
