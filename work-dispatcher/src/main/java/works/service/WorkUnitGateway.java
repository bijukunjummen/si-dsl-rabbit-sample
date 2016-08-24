package works.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import works.service.domain.WorkUnit;

@MessagingGateway
public interface WorkUnitGateway {
	@Gateway(requestChannel = "worksChannel")
	void generate(WorkUnit workUnit);

}
