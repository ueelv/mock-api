package com.decta.mockapi.service;

import com.decta.mockapi.model.Data;

import java.util.List;
import java.util.Optional;

public interface DataService {
    List<Data> getAll();
    Optional<Data> getById(String id);
    Optional<Data> addById(String id, Data data);
    void deleteById(String id);
}
