package quiz.com.userservice.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import quiz.com.userservice.command.command.CreateUserCommand;
import quiz.com.userservice.command.command.DeleteUserCommand;
import quiz.com.userservice.command.command.UpdateUserCommand;
import quiz.com.userservice.command.event.UserCreateEvent;
import quiz.com.userservice.command.event.UserDeleteEvent;
import quiz.com.userservice.command.event.UserUpdateEvent;

@Aggregate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAggregate {


    @AggregateIdentifier
    private String userId;
    private String name;
    private String address;

    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand){
        UserCreateEvent userCreateEvent = new UserCreateEvent();
        BeanUtils.copyProperties(createUserCommand, userCreateEvent);
        AggregateLifecycle.apply(userCreateEvent);
    }

    @CommandHandler
    public void handle(UpdateUserCommand updateUserCommand){
        UserUpdateEvent userUpdateEvent = new UserUpdateEvent();
        BeanUtils.copyProperties(updateUserCommand, userUpdateEvent);
        AggregateLifecycle.apply(userUpdateEvent);
    }

    @CommandHandler
    public void handle(DeleteUserCommand deleteUserCommand){
        UserDeleteEvent userDeleteEvent = new UserDeleteEvent();
        BeanUtils.copyProperties(deleteUserCommand, userDeleteEvent);
        AggregateLifecycle.apply(userDeleteEvent);
    }

    @EventSourcingHandler
    public void on(UserCreateEvent event){
        this.userId = event.getUserId();
        this.name = event.getName();
        this.address = event.getAddress();
        
    }

    @EventSourcingHandler
    public void on(UserUpdateEvent event){
        this.userId = event.getUserId();
        this.name = event.getName();
        this.address = event.getAddress();
        
    }

    @EventSourcingHandler
    public void on(UserDeleteEvent event){
        this.userId = event.getUserId();
    }




}
