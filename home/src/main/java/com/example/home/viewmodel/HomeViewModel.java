package com.example.home.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.core.repository.Repository;
import com.example.core.viewmodel.BaseViewModel;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SysMsgEntity;
import com.example.home.repository.HomeRepository;
import com.example.protocol.BasesRespEntity;

import java.util.List;

public class HomeViewModel extends BaseViewModel {

    public HomeViewModel(LifecycleOwner _owner){
        super(_owner);
        registerRepository(HomeRepository.class.getSimpleName(),new HomeRepository(_owner));
    }

    /**
     * 获取banner数据
     * @return
     */
       public LiveData<BasesRespEntity<List<BannerEntity>>> getBanner(){
        HomeRepository repository = super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getBanner();
    }

    /**
     * 获取System数据
     * @return
     */
    public LiveData<BasesRespEntity<List<SysMsgEntity>>> getSystemMegs(){
        HomeRepository repository = super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getSystemMegs();
    }

    /**
     * 获取newProduct数据
     * @return
     */
    public LiveData<BasesRespEntity<List<ProductEntity>>> getNewProduct(){
        HomeRepository repository = super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getNewProduct();
    }

    /**
     * 获取product数据
     * @return
     */
    public LiveData<BasesRespEntity<List<ProductEntity>>> getProduct(){
        HomeRepository repository = super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getProduct();
    }
}
