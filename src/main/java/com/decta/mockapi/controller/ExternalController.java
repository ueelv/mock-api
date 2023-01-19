package com.decta.mockapi.controller;

import com.decta.mockapi.model.Data;
import com.decta.mockapi.model.Error;
import com.decta.mockapi.service.ExternalService;
import com.decta.mockapi.utils.LogUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.decta.mockapi.utils.LogUtil.*;


@RestController
@RequestMapping(path = "/api/external", produces = "application/json")
public class ExternalController extends AbstractController {
    private final ExternalService externalService;
    private static final String PAN = "pan";

    public ExternalController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @PostMapping("/enrolment")
    ResponseEntity<Object> getExternal(@RequestBody Map<String, String> request) {
        int httpStatusCode;
        if (request.containsKey(PAN)) {
            Optional<Data> response = externalService.getById(request.get(PAN));

            if (response.isPresent()) {
                httpStatusCode = response.get().getHttpResponseCode();
                String responseLog = getNodeValueFromJson(convertDataToJson(response.get()),"response");
                log.info("request: POST /api/external/enrolment httpStatusCode: {} response: {} ", httpStatusCode, responseLog);
                return new ResponseEntity<>(response.get().getResponse(), HttpStatus.valueOf(httpStatusCode));
            } else {
                httpStatusCode = 404;
                Error error = new Error(httpStatusCode, "card not found", "/api/external/config/enrolment");
                String errorLog = convertDataToJson(error);
                log.info("request: POST /api/external/enrolment httpStatusCode: {} response: {} ", httpStatusCode, errorLog);
                return new ResponseEntity<>(error, HttpStatus.valueOf(httpStatusCode));
            }


//            return response.map(data -> new ResponseEntity<>(data.getResponse(), HttpStatus.valueOf(data.getHttpResponseCode()))).orElseGet(() ->
//                    new ResponseEntity<>(new Error(404,
//                            "card not found", "/api/external/enrolment"), HttpStatus.NOT_FOUND));
        } else {
            httpStatusCode = 400;
            Error error = new Error(httpStatusCode, "pan parameter is missing", "/api/external/enrolment");
            String errorLog = convertDataToJson(error);
            log.info("request: POST /api/external/enrolment httpStatusCode: {} response: {} ", httpStatusCode, errorLog);
            return new ResponseEntity<>(error, HttpStatus.valueOf(httpStatusCode));
        }
    }


    @GetMapping("/enrolment/config/{id}")
    ResponseEntity<Object> getById(@PathVariable("id") String id) {
        int httpStatusCode;
        Optional<Data> response = externalService.getById(id, false);
        if (response.isPresent()) {
            httpStatusCode = 200;
            String responseLog = convertDataToJson(response.get());
            log.info("request: GET /enrolment/config/{} httpStatusCode: {} response: {} ", id, httpStatusCode, responseLog);
            return new ResponseEntity<>(response.get(), HttpStatus.valueOf(httpStatusCode));
        } else {
            httpStatusCode = 404;
            Error error = new Error(httpStatusCode, "card not found", "/api/external/config/enrolment");
            String errorLog = convertDataToJson(error);
            log.info("request: GET /enrolment/config/{} httpStatusCode: {} response: {} ", id, httpStatusCode, errorLog);
            return new ResponseEntity<>(error, HttpStatus.valueOf(httpStatusCode));
        }
    }

    @GetMapping("/enrolment/config")
    ResponseEntity<Object> getAll() {
        List<Data> response = externalService.getAll();
        String responseLog = convertDataToJson(response);
        log.info("request: GET /enrolment/config httpStatusCode: 200 response: {}", responseLog);
        return new ResponseEntity<>(response, HttpStatus.valueOf(200));
    }

    @PutMapping("/enrolment/config/{id}")
    ResponseEntity<Object> addData(@PathVariable String id, @RequestBody Data request) {
        String responseLog;
        String requestLog = convertDataToJson(request);
        Optional<Data> response = externalService.addById(id, request);
        responseLog = response.map(LogUtil::convertDataToJson).orElse(null);
        log.info("request: PUT /enrolment/config/{} {}  httpStatusCode: 200 response: {}", id, requestLog, responseLog);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/enrolment/config/{id}")
    ResponseEntity<Object> delete(@PathVariable String id) {
        externalService.deleteById(id);
        log.info("request: DELETE /enrolment/{} httpStatusCode: 200", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
