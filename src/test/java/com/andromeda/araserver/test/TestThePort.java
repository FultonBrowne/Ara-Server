package com.andromeda.araserver.test;

import com.andromeda.araserver.Run;
import org.junit.Assert;
import org.junit.Test;

public class TestThePort {
    @Test
    public void test(){
        Assert.assertNotEquals(Run.INSTANCE.getListeningPort(), 80);
    }
}