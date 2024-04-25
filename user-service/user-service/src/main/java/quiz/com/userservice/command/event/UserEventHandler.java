package quiz.com.userservice.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import quiz.com.userservice.command.data.User;
import quiz.com.userservice.command.data.UserRepository;

@Component
public class UserEventHandler {
    @Autowired
    private UserRepository userRepository;
    
    @EventHandler
    public void on(UserCreateEvent event){
        User user = new User();
        BeanUtils.copyProperties(event, user);
        userRepository.save(user);
    }

    @EventHandler
    public void on(UserUpdateEvent event){
        @SuppressWarnings("deprecation")
        User user = userRepository.getById(event.getUserId());
        user.setName(event.getName());
        user.setAddress(event.getAddress());
        userRepository.save(user);
    }

    @EventHandler
    public void on(UserDeleteEvent event){
        userRepository.deleteById(event.getUserId());
    }
}
