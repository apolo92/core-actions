package sample;

import com.github.apolo92.action.ActionCore;
import com.github.apolo92.core.DiscoveryAction;
import com.github.apolo92.request.Request;

import java.util.Optional;

@DiscoveryAction(name = "HellouWorldSampleAction")
public class HellouWorldSampleAction extends ActionCore {

    @Override
    public Object execute(final Request req) {
        String name = req.getBody().getString("name");
        System.out.println(name);

        return Optional.empty();
    }
}
