package bstorm.akimts.marie.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReciever {

    @RabbitListener(queues = "message_marie")
    public void recieve(String message){
        System.out.println("> Recieved -----------------");
        System.out.println(message);
        System.out.println("----------------------------");
    }
}
