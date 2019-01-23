# EasySp

## 介绍
简单的 SharedPreferences 文件映射框架，对项目中使用的 SharedPreferences 统一管理，灵感来自于 [Retrofit2](http://square.github.io/retrofit/)！一个 SharedPreferences 文件与一个java接口形成一个映射关系。

## 软件架构
动态代理


## 安装教程

1. 使用 Android Studio 或者其他 Gradle 构建的项目

2. 将以下代码添加到app/build.gradle的`dependencies`中：

   ```groovy
   dependencies {
   	...
   	implementation 'com.ds.lhm:easysp:1.0.8'
   }
   ```

## 使用说明

### 全局初始化

```java
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Init EasySp
        EasySp.init(this);
    }
}
```

### 定义映射关系接口

```java
//给 SpFile 注解的接口，才能被映射为 SharedPreferences 
@SpFile(fileName = "user.sp"/*SharedPreferences文件名*/)
public interface UserSp {
    String TOKEN = "token";
    String FIRST_START = "first_start";
    String TIME = "time";
	//被 SpGet 注解的方法，会被映射为 SharedPreferences.getXXX 
    @SpGet(key = TOKEN/*SharedPreferences 对应的 key 名*/)
    String getToken(String def/*默认值*/);
	//被 SpPut 注解的方法，会被映射为 SharedPreferences.edit().putXXX() 
    @SpPut(key = TOKEN/*SharedPreferences 对应的 key 名*/)
    void putToken(String value/*put 的值*/);

    @SpGet(key = TIME)
    long getTime(Long def);

    @SpPut(key = TIME)
    void putTime(Long value);

    @SpGet(key = FIRST_START)
    boolean getFirstStart(Boolean def);

    @SpPut(key = FIRST_START)
    void putFirstStart(Boolean value);
	//被 SpClear 注解的方法，会被映射为 SharedPreferences.edit().clear() 
    @SpClear
    void clear();
}
```

> 注意：
>
> 1. 只有被 SpFIle 注解的接口才能被映射为 SharedPreferences 
> 2. 被 SpFile 注解的接口内只能出现 被 SpGet 、 SpPut 或 SpClear 注解的方法且。
> 3. 被 SpGet 注解的方法
>    1. 返回值只能是 int、long、boolean、float、String、Set\<String\>，
>    2. 参数只能有一个，且类型只能是 Integer、Long、Boolean、Float、String、Set\<String\>
> 4. 被 SpPut 注解的方法
>    1. 返回值只能是 void
>    2. 参数只能有一个，且类型只能是 Integer、Long、Boolean、Float、String、Set\<String\>
> 5. 被 SpGet 注解的方法 
>    1. 返回值只能是 void
>    2. 不能有参数

### 程序中使用

```java
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
```

## 许可证

```
Copyright 2019 Huaming Lin.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```