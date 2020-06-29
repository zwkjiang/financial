package com.example.storage.core;

import com.example.storage.core.impl.FileStorage;
import com.example.storage.core.impl.SPStorage;

public class StorageManager {

    private static StorageManager instance = new StorageManager();

    private IStorage storage;

    private StorageManager(){
        storage = new SPStorage();
    }
    public static StorageManager getInstance(){
        return instance;
    }

    public void init(int storageType){
        if (storageType==StorageType.SP){
            storage = new SPStorage();
        }else if (storageType == StorageType.FILE){
            storage = new FileStorage();
        }
    }

    public <T> boolean save(String key,T value){
        return storage.save(key,value);
    }

    public <T> T get(String key){
        return storage.get(key);
    }
}
