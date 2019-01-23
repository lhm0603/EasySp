

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

import android.content.Context;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author ： linhuaming  Email:linhuaming0603@outlook.com
 * @since ： 2019/01/20 17:03
 */
public class EasySp {

    public static void init(Context context) {
        Objects.requireNonNull(context);
        SpUtils.init(context.getApplicationContext());
    }

    private static <T> void validateServiceInterface(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }

    private static <T> void validateServiceInterfaceMethod(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
        Method[] declaredMethods = service.getDeclaredMethods();
        for (Method m : declaredMethods) {
            validateAnnotationType(m);
            validateReturnType(m);
            validateParameterType(m);
        }
    }

    private static void validateAnnotationType(Method m) {
        SpGet spGet = m.getAnnotation(SpGet.class);
        SpPut spPut = m.getAnnotation(SpPut.class);
        SpClear spClear = m.getAnnotation(SpClear.class);
        List<Annotation> annotationList = new ArrayList<>();
        if (spGet != null) {
            annotationList.add(spGet);
        }
        if (spPut != null) {
            annotationList.add(spPut);
        }
        if (spClear != null) {
            annotationList.add(spClear);
        }
        if (annotationList.size() != 1) {
            throw new IllegalArgumentException("API method Annotation X has to have SpGet,SpPut, or SpClear.");
        }
    }

    private static void validateParameterType(Method m) {
        Class<?>[] parameterTypes = m.getParameterTypes();
        SpClear spClear = m.getAnnotation(SpClear.class);
        if (spClear != null) {
            if (parameterTypes.length != 0) {
                throw new IllegalArgumentException("SpClear method parameter count must be 0.");
            }
            return;
        }
        if (parameterTypes.length != 1) {
            throw new IllegalArgumentException("API method parameter count must be 1.");
        }
        Class<?> parameterType = parameterTypes[0];
        if (parameterType != Integer.class
                && parameterType != Long.class
                && parameterType != Float.class
                && parameterType != Boolean.class
                && parameterType != String.class
                && parameterType != Set.class) {
            throw new IllegalArgumentException("API method parameter type must has to be Integer,Long,Float,Boolean,String or Set.");
        }
    }

    private static void validateReturnType(Method m) {
        Class<?> returnType = m.getReturnType();
        SpGet spGet = m.getAnnotation(SpGet.class);
        if (spGet != null) {
            if (returnType != int.class
                    && returnType != long.class
                    && returnType != float.class
                    && returnType != boolean.class
                    && returnType != String.class
                    && returnType != Set.class) {
                throw new IllegalArgumentException("SpGet method return type must has to be int,long,float,boolean,String or Set.");
            }
            return;
        }
        SpPut spPut = m.getAnnotation(SpPut.class);
        if (spPut != null) {
            if (returnType != void.class) {
                throw new IllegalArgumentException("SpPut method return type must void.");
            }
            return;
        }
        SpClear spClear = m.getAnnotation(SpClear.class);
        if (spClear != null) {
            if (returnType != void.class) {
                throw new IllegalArgumentException("SpClear method return type must void.");
            }
        }
    }


    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> spFileClass) {
        //检查接口合法
        validateServiceInterface(spFileClass);
        //检查接口的方法是否合法
        validateServiceInterfaceMethod(spFileClass);
        SpFile spFile = spFileClass.getAnnotation(SpFile.class);
        final String fileName = Objects.requireNonNull(spFile).fileName();
        return (T) Proxy.newProxyInstance(spFileClass.getClassLoader(), new Class<?>[]{spFileClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        SpPut spPut = method.getAnnotation(SpPut.class);
                        if (spPut != null) {
                            String key = spPut.key();
                            Object value = args[0];
                            SpUtils.put(fileName, key, value);
                            return null;
                        }
                        SpGet spGet = method.getAnnotation(SpGet.class);
                        if (spGet != null) {
                            String key = spGet.key();
                            Object value = args[0];
                            if (value == null) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType == int.class) {
                                    value = 0;
                                } else if (returnType == long.class) {
                                    value = 0L;
                                } else if (returnType == float.class) {
                                    value = 0.0F;
                                } else if (returnType == boolean.class) {
                                    value = false;
                                } else if (returnType == String.class) {
                                    value = "";
                                } else {
                                    value = new HashSet<String>(0);
                                }
                            }
                            return SpUtils.get(fileName, key, value);
                        }
                        SpClear spClear = method.getAnnotation(SpClear.class);
                        if (spClear != null) {
                            SpUtils.clear(fileName);
                            return null;
                        }
                        return null;
                    }
                });
    }
}
