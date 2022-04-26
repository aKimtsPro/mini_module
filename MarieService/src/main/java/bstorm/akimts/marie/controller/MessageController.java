package bstorm.akimts.marie.controller;

import bstorm.akimts.marie.rabbit.MessageSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageSender sender;

    public MessageController(MessageSender sender) {
        this.sender = sender;
    }

    @PostMapping
    public void send(@RequestParam String message){
        sender.send(message);
    }

}
