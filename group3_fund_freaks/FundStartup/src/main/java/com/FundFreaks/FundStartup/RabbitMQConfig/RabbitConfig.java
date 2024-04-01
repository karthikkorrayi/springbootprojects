package com.FundFreaks.FundStartup.RabbitMQConfig;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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
    private String exchangeName="idea_exchange";
    private String ideaQueue="idea_queue";

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue ideaQueue()
    {
        return new Queue(ideaQueue,true);
    }
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(){
        return new  Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(final org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemp=new RabbitTemplate(connectionFactory);
        rabbitTemp.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemp;
    }
    @Bean
    Binding bindingUser(DirectExchange exchange1,Queue ideaQueue)
    {
        return BindingBuilder.bind(ideaQueue()).to(exchange1).with("idea_route");
    }

    private String authExchange="auth_exchange";
    private String startQueue="startup_auth_queue";
    @Bean
    Queue startQueue(){ return new Queue(startQueue,true);}
    @Bean
    public Binding bindExcWithQue()
    {
        return  BindingBuilder.bind(startQueue()).to(directExchange()).with("startup_auth_route");
    }

    private String interest="interest_queue";
    @Bean
    public Queue interest(){return new Queue(interest,true);}
    @Bean
    public Binding bindExcWithQue3()
    {
        return BindingBuilder.bind(interest()).to(directExchange()).with("interest_route");
    }
//    private String startups="startup_queue";
//    @Bean
//    public Queue startups(){return new Queue(startups,true);}
//    @Bean
//    Binding bindingUser2(DirectExchange exchange1,Queue ideaQueue)
//    {
//        return BindingBuilder.bind(startups()).to(exchange1).with("startup_route");
//    }
}
