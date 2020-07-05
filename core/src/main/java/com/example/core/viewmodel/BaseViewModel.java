package com.example.core.viewmodel;

import androidx.lifecycle.*;
import com.example.core.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class BaseViewModel extends ViewModel implements LifecycleObserver {
    protected Map<String, Repository> repositoryMap;

    protected LifecycleOwner owner;

    public BaseViewModel(LifecycleOwner _owner){
        repositoryMap  = new HashMap<>();
        owner=_owner;
    }
    public BaseViewModel(){
        repositoryMap  = new HashMap<>();
    }

    /**
     * 注册数据仓库
     * @param key
     * @param repository
     */
    protected void registerRepository(String key,Repository repository){
        if (repositoryMap.containsKey(key)){
            return;
        }
        repositoryMap.put(key,repository);
    }

    /**
     * 注销数据仓库
     * @param key
     */
    protected void unRegisterRepository(String key){
        if (repositoryMap.containsKey(key)){
            repositoryMap.remove(key);
        }
    }

    /**
     * 获取数据仓库
     * @param key
     * @param <SubRepository>
     * @return
     */
    protected <SubRepository extends Repository> SubRepository getRepository(String key){
        if (repositoryMap.containsKey(key)){
            return (SubRepository) repositoryMap.get(key);
        }
        return null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disConnectOwner(){
        repositoryMap.clear();
        repositoryMap = null;
    }
}
