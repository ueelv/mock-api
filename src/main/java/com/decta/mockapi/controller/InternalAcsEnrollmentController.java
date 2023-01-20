package com.decta.mockapi.controller;

import com.decta.mockapi.model.Data;
import com.decta.mockapi.service.InternalAcsEnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/internal", produces = "application/json")
public class InternalAcsEnrollmentController extends AbstractController {

    private static final String CONFIG_URL = "/api/internal/acs/enrolment/config";
    private final InternalAcsEnrollmentService internalAcsEnrollmentService;
    private static final String ACCT_NUMBER = "acctNumber";

    public InternalAcsEnrollmentController(InternalAcsEnrollmentService internalAcsEnrollmentService) {
        this.internalAcsEnrollmentService = internalAcsEnrollmentService;
    }

    @PostMapping("/acs/enrolment")
    ResponseEntity<Object> getInternalAcsEnrollment(@RequestBody Map<String, Object> request) {
        return getPostResponse(ACCT_NUMBER, request, internalAcsEnrollmentService, "/api/internal/acs/enrolment");
    }

    @GetMapping("/acs/enrolment/config/{id}")
    ResponseEntity<Object> getById(@PathVariable("id") String id) {
        return getByIdResponse(id, internalAcsEnrollmentService, CONFIG_URL + "/");
    }

    @GetMapping("/acs/enrolment/config")
    ResponseEntity<Object> getAll() {
        return getAllResponse(internalAcsEnrollmentService, CONFIG_URL);
    }

    @PutMapping("/acs/enrolment/config/{id}")
    ResponseEntity<Object> addData(@PathVariable String id, @RequestBody Data request) {
        return addDataResponse(id, request, internalAcsEnrollmentService, CONFIG_URL + "/");
    }

    @DeleteMapping("/acs/enrolment/config/{id}")
    ResponseEntity<Object> delete(@PathVariable String id) {
        return deleteResponse(id, internalAcsEnrollmentService, CONFIG_URL + "/");
    }
}
