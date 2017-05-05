package com.github.apolo92.action;

import com.github.apolo92.request.Request;
import org.json.JSONObject;
import rx.Observable;

public interface Action {

    Object execute(final Request req);

    Observable<JSONObject> rxAction(String actionName, Request params);
}
