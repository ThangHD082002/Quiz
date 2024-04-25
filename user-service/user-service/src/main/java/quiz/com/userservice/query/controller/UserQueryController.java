package quiz.com.userservice.query.controller;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quiz.com.userservice.query.model.UserRespondModel;
import quiz.com.userservice.query.queries.GetUserQuery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/user")
public class UserQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/get-user/{userId}")
    public UserRespondModel getUserDetail(@PathVariable String userId) {
        GetUserQuery getUserQuery = new GetUserQuery();
        getUserQuery.setUserId(userId);

        UserRespondModel userRespondModel = queryGateway.query(getUserQuery, 
                                                    ResponseTypes.instanceOf(UserRespondModel.class))
                                                    .join();

        return userRespondModel;

    }
    
}
