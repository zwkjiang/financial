package com.example.router;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.conmon.app.BaseApplication;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class RouterManager {
    private volatile static RouterManager instance=null;
    private RouterManager(){

    }
    public static RouterManager getInstance(){
        if(instance==null){
            synchronized (RouterManager.class){
                if(instance==null){
                    instance = new RouterManager();
                }
            }
        }
        return instance;
    }
    public void init(Application application,boolean isDebug){
        if (isDebug){
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(application);
    }
    public void route(String path){
        ARouter.getInstance().build(path).navigation();
    }
    public void route(String path,int code,Activity activity){
        ARouter.getInstance().build(path).navigation(activity ,code);
    }
    public void route(String path, Map<String,Object> params){
        Postcard build = ARouter.getInstance().build(path);
        if (params!=null&&params.size()>0){
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry<String, Object> set:entries){
                Object value = set.getValue();
                if (value instanceof Boolean){
                    build.withBoolean(set.getKey(), (Boolean) value);
                }else if (value instanceof Bundle){
                    build.withBundle(set.getKey(), (Bundle) value);
                }else if (value instanceof Integer){
                    build.withInt(set.getKey(), (Integer) value);
                }else if (value instanceof Double){
                    build.withDouble(set.getKey(), (Double) value);
                }else if (value instanceof Float){
                    build.withString(set.getKey(), (String) value);
                }else if (value instanceof Parcelable){
                    build.withParcelable(set.getKey(), (Parcelable) value);
                }else if (value instanceof Serializable){
                    build.withSerializable(set.getKey(), (Serializable) value);
                }
            }
        }
        build.navigation();
    }
    /**
     * 普通Activity路由
     * @param context
     * @param intent
     */
    public void route(Context context, Intent intent){
        context.startActivity(intent);
    }

}
