package com.example.usercenter.app;

import com.example.conmon.app.BaseApplication;
import com.example.storage.core.StorageManager;
import com.example.storage.core.StorageType;

public class UserCenterApp extends BaseApplication {
    @Override
    protected void initOtherConfig() {
        StorageManager.getInstance().init(StorageType.SP);
    }
}
