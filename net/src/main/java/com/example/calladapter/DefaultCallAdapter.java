package com.example.calladapter;

import com.example.net.R;
import retrofit2.Call;
import retrofit2.CallAdapter;

import java.lang.reflect.Type;

public class DefaultCallAdapter implements CallAdapter<R,Object> {
    Type type;

    public DefaultCallAdapter(Type type) {
        this.type = type;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public Object adapt(Call<R> call) {
        return call;
    }
}
