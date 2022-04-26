package bstorm.akimts.luc.controller;

import bstorm.akimts.luc.rabbit.MessageSender;
import org.springframework.web.bind.annotation.*;

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
