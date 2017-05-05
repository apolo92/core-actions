package com.github.apolo92.core;

import com.github.apolo92.action.Action;
import com.github.apolo92.request.Request;
import org.json.JSONObject;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class CallAction {

    private ParamsRequest paramsReq;
    private final List<Observable<JSONObject>> actionsToCall;

    public CallAction() {
        this.paramsReq = new ParamsRequest();
        this.actionsToCall = new ArrayList<Observable<JSONObject>>();
    }

    public CallAction body(String keyName, Object value) {
        this.paramsReq.body.put(keyName, value);
        return this;
    }

    public CallAction header(String keyName, Object value) {
        this.paramsReq.header.put(keyName, value);
        return this;
    }

    public CallAction query(String keyName, Object value) {
        this.paramsReq.query.put(keyName, value);
        return this;
    }

    public CallAction param(String keyName, Object value) {
        this.paramsReq.params.put(keyName, value);
        return this;
    }

    public CallAction call(String nameAction) {
        Action action = DiscoveryActions.getAction(nameAction);
        Request request = new Request(this.paramsReq.body, this.paramsReq.header, this.paramsReq.query, this.paramsReq.params);
        this.paramsReq = new ParamsRequest();
        this.actionsToCall.add(action.rxAction(nameAction, request));
        return this;
    }

    public JSONObject executeActions() {
        JSONObject result = new JSONObject();
        try {
            Observable.merge(actionsToCall).toBlocking().subscribe(resultAssync -> {
                resultAssync.keys().forEachRemaining(element -> {
                    result.put(element.toString(), resultAssync.opt(element.toString()));
                });
            });
        } catch (Throwable e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            }
            throw e;
        }
        return result;
    }

    private class ParamsRequest {

        private final JSONObject body;
        private final JSONObject header;
        private final JSONObject query;
        private final JSONObject params;

        public ParamsRequest() {
            this.body = new JSONObject();
            this.header = new JSONObject();
            this.query = new JSONObject();
            this.params = new JSONObject();
        }

    }
}
