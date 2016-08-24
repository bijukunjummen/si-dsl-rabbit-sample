package works.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.support.Transformers;

@Configuration
public class WorksOutbound {

    @Autowired
    private RabbitConfig rabbitConfig;

    @Bean
    public IntegrationFlow toOutboundQueueFlow() {
        return IntegrationFlows.from("worksChannel")
                .transform(Transformers.toJson())
                .handle(Amqp.outboundAdapter(rabbitConfig.worksRabbitTemplate()))
                .get();
    }
}