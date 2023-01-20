package com.decta.mockapi.controller;

import com.decta.mockapi.model.Data;
import com.decta.mockapi.service.ExternalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(path = "/api/external", produces = "application/json")
public class ExternalController extends AbstractController {
    private static final String CONFIG_URL = "/api/external/enrolment/config";
    private final ExternalService externalService;
    private static final String PAN = "pan";

    public ExternalController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @PostMapping("/enrolment")
    ResponseEntity<Object> getExternal(@RequestBody Map<String, Object> request) {
        return getPostResponse(PAN, request, externalService, "/api/external/enrolment");
    }

    @GetMapping("/enrolment/config/{id}")
    ResponseEntity<Object> getById(@PathVariable("id") String id) {
        return getByIdResponse(id, externalService, CONFIG_URL + "/");
    }

    @GetMapping("/enrolment/config")
    ResponseEntity<Object> getAll() {
        return getAllResponse(externalService, CONFIG_URL);
    }

    @PutMapping("/enrolment/config/{id}")
    ResponseEntity<Object> addData(@PathVariable String id, @RequestBody Data request) {
        return addDataResponse(id, request, externalService, CONFIG_URL + "/");
    }

    @DeleteMapping("/enrolment/config/{id}")
    ResponseEntity<Object> delete(@PathVariable String id) {
        return deleteResponse(id, externalService, CONFIG_URL + "/");
    }

}
