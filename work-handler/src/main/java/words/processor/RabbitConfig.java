package words.processor;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Autowired
	private ConnectionFactory rabbitConnectionFactory;

	@Bean
	DirectExchange worksExchange() {
		return new DirectExchange("work.exchange", true, false);
	}

	@Bean
	public Queue worksQueue() {
		return new Queue("work.queue", true);
	}

	@Bean
	Binding rubeExchangeBinding(DirectExchange worksExchange, Queue worksQueue) {
		return BindingBuilder.bind(worksQueue).to(worksExchange).with("work");
	}
}
