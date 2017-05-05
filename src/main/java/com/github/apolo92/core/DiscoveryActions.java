package com.github.apolo92.core;

import com.github.apolo92.action.Action;
import com.github.apolo92.issues.ErrorCreatingAction;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class DiscoveryActions {

    private static Map<String, Class<? extends Action>> actionRegistred = new HashMap<>();
    private static final Logger log = Logger.getLogger(DiscoveryActions.class.getName());

    public static void init(){
        Reflections reflections = new Reflections();

        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(DiscoveryAction.class);
        classes.forEach(aClass -> {
            DiscoveryAction annotation = aClass.getAnnotation(DiscoveryAction.class);
            actionRegistred.put(annotation.name(), (Class<? extends Action>) aClass);
        });
    }

    public static Action getAction(String actionName){
        try {
            return actionRegistred.get(actionName).newInstance();
        } catch (InstantiationException e) {
            log.severe("Action not registred: " + actionName);
            e.printStackTrace();
            throw new ErrorCreatingAction("Action not registred: " + actionName);
        } catch (IllegalAccessException e) {
            log.severe("Action not registred: " + actionName);
            e.printStackTrace();
            throw new ErrorCreatingAction("Action not registred: " + actionName);
        }
    }
}
