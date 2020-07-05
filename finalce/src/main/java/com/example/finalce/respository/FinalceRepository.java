package com.example.finalce.respository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.example.conmon.app.BaseApplication;
import com.example.conmon.async.CacheThreadPool;
import com.example.conmon.net.NetStateUtils;
import com.example.core.repository.Repository;
import com.example.finalce.entity.FinalceEntity;
import com.example.finalce.model.database.FinalceDBHelper;
import com.example.finalce.model.service.FinalceLocalService;
import com.example.finalce.model.service.FinalceRemoteService;
import com.example.protocol.BasesRespEntity;

import java.util.List;

public class FinalceRepository extends Repository<FinalceRemoteService> {
    private FinalceLocalService finalceLocalService;
    private LiveData<BasesRespEntity<List<FinalceEntity>>> finalce;
    @Override
    protected FinalceRemoteService createModel() {
        return new FinalceRemoteService();
    }

    public FinalceRepository(LifecycleOwner _owner) {
        super(_owner);
        finalceLocalService = new FinalceLocalService();
    }

    public LiveData<BasesRespEntity<List<FinalceEntity>>> getFinalce(final int producttype, final int currentpage, final int pagesize){
        if (NetStateUtils.isNetWordAvailable(BaseApplication.getApplication())){
            finalce = mModel.getFinacel(producttype, currentpage, pagesize);
            finalce.observe(owner, new Observer<BasesRespEntity<List<FinalceEntity>>>() {
            @Override
            public void onChanged(final BasesRespEntity<List<FinalceEntity>> listBasesRespEntity) {
                CacheThreadPool.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        FinalceDBHelper.getInstance().getDB().finalceDao().insertFinalce(listBasesRespEntity.getData());
                    }
                });
            }
        });
        }else{
            final MutableLiveData<BasesRespEntity<List<FinalceEntity>>> mutableLiveData = new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<FinalceEntity> finalceAll = FinalceDBHelper.getInstance().getDB().finalceDao().getFinalceAll(producttype, currentpage, pagesize);
                    mutableLiveData.postValue(new BasesRespEntity<List<FinalceEntity>>(finalceAll));
                }
            });
            return mutableLiveData;
        }
        return finalce;
    }
}
