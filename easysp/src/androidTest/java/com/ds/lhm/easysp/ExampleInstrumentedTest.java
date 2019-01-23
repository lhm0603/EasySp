/*
 * *************************************************************************
 * Copyright (c) 2019
 * File：ExampleInstrumentedTest.java   Project：MvvmBaseProject  Module：MvvmBaseProject
 * LastModifiedTime：2019/01/20 20:25
 * CurrentModifiedTime：2019/01/20 20:25
 * Author：Lin Hua Ming
 * Email ： linhuaming@outlook.com
 * Blog ：https://blog.csdn.net/h461415832
 * *************************************************************************
 */

package com.ds.lhm.easysp;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.ds.lhm.easysp.test", appContext.getPackageName());
    }
}
