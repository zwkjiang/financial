package com.example.storage.core.impl;

import com.example.conmon.app.BaseApplication;
import com.example.conmon.utils.LogUtils;
import com.example.storage.core.IStorage;
import com.example.storage.utils.SharePreferenceUtils;

public class SPStorage implements IStorage {
    private final String TAG=SPStorage.class.getSimpleName();
    @Override
    public <T> boolean save(String key, T value) {
        try{
            SharePreferenceUtils.put(BaseApplication.getApplication(),key
                    ,value);

        }catch (Exception ex){
            LogUtils.INSTANCE.e(TAG,ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public <T> T get(String key) {
        T result = (T) SharePreferenceUtils.get(BaseApplication.getApplication(), key, "");
        return result;
    }
}
