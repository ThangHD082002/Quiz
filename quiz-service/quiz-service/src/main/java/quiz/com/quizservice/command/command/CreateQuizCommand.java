package quiz.com.quizservice.command.command;

import java.time.LocalDateTime;
import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import quiz.com.quizservice.command.model.QuestionRequestModel;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuizCommand {
    @TargetAggregateIdentifier
    private String identify;
    private String title;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeEnd;
    private boolean state;
    private List<QuestionRequestModel> listQuestion;
}