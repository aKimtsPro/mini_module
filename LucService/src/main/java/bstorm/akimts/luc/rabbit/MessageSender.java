package bstorm.akimts.luc.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;

@Component
public class MessageSender implements InitializingBean {

    private final RabbitTemplate template;

    public MessageSender(RabbitTemplate template) {
        this.template = template;
    }

    public void send(Object message){
        System.out.println("sending : " + message);
        template.convertAndSend("message_marie", message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        send("salut");
    }
}
