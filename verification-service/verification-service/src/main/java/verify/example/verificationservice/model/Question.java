package verify.example.verificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String titleQuestion;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
}
