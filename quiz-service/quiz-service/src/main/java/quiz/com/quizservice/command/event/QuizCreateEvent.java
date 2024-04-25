package quiz.com.quizservice.command.event;

import java.time.LocalDateTime;
import java.util.List;

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
public class QuizCreateEvent {
    private int id;
    private String title;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeEnd;
    private Boolean state;
    private List<QuestionRequestModel> listQuestion;
}
