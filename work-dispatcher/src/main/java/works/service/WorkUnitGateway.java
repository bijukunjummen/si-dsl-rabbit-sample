package words.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import words.service.domain.WorkUnit;

@MessagingGateway
public interface WorkUnitGateway {
	@Gateway(requestChannel = "worksChannel")
	void generate(WorkUnit workUnit);

}
