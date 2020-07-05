package com.example.home.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity {
    private String string;

    public TabEntity(String string) {
        this.string = string;
    }

    @Override
    public String getTabTitle() {
        return string;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
