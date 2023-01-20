package com.decta.mockapi.service;

import com.decta.mockapi.model.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.decta.mockapi.utils.UId.getUUID;

@Service
public class ExternalService extends AbstractDataService {

    private static final Map<Object, Data> database = new HashMap<>();

    public ExternalService() {
        super(database);
    }

    @Override
    public Object replace(Object response, Map<String, Object> request) {
        String tempData = (String) response;
        return tempData.replace("@state", getUUID());
    }

}
