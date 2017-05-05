package sample;

import com.github.apolo92.core.CallAction;
import com.github.apolo92.core.DiscoveryActions;
import org.junit.BeforeClass;
import org.junit.Test;

public class HellouWorldSampleActionTest {

    private CallAction callAction = new CallAction();

    @BeforeClass
    public static void setUp(){
        DiscoveryActions.init();
    }

    @Test
    public void callObserverHellouWorldAction(){
        callAction.body("name","HELLOU WORLD!!")
                .call("HellouWorldSampleAction")
                .executeActions();
    }

    @Test
    public void callTwoActionsHellouWordAssync(){
        callAction.body("name","HELLOU")
                .call("HellouWorldSampleAction")
                .body("name","WORLD!!")
                .call("HellouWorldSampleAction")
                .executeActions();
    }

}