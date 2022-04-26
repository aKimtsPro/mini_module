package bstorm.akimts.luc.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean("publish")
    public Queue marieQueue(@Value("${rabbitmq.publish-to}")String queueName){
        return new Queue(queueName, false);
    }

    @Bean
    public TopicExchange MessageExchange(@Value("${rabbitmq.exchange-name}") String exchangeName){
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue q, TopicExchange e){
        return BindingBuilder.bind(q).to(e).with("marie");
    }


}
