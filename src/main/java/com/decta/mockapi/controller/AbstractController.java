package com.decta.mockapi.controller;

import com.decta.mockapi.model.Data;
import com.decta.mockapi.model.Error;
import com.decta.mockapi.service.AbstractDataService;
import com.decta.mockapi.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.decta.mockapi.utils.LogUtil.convertDataToJson;
import static com.decta.mockapi.utils.LogUtil.getNodeValueFromJson;

public abstract class AbstractController {
    public final Logger log = LoggerFactory.getLogger(AbstractController.class);
    private static final String MESSAGE = "request: POST {} httpStatusCode: {} response: {} ";


    public ResponseEntity<Object> getPostResponse(String key, Map<String, Object> request, AbstractDataService service, String url) {
        int httpStatusCode;
        if (request.containsKey(key)) {
            Optional<Data> response = service.getById(request.get(key), true, request);

            if (response.isPresent()) {
                httpStatusCode = response.get().getHttpResponseCode();
                String responseLog = getNodeValueFromJson(convertDataToJson(response.get()), "response");
                log.info(MESSAGE, url, httpStatusCode, responseLog);
                return new ResponseEntity<>(response.get().getResponse(), HttpStatus.valueOf(httpStatusCode));
            } else {
                httpStatusCode = 404;
                Error error = new Error(httpStatusCode, "card not found", url);
                String errorLog = convertDataToJson(error);
                log.info(MESSAGE, url, httpStatusCode, errorLog);
                return new ResponseEntity<>(error, HttpStatus.valueOf(httpStatusCode));
            }
        } else {
            httpStatusCode = 400;
            Error error = new Error(httpStatusCode, "pan parameter is missing", url);
            String errorLog = convertDataToJson(error);
            log.info(MESSAGE, url, httpStatusCode, errorLog);
            return new ResponseEntity<>(error, HttpStatus.valueOf(httpStatusCode));
        }

    }

    public ResponseEntity<Object> getByIdResponse(String id, AbstractDataService service, String url) {
        int httpStatusCode;
        Optional<Data> response = service.getById(id, false, new HashMap<>());
        if (response.isPresent()) {
            httpStatusCode = 200;
            String responseLog = convertDataToJson(response.get());
            log.info("request: GET {}{} httpStatusCode: {} response: {} ", url, id, httpStatusCode, responseLog);
            return new ResponseEntity<>(response.get(), HttpStatus.valueOf(httpStatusCode));
        } else {
            httpStatusCode = 404;
            Error error = new Error(httpStatusCode, "card not found", url + id);
            String errorLog = convertDataToJson(error);
            log.info("request: GET {}{} httpStatusCode: {} response: {} ", url, id, httpStatusCode, errorLog);
            return new ResponseEntity<>(error, HttpStatus.valueOf(httpStatusCode));
        }
    }

    public ResponseEntity<Object> getAllResponse(AbstractDataService service, String url) {
        List<Data> response = service.getAll();
        String responseLog = convertDataToJson(response);
        log.info("request: GET {} httpStatusCode: 200 response: {}", url, responseLog);
        return new ResponseEntity<>(response, HttpStatus.valueOf(200));
    }

    public ResponseEntity<Object> addDataResponse(String id, Data request, AbstractDataService service, String url) {
        String responseLog;
        String requestLog = convertDataToJson(request);
        Optional<Data> response = service.addById(id, request);
        responseLog = response.map(LogUtil::convertDataToJson).orElse(null);
        log.info("request: PUT {}{} {}  httpStatusCode: 200 response: {}", url, id, requestLog, responseLog);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteResponse(String id, AbstractDataService service, String url) {
        service.deleteById(id);
        log.info("request: DELETE {}{} httpStatusCode: 200", url, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
