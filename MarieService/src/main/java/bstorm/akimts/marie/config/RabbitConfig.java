package bstorm.akimts.marie.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean("publish")
    public Queue marieQueue(@Value("${rabbitmq.publish-to}")String queueName){
        return new Queue(queueName, true);
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
