package com.example.storage.core.impl;

import com.example.storage.core.IStorage;

public class FileStorage implements IStorage {

    @Override
    public <T> boolean save(String key, T value) {
        return false;
    }

    @Override
    public <T> T get(String key) {
        return null;
    }
}
