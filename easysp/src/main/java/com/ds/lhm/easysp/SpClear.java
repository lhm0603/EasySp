

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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author ： linhuaming
 * @date ： 2019/01/20 17:03
 * @since ： linhuaming0603@outlook.com
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface SpClear {
}
