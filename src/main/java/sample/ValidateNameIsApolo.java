package sample;

import com.github.apolo92.action.ActionCore;
import com.github.apolo92.core.DiscoveryAction;
import com.github.apolo92.request.Request;

@DiscoveryAction(name = "ValidateNameIsApolo")
public class ValidateNameIsApolo extends ActionCore {

    @Override
    public Object execute(Request req) {
        String name = req.getBody().getString("name");
        if (name.equals("apolo")) {
            return name;
        }
        return null;
    }
}
