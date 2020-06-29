package com.example.conmon.app;

import android.app.Application;
import android.content.Context;

public abstract class BaseApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initOtherConfig();
    }

    protected abstract void initOtherConfig();
    public static Context getApplication(){
        return context;
    }
}
