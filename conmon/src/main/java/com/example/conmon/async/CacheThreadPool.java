package com.example.conmon.async;

import java.util.concurrent.Executors;

public class CacheThreadPool {

    private static CacheThreadPool instance = new CacheThreadPool();
    private CacheThreadPool(){}

    public static CacheThreadPool getInstance(){
        return instance;
    }

    public void execute(Runnable runnable){
        Executors.newCachedThreadPool().submit(runnable);
    }
}
