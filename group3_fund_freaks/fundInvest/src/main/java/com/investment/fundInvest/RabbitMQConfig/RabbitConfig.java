package com.investment.fundInvest.RabbitMQConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig<ConnectionFactory> {
    private String exchange_name="idea_exchange";
    private String queue_name="idea_queue";
    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(exchange_name) ;
    }
    @Bean
    public Queue ideaQueue()
    {
        return new Queue(queue_name);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemp=new RabbitTemplate(connectionFactory);
        rabbitTemp.setMessageConverter(producerJackson2MessageConvert());
        return rabbitTemp;
    }
    @Bean
    public Binding bindExcWithQue()
    {
        return  BindingBuilder.bind(ideaQueue()).to(directExchange()).with("idea_route");
    }
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConvert()
    {
        return new Jackson2JsonMessageConverter();
    }
    private String authExchange="auth_exchange";
    private String invQueue="investor_auth_queue";

    @Bean
    public Queue invQueue(){ return new Queue(invQueue,true);}
    @Bean
    public Binding bindExcWithQue2()
    {
        return  BindingBuilder.bind(invQueue()).to(directExchange()).with("investor_auth_route");
    }
    private String interest="interest_queue";
    @Bean
    public Queue interest(){return new Queue(interest,true);}
    @Bean
    public Binding bindExcWithQue3(DirectExchange exchange,Queue interest)
    {
        return BindingBuilder.bind(interest()).to(exchange).with("interest_route");
    }

//    private String startups="startup_queue";
//    @Bean
//    public Queue startups(){return new Queue(startups,true);}
//    @Bean
//    public Binding bindExcWithQue4()
//    {
//        return  BindingBuilder.bind(startups()).to(directExchange()).with("startup_route");
//    }
}
