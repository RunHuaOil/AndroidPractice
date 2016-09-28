package com.runhuaoil.locationchat;

/**
 * Created by RunHua on 2016/9/26.
 */

public class Msg {

    public static final int MSG_SEND = 0;
    public static final int MSG_RECEIVE = 1;

    private String content;
    private int type;


    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }


    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
