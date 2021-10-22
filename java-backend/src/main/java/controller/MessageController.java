package controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    // Concrete endpoint capturing requests
    @MessageMapping("chat")
    // Redirecting return value to the given endpoint
    @SendTo("chatWindow")
    public Message redirectMessage(Message message){
        // return object compatible with front
    }

    // Concrete endpoint capturing requests
    @MessageMapping("group")
    // Redirecting return value to the given endpoint
    @SendTo("groupWindow")
    public Message redirectMessage(Message message){
        // return object compatible with front
    }
}
