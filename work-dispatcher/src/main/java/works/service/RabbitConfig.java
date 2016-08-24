package works.service;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Autowired
	private ConnectionFactory rabbitConnectionFactory;

	@Bean
	TopicExchange worksExchange() {
		return new TopicExchange("work.exchange", true, false);
	}

	@Bean
	public RabbitTemplate worksRabbitTemplate() {
		RabbitTemplate r = new RabbitTemplate(rabbitConnectionFactory);
		r.setExchange("work.exchange");
		r.setRoutingKey("work");
		r.setConnectionFactory(rabbitConnectionFactory);
		return r;
	}

}
