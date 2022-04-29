package bstorm.akimts.luc.controller;

import bstorm.akimts.luc.rabbit.MessageSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageSender sender;
    private final RestTemplate restTemplate;

    public MessageController(MessageSender sender, RestTemplate restTemplate) {
        this.sender = sender;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public void send(@RequestParam String message){
        sender.send(message);
    }

    @GetMapping
    public void askForMessage(@RequestParam String message) {
        ResponseEntity<Object> response = restTemplate.postForEntity("http://marie:8082/message?message=send_this", null, Object.class);
    }

}
