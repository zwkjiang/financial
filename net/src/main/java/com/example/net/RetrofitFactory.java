package com.example.net;

import android.os.Build;
import android.text.TextUtils;
import com.example.api.Api;
import com.example.calladapter.LiveDataCallAdapterFactory;
import com.example.conmon.Config;
import com.example.conmon.utils.LogUtils;
import com.example.protocol.TokenRespEntity;
import com.example.storage.core.StorageManager;
import com.google.gson.Gson;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RetrofitFactory {
    private static RetrofitFactory retrofitFactory;
    public static RetrofitFactory getInstance(){
        if (retrofitFactory==null){
            retrofitFactory = new RetrofitFactory();
        }
        return retrofitFactory;
    }
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    private Gson gson;
    public Gson getGson(){
        if (gson==null){
            gson = new Gson();
        }
        return gson;
    }

    private RetrofitFactory(){

    }

    private Retrofit retrofit;
    private final String TAG = RetrofitFactory.class.getSimpleName();
    public Retrofit getRetrofit(){
        if (retrofit==null){
            createRetrofit();
        }
        return retrofit;
    }

    private void createRetrofit() {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .baseUrl(Config.BASE_URL)
                .client(createClient())
                .build();
    }

    private OkHttpClient createClient() {
        OkHttpClient build = new OkHttpClient.Builder()
                .writeTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(createLog())
                .addInterceptor(changeBaseUrl())
                .addInterceptor(createInterceptor())
                .addInterceptor(createNetworkInterceptor())
                .build();
        return build;
    }

    private Interceptor createLog() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    private Interceptor changeBaseUrl() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl oldUrl = request.url();
                Request.Builder newBuilder = request.newBuilder();
                List<String> headers = request.headers(Config.NewUrlHeaderKey);
                LogUtils.INSTANCE.i(TAG,"KEYI");
                if (headers!=null&&headers.size()>0){
                    LogUtils.INSTANCE.i(TAG,"DIDI");
                    newBuilder.removeHeader(Config.NewUrlHeaderKey);
                    String headerValue = headers.get(0);
                    HttpUrl newBaseUrl = null;
                    if (headerValue.equals(Config.NewUrlHeaderValue)){
                        newBaseUrl = HttpUrl.parse(Config.TEXT_URL);
                    }else{
                        newBaseUrl = oldUrl;
                    }
                    HttpUrl newUri = oldUrl.newBuilder()
                            .scheme(newBaseUrl.scheme())
                            .host(newBaseUrl.host())
                            .port(newBaseUrl.port())
                            .build();
                    Request build = newBuilder.url(newUri).build();
                    return chain.proceed(build);
                }
                return chain.proceed(request);
            }
        };
        return interceptor;
    }

    private Interceptor createNetworkInterceptor() {
        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder()
                        .addHeader("v0", Build.MANUFACTURER)
                        .addHeader("V1",Build.MODEL)
                        .addHeader("V2",Build.TYPE)
                        .addHeader("V3",""+Build.VERSION.SDK_INT);
                Response proceed = chain.proceed(builder.build());
                return proceed;
            }
        };
        return interceptor;
    }

    private Interceptor createInterceptor() {
        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                String localToken = StorageManager.getInstance().get("token");
                if (!TextUtils.isEmpty(localToken)){
                    LogUtils.INSTANCE.i(TAG,localToken+"123");
                    return resetRequest(request,localToken,chain);
                }
                Response response = chain.proceed(request);
                if (response.code()==401){
                   String token = requestToken();
                   if (TextUtils.isEmpty(token)){
                       LogUtils.INSTANCE.e(TAG,"Error : token is null...");
                       return response;
                   }
                   StorageManager.getInstance().save("token",token);
                    return resetRequest(request,token,chain);
                }
                return response;
            }
        };
        return interceptor;
    }

    private Response resetRequest(Request request, String localToken, Interceptor.Chain chain) {
        Request.Builder authorization = request.newBuilder().addHeader("Authorization", "bearer " + localToken);
        Request build = authorization.build();
        try {
            return chain.proceed(build);
        } catch (IOException e){
            LogUtils.INSTANCE.e(TAG,e.getMessage());
        }
        return null;

    }

    private String requestToken() {
        Call<TokenRespEntity> tokenService = create(Api.class)
                .getToken("password", Config.AUTH_CODE, "");
        try {
            retrofit2.Response<TokenRespEntity> execute = tokenService.execute();
            if (execute!=null&&execute.body()!=null){
                return execute.body().getAccess_token();
            }
        } catch (IOException e) {
            LogUtils.INSTANCE.e(TAG,e.getMessage());
        }

        return "";
    }

    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
