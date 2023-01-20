package com.decta.mockapi.service;

import com.decta.mockapi.model.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public abstract class AbstractDataService implements DataService {
    private final Map<Object, Data> database;

    protected AbstractDataService(Map<Object, Data> database) {
        this.database = database;
    }

    public List<Data> getAll() {
        List<Data> dataList = new ArrayList<>();
        for (Map.Entry<Object, Data> entry : database.entrySet()) {
            dataList.add(entry.getValue());
        }
        return dataList;
    }


    public Optional<Data> addById(Object id, Data data) {
        database.put(id, data);
        return Optional.ofNullable(database.get(id));
    }

    public void deleteById(Object id) {
        database.remove(id);
    }

    public Optional<Data> getById(Object id, boolean replace, Map<String, Object> request) {
        Data dataFromDB = database.get(id);
        Data data = new Data();
        if (dataFromDB != null) {
            data.setHttpResponseCode(dataFromDB.getHttpResponseCode());
            if (replace) {
                data.setResponse(replace(dataFromDB.getResponse(), request));
            } else {
                data.setResponse(dataFromDB.getResponse());
            }
            return Optional.of(data);
        } else {
            return Optional.empty();
        }
    }

    public abstract Object replace(Object response, Map<String, Object> request);
}
