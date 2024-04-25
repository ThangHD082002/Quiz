package quiz.com.userservice.query.projection;

import java.util.Optional;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import quiz.com.userservice.command.data.User;
import quiz.com.userservice.command.data.UserRepository;
import quiz.com.userservice.query.model.UserRespondModel;
import quiz.com.userservice.query.queries.GetUserQuery;

@Component
public class UserProjection {
    @Autowired
    private UserRepository userRepository;

    @QueryHandler
    public UserRespondModel handle(GetUserQuery getUserQuery){
        UserRespondModel model = new UserRespondModel();
        @SuppressWarnings("deprecation")
        User user = userRepository.getById(getUserQuery.getUserId());
        BeanUtils.copyProperties(user, model);

        return model;
    }
    
}
