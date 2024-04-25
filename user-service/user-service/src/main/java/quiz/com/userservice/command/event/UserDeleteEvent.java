package quiz.com.userservice.command.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeleteEvent {
    private String userId;
}
