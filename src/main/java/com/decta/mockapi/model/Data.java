package com.decta.mockapi.model;

public class Data {
    Integer httpResponseCode;
    Object response;

    public Data() {
    }

    public Data(Integer httpResponseCode, Object response) {
        this.httpResponseCode = httpResponseCode;
        this.response = response;
    }

    public Integer getHttpResponseCode() {
        return httpResponseCode;
    }

    public void setHttpResponseCode(Integer httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
