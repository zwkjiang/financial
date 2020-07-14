package com.example.adapter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public Context context;

    public List<T> mDataSource;

    public BaseAdapter(Context _context){
        this.context = _context;
        this.mDataSource = new ArrayList<>();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createVH(parent,viewType);
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        bindVH(holder,position);
    }



    @Override
    public int getItemCount() {
        return this.mDataSource.size();
    }

    public void loadDataSource(List<T> _dataSource){
        if (this.mDataSource!=null&&_dataSource!=null){
            this.mDataSource.clear();
            this.mDataSource.addAll(_dataSource);

        }
    }

    public void appendDataSource(List<T> _dataSource){
        if (this.mDataSource!=null&&_dataSource!=null){
            this.mDataSource.addAll(_dataSource);
            notifyDataSetChanged();
        }
    }

    protected abstract VH createVH(ViewGroup parent, int viewType);
    protected abstract void bindVH(VH holder, int position);
}
