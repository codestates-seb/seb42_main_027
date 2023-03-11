package ynzmz.server.helper;

import org.springframework.restdocs.operation.Operation;
import org.springframework.restdocs.operation.OperationRequest;
import org.springframework.restdocs.operation.OperationResponse;

import java.util.Map;

public class MyOperation implements Operation {
    private final StringBuilder descriptionBuilder = new StringBuilder();
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
    @Override
    public String getName() {
        return null;
    }

    @Override
    public OperationRequest getRequest() {
        return null;
    }
    public StringBuilder getDescription() {
        return descriptionBuilder;
    }

    @Override
    public OperationResponse getResponse() {
        return null;
    }
}
