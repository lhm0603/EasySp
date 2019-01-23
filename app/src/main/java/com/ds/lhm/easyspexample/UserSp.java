package com.ds.lhm.easyspexample;

import com.ds.lhm.easysp.SpClear;
import com.ds.lhm.easysp.SpFile;
import com.ds.lhm.easysp.SpGet;
import com.ds.lhm.easysp.SpPut;

/**
 * @author ： linhuaming linhuaming0603@outlook.com
 * @since ：2019/1/22 23:39
 */
@SpFile(fileName = "user.sp")
public interface UserSp {
    String TOKEN = "token";
    String FIRST_START = "first_start";
    String TIME = "time";

    @SpGet(key = TOKEN)
    String getToken(String def);

    @SpPut(key = TOKEN)
    void putToken(String value);

    @SpGet(key = TIME)
    long getTime(Long def);

    @SpPut(key = TIME)
    void putTime(Long value);

    @SpGet(key = FIRST_START)
    boolean getFirstStart(Boolean def);

    @SpPut(key = FIRST_START)
    void putFirstStart(Boolean value);

    @SpClear
    void clear();

}
