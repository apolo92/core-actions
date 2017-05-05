package com.github.apolo92.request;


import org.json.JSONObject;

public class Request {

    private static final String BODY = "body";
    private static final String HEADER = "header";
    private static final String QUERY = "query";
    private static final String PARAMS = "param";
    private final JSONObject request;

    public Request(JSONObject request) {
        this.request = request;
    }

    public Request(JSONObject body, JSONObject header, JSONObject query, JSONObject param) {
        this.request = new JSONObject();
        this.request.put(BODY, body);
        this.request.put(HEADER, header);
        this.request.put(QUERY, query);
        this.request.put(PARAMS, param);
    }

    public final JSONObject getBody() {
        return request.optJSONObject(BODY);
    }

    public final JSONObject getHeader() {
        return request.optJSONObject(HEADER);
    }

    public final JSONObject getQuery() {
        return request.optJSONObject(QUERY);
    }

    public final JSONObject getParams() {
        return request.optJSONObject(PARAMS);
    }
}
