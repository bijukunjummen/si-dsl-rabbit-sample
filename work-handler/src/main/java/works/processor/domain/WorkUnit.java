package works.processor.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkUnit {

    private final String id;
    private final String definition;

    //to simulate a case where the service rejects a workunit..
    private final boolean throwException;

    @JsonCreator
    public WorkUnit(@JsonProperty("id") String id,
                    @JsonProperty("definition") String definition,
                    @JsonProperty("throw_exception") boolean throwException) {
        this.id = id;
        this.definition = definition;
        this.throwException = throwException;
    }

    public String getId() {
        return id;
    }

    public String getDefinition() {
        return definition;
    }

    @JsonProperty("throw_exception")
    public boolean isThrowException() {
        return throwException;
    }
}