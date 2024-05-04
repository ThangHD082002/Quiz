package quiz.com.quizservice.aggregate;

import java.time.LocalDateTime;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import quiz.com.quizservice.command.command.CreateQuizCommand;
import quiz.com.quizservice.command.event.QuizCreateEvent;
import quiz.com.quizservice.command.model.QuestionRequestModel;

@Aggregate
public class QuizAggregate {
    @AggregateIdentifier
    private String idIndentify;

    private String title;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeEnd;
    private Boolean state;
    private List<QuestionRequestModel> listQuestion;


    @CommandHandler
    public QuizAggregate(CreateQuizCommand createQuizCommand){
        QuizCreateEvent quizCreateEvent = new QuizCreateEvent();
        BeanUtils.copyProperties(createQuizCommand, quizCreateEvent);
        AggregateLifecycle.apply(quizCreateEvent);
    }

    @EventSourcingHandler
    public void on(QuizCreateEvent event){
        this.idIndentify = event.getIdentify();
        this.title = event.getTitle();
        this.state = event.getState();
        this.timeStart = event.getTimeStart();
        this.timeEnd = event.getTimeEnd();
        this.listQuestion = event.getListQuestion();
    }


}