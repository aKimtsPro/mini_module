package bstorm.akimts.luc.config;

import bstorm.akimts.luc.interceptors.LogInterceptor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate(
                RestTemplateBuilder builder,
                LogInterceptor interceptor
            ){
        return builder
                .setConnectTimeout(Duration.of(6, ChronoUnit.SECONDS))
                .setReadTimeout(Duration.of(6, ChronoUnit.SECONDS))
                .additionalInterceptors(interceptor)
                .build();
    }

//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    @Configuration
    public static class RabbitConfig {

        @Bean("publish")
        public Queue marieQueue(@Value("${rabbitmq.publish-to}") String queueName) {
            return new Queue(queueName, true);
        }

        @Bean("consume")
        public Queue lucQueue(@Value("${rabbitmq.listen-to}") String queueName) {
            return new Queue(queueName);
        }

        @Bean
        public DirectExchange MessageExchange(@Value("${rabbitmq.exchange-name}") String exchangeName) {
            return new DirectExchange(exchangeName);
        }

        @Bean
        public Binding binding(@Qualifier("publish") Queue q, DirectExchange e) {
            return BindingBuilder
                    .bind(q)
                    .to(e)
                    .with("marie");
        }

        @Bean
        public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
            return new RabbitAdmin(connectionFactory);
        }

    }

}
