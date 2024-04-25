package quiz.com.quizservice.command.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quiz.com.quizservice.command.command.CreateQuizCommand;
import quiz.com.quizservice.command.model.QuizRequestModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/quiz")
public class QuizCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("/create")
    public String createQuiz(@RequestBody QuizRequestModel model) {
        CreateQuizCommand command = new CreateQuizCommand(model.getId(),
                                                        model.getTitle(), 
                                                        model.getTimeStart(),
                                                        model.getTimeEnd(),
                                                        model.getState(),
                                                        model.getListQuestion()
                                                        );
        commandGateway.sendAndWait(command);
        return "added Quizz";
    }
}
