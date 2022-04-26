package bstorm.akimts.luc.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessagesListener{

    @RabbitListener(queues = "message_luc")
    public void listen(String message){
        System.out.println(message);
    }

}
