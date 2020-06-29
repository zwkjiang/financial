package com.example.usercenter.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.core.model.IModel;
import com.example.net.RetrofitFactory;
import com.example.protocol.BasesRespEntity;
import com.example.usercenter.entity.UserEntity;
import com.example.usercenter.model.api.UserApi;

public class UserModel implements IModel{
    private final  String TAG= UserModel.class.getSimpleName();

    public LiveData<BasesRespEntity<UserEntity>> login(final UserEntity userEntity){
        final MutableLiveData<BasesRespEntity<UserEntity>> result = new MutableLiveData<>();
        LiveData<BasesRespEntity<UserEntity>> login = RetrofitFactory.getInstance().getRetrofit().create(UserApi.class)
                .login(userEntity);

//        Call<BasesRespEntity<UserEntity>> login = RetrofitFactory.getInstance().getRetrofit().create(UserApi.class)
////                .login(userEntity);
////        login.enqueue(new Callback<BasesRespEntity<UserEntity>>() {
////            @Override
////            public void onResponse(Call<BasesRespEntity<UserEntity>> call, Response<BasesRespEntity<UserEntity>> response) {
////                BasesRespEntity<UserEntity> body = response.body();
////                LogUtils.INSTANCE.i(TAG,body.getData().getUsername());
////                result.setValue(body);
////            }
////
////            @Override
////            public void onFailure(Call<BasesRespEntity<UserEntity>> call, Throwable t) {
////
////            }
////        });
//        Observable<BasesRespEntity<UserEntity>> observable = Observable.create(new ObservableOnSubscribe<BasesRespEntity<UserEntity>>() {
//            @Override
//            public void subscribe(final ObservableEmitter<BasesRespEntity<UserEntity>> emitter) throws Exception {
//                UserApi userApi = RetrofitFactory.getInstance().getRetrofit().create(UserApi.class);
//                Call<BasesRespEntity<UserEntity>> login = userApi.login(userEntity);
//                login.enqueue(new Callback<BasesRespEntity<UserEntity>>() {
//                    @Override
//                    public void onResponse(Call<BasesRespEntity<UserEntity>> call, Response<BasesRespEntity<UserEntity>> response) {
//                        if (response.body().getCode()==-1){
//                            emitter.onError(new RuntimeException("登录失败"));
//                            return;
//                        }
//                        emitter.onNext(response.body());
//                        emitter.onComplete();
//                    }
//
//                    @Override
//                    public void onFailure(Call<BasesRespEntity<UserEntity>> call, Throwable t) {
//                        emitter.onError(t);
//                    }
//                });
//            }
//        });
//
//        BaseObservable.doObservable(observable,new BaseObserver<BasesRespEntity<UserEntity>>(){
//            @Override
//            public void onNext(BasesRespEntity<UserEntity> userEntityBasesRespEntity) {
//                super.onNext(userEntityBasesRespEntity);
//                result.postValue(userEntityBasesRespEntity);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                result.postValue(null);
//            }
//        });
        return login;
    }
}
