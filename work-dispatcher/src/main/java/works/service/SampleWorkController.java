package works.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import works.service.domain.WorkUnit;

import java.util.UUID;

@Controller
public class SampleWorkController {

    @Autowired
    private WorkUnitGateway workUnitGateway;

    @RequestMapping("/generateWork")
    @ResponseBody
    public WorkUnit generateWork(@RequestParam("definition") String definition) {
        WorkUnit sampleWorkUnit = new WorkUnit(UUID.randomUUID().toString(), definition);
        workUnitGateway.generate(sampleWorkUnit);
        return sampleWorkUnit;
    }
}
