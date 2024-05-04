package quizz.example.initialquiz.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int rightAnswer;
}
