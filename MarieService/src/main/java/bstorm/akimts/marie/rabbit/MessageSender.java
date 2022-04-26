package bstorm.akimts.marie.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MessageSender implements InitializingBean {

    private final RabbitTemplate template;

    public MessageSender(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        send("salut luc");
    }

    public void send(String message){
        template.convertAndSend(message);
    }
}
