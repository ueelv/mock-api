package com.decta.mockapi.controller;

import com.decta.mockapi.model.Data;
import com.decta.mockapi.service.InternalAcsAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/internal", produces = "application/json")
public class InternalAcsAuthenticationController extends AbstractController {

    private static final String CONFIG_URL = "/api/internal/acs/authentication/config";
    private final InternalAcsAuthenticationService internalAcsAuthenticationService;
    private static final String ACCT_NUMBER = "acctNumber";

    public InternalAcsAuthenticationController(InternalAcsAuthenticationService internalAcsAuthenticationService) {
        this.internalAcsAuthenticationService = internalAcsAuthenticationService;
    }

    @PostMapping("/acs/authentication")
    ResponseEntity<Object> getInternalAcsEnrollment(@RequestBody Map<String, Object> request) {
        return getPostResponse(ACCT_NUMBER, request, internalAcsAuthenticationService, "/api/internal/acs/authentication");
    }

    @GetMapping("/acs/authentication/config/{id}")
    ResponseEntity<Object> getById(@PathVariable("id") String id) {
        return getByIdResponse(id, internalAcsAuthenticationService, CONFIG_URL + "/");
    }

    @GetMapping("/acs/authentication/config")
    ResponseEntity<Object> getAll() {
        return getAllResponse(internalAcsAuthenticationService, CONFIG_URL);
    }

    @PutMapping("/acs/authentication/config/{id}")
    ResponseEntity<Object> addData(@PathVariable String id, @RequestBody Data request) {
        return addDataResponse(id, request, internalAcsAuthenticationService, CONFIG_URL + "/");
    }

    @DeleteMapping("/acs/authentication/config/{id}")
    ResponseEntity<Object> delete(@PathVariable String id) {
        return deleteResponse(id, internalAcsAuthenticationService, CONFIG_URL + "/");
    }

}
