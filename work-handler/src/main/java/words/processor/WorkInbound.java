package words.processor;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.support.Transformers;
import words.processor.domain.WorkUnit;

@Configuration
public class WorkInbound {

    @Autowired
    private RabbitConfig rabbitConfig;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public IntegrationFlow inboundFlow() {
        return IntegrationFlows.from(
                Amqp.inboundAdapter(connectionFactory, rabbitConfig.worksQueue()).concurrentConsumers(3))
                .transform(Transformers.fromJson(WorkUnit.class))
                .handle("workHandler", "process")
                .get();
    }
}
