package com.github.apolo92.action;


import com.github.apolo92.request.Request;
import org.json.JSONObject;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.logging.Logger;

public abstract class ActionCore implements Action {

    private final Logger log = Logger.getLogger(ActionCore.class.getName());

    @Override
    public abstract Object execute(final Request req);

    @Override
    public Observable<JSONObject> rxAction(String actionName, Request params) {
        return Observable.fromCallable(() -> callAssyncAction(actionName, params)).subscribeOn(Schedulers.io());
    }

    private final JSONObject callAssyncAction(String actionName, Request request) throws Exception {
        JSONObject result = new JSONObject();
        try {
            result.put(actionName, execute(request));
        } catch (Throwable e) {
            log.severe("Error to call action " + actionName + " " + e);
            e.printStackTrace();
            throw e;
        }
        return result;
    }
}
