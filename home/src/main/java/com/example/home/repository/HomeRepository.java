package com.example.home.repository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.example.conmon.app.BaseApplication;
import com.example.conmon.async.CacheThreadPool;
import com.example.conmon.net.NetStateUtils;
import com.example.conmon.utils.LogUtils;
import com.example.core.repository.Repository;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SysMsgEntity;
import com.example.home.model.database.HomeDBHelper;
import com.example.home.model.service.HomeLocalModel;
import com.example.home.model.service.HomeRemoteModel;
import com.example.protocol.BasesRespEntity;

import java.util.List;

public class HomeRepository extends Repository<HomeRemoteModel>{

    private final String TAG =HomeRepository.class.getSimpleName();

    private HomeLocalModel homeLocalModel = null;

    @Override
    protected HomeRemoteModel createModel() {
        return new HomeRemoteModel();
    }

    public HomeRepository(LifecycleOwner _owner){
        super(_owner);
        homeLocalModel = new HomeLocalModel();
    }

    LiveData<BasesRespEntity<List<BannerEntity>>> banner = null;

    public LiveData<BasesRespEntity<List<BannerEntity>>> getBanner(){
        if (NetStateUtils.isNetWordAvailable(BaseApplication.getApplication())){
            banner = getmModel().getBanner();
            banner.observe(owner, new Observer<BasesRespEntity<List<BannerEntity>>>() {
                @Override
                public void onChanged(final BasesRespEntity<List<BannerEntity>> listBasesRespEntity) {
                    CacheThreadPool.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance().getDB().homeDao().insertBannerAll(listBasesRespEntity.getData());
                        }
                    });
                }
            });
        }else{
            final MutableLiveData<BasesRespEntity<List<BannerEntity>>> data = new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<BannerEntity> banner = homeLocalModel.getBanner();
                    data.postValue(new BasesRespEntity<List<BannerEntity>>(banner));
                }
            });
            return data;
        }
        return banner;
    }


    public LiveData<BasesRespEntity<List<SysMsgEntity>>> getSystemMegs(){
        LiveData<BasesRespEntity<List<SysMsgEntity>>> systemMsgs = null;
        if (NetStateUtils.isNetWordAvailable(BaseApplication.getApplication())){
            systemMsgs = getmModel().getSysMsg();
            systemMsgs.observe(owner, new Observer<BasesRespEntity<List<SysMsgEntity>>>() {
                @Override
                public void onChanged(final BasesRespEntity<List<SysMsgEntity>> listBasesRespEntity) {
                    CacheThreadPool.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance().getDB().homeDao().insertSysMsgAll(listBasesRespEntity.getData());
                        }
                    });
                }
            });
        }else{
            final MutableLiveData<BasesRespEntity<List<SysMsgEntity>>> data = new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<SysMsgEntity> banner = homeLocalModel.getSysMsg();
                    data.postValue(new BasesRespEntity<List<SysMsgEntity>>(banner));
                }
            });
            return data;
        }
        return systemMsgs;
    }
    public LiveData<BasesRespEntity<List<ProductEntity>>> getNewProduct(){
        LiveData<BasesRespEntity<List<ProductEntity>>> newProduct = null;
        if (NetStateUtils.isNetWordAvailable(BaseApplication.getApplication())){
            newProduct = getmModel().getNewProduct();
            newProduct.observe(owner, new Observer<BasesRespEntity<List<ProductEntity>>>() {
                @Override
                public void onChanged(final BasesRespEntity<List<ProductEntity>> listBasesRespEntity) {
                    CacheThreadPool.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance().getDB().homeDao().insertNewProductAll(listBasesRespEntity.getData());
                        }
                    });
                }
            });
        }else{
            final MutableLiveData<BasesRespEntity<List<ProductEntity>>> data = new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<ProductEntity> banner = homeLocalModel.getNewProduct();
                    data.postValue(new BasesRespEntity<List<ProductEntity>>(banner));
                }
            });
            return data;
        }
        return newProduct;
    }
    public LiveData<BasesRespEntity<List<ProductEntity>>> getProduct(){
        LiveData<BasesRespEntity<List<ProductEntity>>> product = null;
        if (NetStateUtils.isNetWordAvailable(BaseApplication.getApplication())){
            product = getmModel().getProduct();
            product.observe(owner, new Observer<BasesRespEntity<List<ProductEntity>>>() {
                @Override
                public void onChanged(final BasesRespEntity<List<ProductEntity>> listBasesRespEntity) {
                    CacheThreadPool.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance().getDB().homeDao().insertProductAll(listBasesRespEntity.getData());
                        }
                    });
                }
            });
        }else{
            final MutableLiveData<BasesRespEntity<List<ProductEntity>>> data = new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<ProductEntity> banner = homeLocalModel.getProduct();
                    data.postValue(new BasesRespEntity<List<ProductEntity>>(banner));
                }
            });
            return data;
        }
        return product;
    }
}
