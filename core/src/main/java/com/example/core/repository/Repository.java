package com.example.core.repository;

import androidx.lifecycle.LifecycleOwner;
import com.example.core.model.IModel;

/**
 * 数据仓库的基类
 * @param <T>
 */
public abstract class Repository<T extends IModel> {
    protected LifecycleOwner owner;
    protected T mModel;

    public Repository(){
        mModel = createModel();
    }
    public Repository(LifecycleOwner _owner) {
        mModel = createModel();
        owner=_owner;
    }

    protected T getmModel(){
        return mModel;
    }

    /**
     * 创建业务model
     * @return
     */
    protected abstract T createModel();

}
