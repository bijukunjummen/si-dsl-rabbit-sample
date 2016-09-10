package works.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import works.processor.domain.WorkUnit;

@Service
public class WorkHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkHandler.class);

    public void process(WorkUnit workUnit) {
        if (workUnit.isThrowException()) {
            LOGGER.info("Throwing a deliberate exception for work unit - id: {}, definition: {}", workUnit.getId(), workUnit.getDefinition());
            throw new RuntimeException("Throwing a deliberate exception");
        }
        LOGGER.info("Handling work unit - id: {}, definition: {}", workUnit.getId(), workUnit.getDefinition());
    }
}
