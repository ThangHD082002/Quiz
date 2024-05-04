package quiz.com.quizservice.command.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import quiz.com.quizservice.command.entity.Quiz;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestModel {
    private String titleQuestion;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
}