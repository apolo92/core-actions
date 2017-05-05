package com.github.apolo92.core;

import com.github.apolo92.action.Action;
import com.github.apolo92.issues.ErrorCreatingAction;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DiscoveryActionsTest {

    @BeforeClass
    public static void init(){
        DiscoveryActions.init();
    }

    @Test
    public void getActionRegisterinDiscovery(){
        Action test = DiscoveryActions.getAction("HellouWorldSampleAction");
        Assert.assertNotNull(test);
    }

    @Test(expected = ErrorCreatingAction.class)
    public void getActionNotRegistred(){
        Action test = DiscoveryActions.getAction("ActionNotExist");
        Assert.assertNull(test);
    }
}