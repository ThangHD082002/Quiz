package quiz.com.userservice.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteUserCommand {
    @TargetAggregateIdentifier
    private String userId;

    public DeleteUserCommand(String userId){
        super();
        this.userId = userId;
    }
}
