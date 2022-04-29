package bstorm.akimts.marie.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean("publish")
    public Queue lucQueue(@Value("${rabbitmq.publish-to}") String queueName) {
        return new Queue(queueName, true);
    }

    @Bean
    public Queue marieQueue(@Value("${rabbitmq.listen-to}") String queueName) {
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange MessageExchange(@Value("${rabbitmq.exchange-name}") String exchangeName) {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding(@Qualifier("publish") Queue q, DirectExchange e) {
        return BindingBuilder.bind(q).to(e).with("luc");
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
