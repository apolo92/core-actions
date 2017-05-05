package sample;


import com.github.apolo92.action.ActionCore;
import com.github.apolo92.core.DiscoveryAction;
import com.github.apolo92.issues.ItemNotFound;
import com.github.apolo92.request.Request;
import org.json.JSONObject;

@DiscoveryAction(name = "GetDatabaseAction")
public class GetDatabaseAction extends ActionCore{

    @Override
    public Object execute(Request req) {
        String name = req.getParams().getString("user");
        if (name.equals("apolo"))
            return mockDatabase();
        else
            throw new ItemNotFound();
    }

    private JSONObject mockDatabase() {
        JSONObject json = new JSONObject();
        json.put("name","apolo");
        json.put("surname", "testing");
        return json;
    }
}
