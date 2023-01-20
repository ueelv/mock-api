package com.decta.mockapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.decta.mockapi.utils.LogUtil.convertDataToJson;

@RestController
@RequestMapping(path = "/api/internal", produces = "application/json")
public class InternalAcsResultsController extends AbstractController {

    @PostMapping("/acs/results")
    ResponseEntity<Object> getInternalAcsResults(@RequestBody Map<String, Object> request) {
        int httpStatusCode = 200;
        String requestLog = convertDataToJson(request);
        log.info("request: POST /api/internal/acs/results {} httpStatusCode: {} ", requestLog, httpStatusCode);
        return new ResponseEntity<>(HttpStatus.valueOf(httpStatusCode));
    }

}
