package quiz.com.quizservice.command.event;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import quiz.com.quizservice.command.entity.Question;
import quiz.com.quizservice.command.entity.Quiz;
import quiz.com.quizservice.command.model.QuestionRequestModel;
import quiz.com.quizservice.command.repository.QuestionRepository;
import quiz.com.quizservice.command.repository.QuizRepository;

@Component
public class QuizEventHandler {
    @Autowired 
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @EventHandler 
    public void on(QuizCreateEvent event){
        Quiz quiz = new Quiz();
        quiz.setTitle(event.getTitle());
        quiz.setState(event.getState());
        quiz.setTimeStart(event.getTimeStart());
        quiz.setTimeEnd(event.getTimeEnd());
        quizRepository.save(quiz);
        List<Question> listQuestion = new ArrayList<Question>();
        for(QuestionRequestModel qrm : event.getListQuestion()){
            Question question = new Question();
            question.setTitleQuestion(qrm.getTitleQuestion());
            question.setOption1(qrm.getOption1());
            question.setOption2(qrm.getOption2());
            question.setOption3(qrm.getOption3());
            question.setOption4(qrm.getOption4());
            question.setRightAnswer(qrm.getRightAnswer());
            question.setQuiz(quiz);
            questionRepository.save(question);
            listQuestion.add(question);
        }
        quiz.setListQuestion(listQuestion);
        quizRepository.save(quiz);
       
        
    }
}
