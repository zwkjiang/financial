package com.example.conmon.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class NetStateUtils {
    /**
     * 网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetWordAvailable(Context context){
        if (context!=null){
            ConnectivityManager contextSystemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = contextSystemService.getActiveNetworkInfo();
            if (activeNetworkInfo==null){
                return false;
            }
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }

    public static boolean isConnected(){
        URL url;
        InputStream stream = null;
        try {
            url = new URL("https://www.baidu.com");
            stream = url.openStream();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
