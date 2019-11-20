package com.andromeda.araserver.test;

import com.andromeda.araserver.util.KeyWord;
import org.junit.Test;

import java.io.InputStream;


public class NLPtests {
    @Test
    public void mainTest(){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("parse.bin");

    }

}
