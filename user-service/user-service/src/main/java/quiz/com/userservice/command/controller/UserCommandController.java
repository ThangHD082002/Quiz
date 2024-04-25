package quiz.com.userservice.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quiz.com.userservice.command.command.CreateUserCommand;
import quiz.com.userservice.command.command.DeleteUserCommand;
import quiz.com.userservice.command.command.UpdateUserCommand;
import quiz.com.userservice.command.model.UserRequestModel;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/user")
public class UserCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("/create")
    public String addUser(@RequestBody UserRequestModel model) {
        CreateUserCommand command = new CreateUserCommand(UUID.randomUUID().toString(), model.getName(), model.getAddress());
        commandGateway.sendAndWait(command);
        return "added User";
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody UserRequestModel model) {
        UpdateUserCommand command = new UpdateUserCommand(model.getUserId(), model.getName(), model.getAddress());
        commandGateway.sendAndWait(command);
        return "updated User";
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable String userId) {
        commandGateway.sendAndWait(new DeleteUserCommand(userId));
        return "deleted User";
    }
    
    
}
