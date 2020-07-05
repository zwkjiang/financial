package com.example.finalce.model.service;

import com.example.core.model.IModel;
import com.example.finalce.entity.FinalceEntity;
import com.example.finalce.model.database.FinalceDBHelper;

import java.util.List;

public class FinalceLocalService implements IModel {
    private final String TAG = FinalceLocalService.class.getSimpleName();

    public void insertFinalce(List<FinalceEntity> entities){
        FinalceDBHelper.getInstance()
                .getDB()
                .finalceDao()
                .insertFinalce(entities);
    }
    public List<FinalceEntity> getFinalce(int producttype,int currentpage,int pagesize){
        List<FinalceEntity> finalceAll = FinalceDBHelper.getInstance()
                .getDB()
                .finalceDao()
                .getFinalceAll(producttype, currentpage, pagesize);
        return finalceAll;
    }
}
