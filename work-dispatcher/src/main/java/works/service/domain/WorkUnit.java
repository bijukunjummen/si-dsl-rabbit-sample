package works.service.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkUnit {

    private final String id;
    private final String definition;

    @JsonCreator
    public WorkUnit(@JsonProperty("id") String id,
                    @JsonProperty("definition") String definition) {
        this.id = id;
        this.definition = definition;
    }

    public String getId() {
        return id;
    }


    public String getDefinition() {
        return definition;
    }

}
