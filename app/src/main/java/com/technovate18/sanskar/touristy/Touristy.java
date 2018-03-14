package com.technovate18.sanskar.touristy;

import android.app.Application;
import android.content.Context;

/**
 * Created by hadoop on 15/3/18.
 */

public class Touristy extends Application {

    public static Touristy instance;


    public Touristy(){
        instance = this;
    }

    public static Touristy getInstance() {

        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
