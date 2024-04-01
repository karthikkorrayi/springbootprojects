package com.auth.FundAuth.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig<ConnectionFactory> {
    private String exchangeName="auth_exchange";
    private String startQueue="startup_auth_queue";
    private String invQueue="investor_auth_queue";
    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Queue startQueue()
    {
        return new Queue(startQueue,true);
    }

    @Bean
    Binding bindingUser(DirectExchange exchange,Queue startQueue)
    {
        return BindingBuilder.bind(startQueue()).to(exchange).with("startup_auth_route");
    }

    @Bean
    public Queue invQueue(){ return new Queue(invQueue,true);}
    @Bean
    Binding bindingUser2(DirectExchange exchange,Queue invQueue)
    {
        return BindingBuilder.bind(invQueue()).to(exchange).with("investor_auth_route");
    }





    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter()    {
        return new  Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemp=new RabbitTemplate(connectionFactory);
        rabbitTemp.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemp;
    }
}
