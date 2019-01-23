/*
 * *************************************************************************
 * Copyright (c) 2019
 * File：EasySp.java   Project：EasySpExample  Module：easysp
 * LastModifiedTime：2019/01/20 20:30
 * CurrentModifiedTime：2019/01/22 23:53
 * Author：Lin Hua Ming
 * Email ： linhuaming@outlook.com
 * Blog ：https://blog.csdn.net/h461415832
 * *************************************************************************
 */

package com.ds.lhm.easysp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author ： linhuaming  Email:linhuaming0603@outlook.com
 * @since ： 2019/01/20 17:03
 */
class SpUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    static void init(Context context) {
        if (mContext != null) {
            throw new IllegalArgumentException("SpUtils.init(Context) can only initialize once.");
        }
        mContext = context;
    }

    @SuppressWarnings("unchecked")
    static <T> void put(String fileName, String key, T value) {
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (value == null) {
            edit.remove(key);
            edit.apply();
            return;
        }
        Class<?> aClass = value.getClass();
        if (aClass == Integer.class) {
            edit.putInt(key, (Integer) value);
        } else if (aClass == Boolean.class) {
            edit.putBoolean(key, (Boolean) value);
        } else if (aClass == Float.class) {
            edit.putFloat(key, (Float) value);
        } else if (aClass == Long.class) {
            edit.putLong(key, (Long) value);
        } else if (aClass == Set.class) {
            edit.putStringSet(key, (Set<String>) value);
        } else {
            edit.putString(key, String.valueOf(value));
        }
        edit.apply();
    }

    @SuppressWarnings("unchecked")
    static <T> Object get(String fileName, String key, T value) {
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Class<?> aClass = value.getClass();
        if (aClass == Integer.class) {
            return sp.getInt(key, (Integer) value);
        } else if (aClass == Boolean.class) {
            return sp.getBoolean(key, (Boolean) value);
        } else if (aClass == Float.class) {
            return sp.getFloat(key, (Float) value);
        } else if (aClass == Long.class) {
            return sp.getLong(key, (Long) value);
        } else if (aClass == String.class) {
            return sp.getString(key, (String) value);
        } else {
            return sp.getStringSet(key, (Set<String>) value);
        }
    }

    static void clear(String fileName) {
        SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.apply();
    }


}
