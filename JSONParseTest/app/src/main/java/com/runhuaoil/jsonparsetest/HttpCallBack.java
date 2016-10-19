package com.runhuaoil.jsonparsetest;

/**
 * Created by RunHua on 2016/10/19.
 */

public interface HttpCallBack {

    void onFinish(String responseData);

    void onError(Exception e);
}
