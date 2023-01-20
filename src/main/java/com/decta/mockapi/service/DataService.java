package com.decta.mockapi.service;

import com.decta.mockapi.model.Data;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DataService {
    List<Data> getAll();

    Optional<Data> getById(Object id, boolean replace, Map<String, Object> request);

    Optional<Data> addById(Object id, Data data);

    void deleteById(Object id);
}
