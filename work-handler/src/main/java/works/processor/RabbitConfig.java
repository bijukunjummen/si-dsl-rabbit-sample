package works.processor;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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
	public Queue worksQueue() {
		return new Queue("work.queue", true);
	}

	@Bean
	Binding rubeExchangeBinding(TopicExchange worksExchange, Queue worksQueue) {
		return BindingBuilder.bind(worksQueue).to(worksExchange).with("#");
	}
}
