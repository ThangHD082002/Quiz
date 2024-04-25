package quiz.com.quizservice.command.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {
    
    @GetMapping("/say")
    public String say() {
        return "Hello quiz";
    }
    
}
