
/*
 * *************************************************************************
 * Copyright (c) 2019
 * File：MainApplication.java   Project：EasySpExample  Module：app
 * LastModifiedTime：2019/01/22 23:34
 * CurrentModifiedTime：2019/01/22 23:52
 * Author：Lin Hua Ming
 * Email ： linhuaming@outlook.com
 * Blog ：https://blog.csdn.net/h461415832
 * *************************************************************************
 */

package com.ds.lhm.easyspexample;

import android.app.Application;

import com.ds.lhm.easysp.EasySp;

/**
 * @author ： linhuaming linhuaming0603@outlook.com
 * @since ：2019/1/22 23:33
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Init EasySp
        EasySp.init(this);
    }
}
