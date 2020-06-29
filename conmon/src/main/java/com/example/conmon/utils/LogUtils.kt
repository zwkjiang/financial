package com.example.conmon.utils

import android.util.Log

object LogUtils {

    private const val TAG = "snake"
    private const val isDebug = true;

    fun d(tag:String,log:String?){
        if (isDebug){
            if(tag==""){
                Log.d(TAG,log)
            }else{
                Log.d(TAG,"$tag : $log")
            }
        }
    }
    fun e(tag:String,log:String?){
        if (isDebug){
            if(tag==""){
                Log.e(TAG,log)
            }else{
                Log.e(TAG,"$tag : $log")
            }
        }
    }
    fun w(tag:String,log:String?){
        if (isDebug){
            if(tag==""){
                Log.w(TAG,log)
            }else{
                Log.w(TAG,"$tag : $log")
            }
        }
    }
    fun v(tag:String,log:String?){
        if (isDebug){
            if(tag==""){
                Log.v(TAG,log)
            }else{
                Log.v(TAG,"$tag : $log")
            }
        }
    }
    fun i(tag:String,log:String?){
        if (isDebug){
            if(tag==""){
                Log.i(TAG,log)
            }else{
                Log.i(TAG,"$tag : $log")
            }
        }
    }
}