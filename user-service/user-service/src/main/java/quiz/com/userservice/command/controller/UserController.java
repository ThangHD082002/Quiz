package quiz.com.userservice.command.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @GetMapping("/say")
    public String getMethodName() {
        return "Hello User service";
    }
    

    

}
