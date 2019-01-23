
/*
 * *************************************************************************
 * Copyright (c) 2019
 * File：MainActivity.java   Project：EasySpExample  Module：app
 * LastModifiedTime：2019/01/22 23:51
 * CurrentModifiedTime：2019/01/22 23:52
 * Author：Lin Hua Ming
 * Email ： linhuaming@outlook.com
 * Blog ：https://blog.csdn.net/h461415832
 * *************************************************************************
 */

package com.ds.lhm.easyspexample;

import android.os.Bundle;
import android.util.Log;

import com.ds.lhm.easysp.EasySp;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Example
 *
 * @author ： linhuaming linhuaming0603@outlook.com
 * @since ：2019/1/22 23:51
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "EasySp";

    private UserSp userSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create SharedPreferences mapping Object，may be single instance。
        userSp = EasySp.create(UserSp.class);

        //SharedPreferences.edit().putBoolean("first_start",true).apply();
        userSp.putFirstStart(true);
        //SharedPreferences.getBoolean("first_start",false);
        boolean firstStart = userSp.getFirstStart(null);
        //Same as above
        //boolean firstStart = userSp.getFirstStart(false);
        Log.d(TAG, "onCreate: firstStart=" + firstStart);

        //SharedPreferences.edit().remove("first_start").apply();
        userSp.putFirstStart(null);
        firstStart = userSp.getFirstStart(false);
        Log.d(TAG, "onCreate: firstStart=" + firstStart);

        userSp.putToken("00112233445566778899");
        String token = userSp.getToken("thereIsNo");
        Log.d(TAG, "onCreate: token=" + token);

        //SharedPreferences.edit().clear().apply();
        userSp.clear();

        // after clearing

        token = userSp.getToken(null);
        Log.e(TAG, "onCreate: token=" + token);

    }
}
