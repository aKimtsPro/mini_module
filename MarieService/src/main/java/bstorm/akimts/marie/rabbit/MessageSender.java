package bstorm.akimts.marie.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageSender implements InitializingBean {

    private final RabbitTemplate template;
    private final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    public MessageSender(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        send("salut luc");
    }

    public void send(String message){
        String exchange = "direct.messages";
        String key = "luc";
        logger.info("RABBIT SEND - " + exchange + "->" + key + " : " + message );
        template.convertAndSend(exchange, key, message);
    }
}
