package quizz.example.initialquiz.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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