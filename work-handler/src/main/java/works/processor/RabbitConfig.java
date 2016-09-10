package works.processor;

import org.aopalliance.aop.Advice;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@Configuration
public class RabbitConfig {

    @Autowired
    private ConnectionFactory rabbitConnectionFactory;


    // Dead letter exchange for holding rejected work units..
    @Bean
    Exchange worksDlExchange() {
        return ExchangeBuilder
                .topicExchange("work.exchange.dl")
                .durable()
                .build();
    }

    //Queue to hold Deadletter messages from worksQueue
    @Bean
    public Queue worksDLQueue() {
        return QueueBuilder
                .durable("works.queue.dl")
                .withArgument("x-message-ttl", 20000)
                .withArgument("x-dead-letter-exchange", worksExchange().getName())
                .build();
    }

    @Bean
    Binding worksDlBinding() {
        return BindingBuilder
                .bind(worksDLQueue())
                .to(worksDlExchange()).with("#")
                .noargs();
    }

    @Bean
    Exchange worksExchange() {
        return ExchangeBuilder.topicExchange("work.exchange")
                .durable()
                .build();
    }


    @Bean
    public Queue worksQueue() {
        return QueueBuilder.durable("work.queue")
                .withArgument("x-dead-letter-exchange", worksDlExchange().getName())
                .build();
    }

    @Bean
    Binding worksBinding() {
        return BindingBuilder
                .bind(worksQueue())
                .to(worksExchange()).with("#").noargs();
    }

    @Bean
    public SimpleMessageListenerContainer workListenerContainer() {
        SimpleMessageListenerContainer container =
                new SimpleMessageListenerContainer(rabbitConnectionFactory);
        container.setQueues(worksQueue());
        container.setConcurrentConsumers(2);
        container.setDefaultRequeueRejected(false);
//        container.setAdviceChain(new Advice[]{interceptor()});
        return container;
    }

    @Bean
    RetryOperationsInterceptor interceptor() {
        return RetryInterceptorBuilder.stateless()
                .maxAttempts(5)
                .backOffOptions(1000, 3, 60000)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }
}
