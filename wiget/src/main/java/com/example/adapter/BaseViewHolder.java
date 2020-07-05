package com.example.adapter;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    ViewDataBinding mBinging;
    public BaseViewHolder(@NonNull ViewDataBinding binding) {
        super(binding.getRoot());
        this.mBinging = binding;
    }

    public ViewDataBinding getBinging(){
        return mBinging;
    }
}
