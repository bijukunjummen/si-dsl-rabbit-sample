package words.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import words.service.domain.WorkUnit;

import java.util.UUID;

@Controller
public class SampleWorkController {

    @Autowired
    private WorkUnitGateway workUnitGateway;

    @RequestMapping("/generateWork")
    @ResponseBody
    public String generateWork(@RequestParam("definition") String definition) {
        workUnitGateway.generate(new WorkUnit(UUID.randomUUID().toString(), definition));
        return "generated";
    }
}
