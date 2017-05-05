package sample;

import com.github.apolo92.core.CallAction;
import com.github.apolo92.core.DiscoveryActions;
import com.github.apolo92.issues.ItemNotFound;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidateNameIsApoloTest {

    private CallAction callAction = new CallAction();

    @BeforeClass
    public static void setUp() {
        DiscoveryActions.init();
    }

    @Test
    public void callObserverValidateNameAction() {
        JSONObject response = callAction.body("name", "apolo")
                .call("ValidateNameIsApolo")
                .executeActions();

        assertEquals(response.getString("ValidateNameIsApolo"), "name");
    }

    @Test
    public void callTwoActionsvalidateNameAndGetDatabase() {
        String name = "apolo";
        JSONObject response = callAction.body("name", name)
                .call("ValidateNameIsApolo")
                .param("user", name)
                .call("GetDatabaseAction")
                .executeActions();

        assertEquals(response.getJSONObject("GetDatabaseAction").getString("surname"), "testing");
    }

    @Test(expected = ItemNotFound.class)
    public void callGetDatabaseAndThrowError() {
        String name = "test";
        JSONObject response = callAction
                .param("user", name)
                .call("GetDatabaseAction")
                .executeActions();
    }

}