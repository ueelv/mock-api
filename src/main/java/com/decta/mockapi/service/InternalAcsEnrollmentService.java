package com.decta.mockapi.service;

import com.decta.mockapi.model.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InternalAcsEnrollmentService extends AbstractDataService {

    private static final Map<Object, Data> database = new HashMap<>();

    protected InternalAcsEnrollmentService() {
        super(database);
    }

    @Override
    public Object replace(Object response, Map<String, Object> request) {
        String tempData = (String) response;

        if (request.containsKey("acsTransID")) {
            tempData = tempData.replace("@acsTransID", request.get("acsTransID").toString());
        }

        if (request.containsKey("dsTransID")) {
            tempData = tempData.replace("@dsTransID", request.get("dsTransID").toString());
        }

        if (request.containsKey("threeDSServerTransID")) {
            tempData = tempData.replace("@threeDSServerTransID", request.get("threeDSServerTransID").toString());
        }

        return tempData;
    }
}
