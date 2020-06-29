package com.example.calladapter;

import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.protocol.BasesRespEntity;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.reflect.Type;

public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<BasesRespEntity<R>>> {
    Type type;

    public LiveDataCallAdapter(Type type) {
        this.type = type;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public LiveData<BasesRespEntity<R>> adapt(Call<R> call) {
        final MutableLiveData<BasesRespEntity<R>> data = new MutableLiveData<>();
        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    data.setValue((BasesRespEntity<R>) response.body());
                }else{
                    data.postValue((BasesRespEntity<R>) response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    data.setValue(null);
                }else{
                    data.postValue(null);
                }
            }
        });
        return data;
    }
}
