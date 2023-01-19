package com.decta.mockapi.service;

import com.decta.mockapi.model.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDataService implements DataService {
    private final Map<String, Data> database;


    protected AbstractDataService(Map<String, Data> database) {
        this.database = database;
    }

    public List<Data> getAll() {
        List<Data> dataList = new ArrayList<>();
        for (String key : database.keySet()) {
            dataList.add(database.get(key));
        }
        return dataList;
    }

    public Optional<Data> getById(String id) {
        return Optional.ofNullable(database.get(id));
    }

    public Optional<Data> addById(String id, Data data){
        database.put(id,data);
        return Optional.ofNullable(database.get(id));
    }

    public void deleteById(String id){
        database.remove(id);
    }
}
