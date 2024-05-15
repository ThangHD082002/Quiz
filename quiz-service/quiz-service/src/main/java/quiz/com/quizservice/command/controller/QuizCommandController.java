package quiz.com.quizservice.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quiz.com.quizservice.command.command.CreateQuizCommand;
import quiz.com.quizservice.command.model.QuizRequestModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/quiz")
public class QuizCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizRequestModel model) {
        CreateQuizCommand command = new CreateQuizCommand(
                                                        UUID.randomUUID().toString(),     
                                                        model.getTitle(), 
                                                        model.getTimeStart(),
                                                        model.getTimeEnd(),
                                                        model.getState(),
                                                        model.getListQuestion()
                                                        );
        commandGateway.sendAndWait(command);
        return ResponseEntity.ok("added Quizz");
    }


    @PostMapping("/quiz-excel-import")
    public String importQuiz(@RequestBody QuizRequestModel model) {
        CreateQuizCommand command = new CreateQuizCommand(
                                                        UUID.randomUUID().toString(),     
                                                        model.getTitle(), 
                                                        model.getTimeStart(),
                                                        model.getTimeEnd(),
                                                        model.getState(),
                                                        model.getListQuestion()
                                                        );
        commandGateway.sendAndWait(command);
        return "added Quizz";
    }

    @GetMapping("/hello-quiz")
    public ResponseEntity<String> getMethodName() {
        return new ResponseEntity<>("hello quiz from initial quizz", HttpStatus.OK);
    }


    
}