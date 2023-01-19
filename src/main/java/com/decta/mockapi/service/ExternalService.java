package com.decta.mockapi.service;

import com.decta.mockapi.model.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.decta.mockapi.utils.UId.getUUID;

@Service
public class ExternalService extends AbstractDataService {

    private static final Map<String, Data> database = new HashMap<>();

    public ExternalService() {
        super(database);
    }

    @Override
    public Optional<Data> getById(String id) {
        Data dataFromDB = database.get(id);
        Data data = new Data();
        if (dataFromDB != null) {
            data.setHttpResponseCode(dataFromDB.getHttpResponseCode());
            data.setResponse(replace(dataFromDB.getResponse()));
            return Optional.of(data);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Data> getById(String id, Boolean replace) {
        Data dataFromDB = database.get(id);
        Data data = new Data();
        if (dataFromDB != null) {
            data.setHttpResponseCode(dataFromDB.getHttpResponseCode());
            if (replace) {
                data.setResponse(replace(dataFromDB.getResponse()));
            } else {
                data.setResponse(dataFromDB.getResponse());
            }
            return Optional.of(data);
        } else {
            return Optional.empty();
        }

    }

    public Object replace(Object response) {
        String tempData = (String) response;
        return tempData.replace("@state", getUUID());
    }

}
