package com.example.core.repository;

import com.example.core.model.IModel;

/**
 * 数据仓库的基类
 * @param <T>
 */
public abstract class Repository<T extends IModel> {

    protected T mModel;

    public Repository() {
        mModel = createModel();
    }

    /**
     * 创建业务model
     * @return
     */
    protected abstract T createModel();

}
