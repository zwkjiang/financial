package com.example.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.example.adapter.BaseAdapter;
import com.example.adapter.BaseViewHolder;
import com.example.home.R;

public class InvestAdapter extends BaseAdapter<String, BaseViewHolder> {

    public InvestAdapter(Context _context) {
        super(_context);
    }

    @Override
    protected BaseViewHolder createVH(ViewGroup parent, int viewType) {
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.comment_view, parent, false);
        return new BaseViewHolder(inflate);
    }

    @Override
    protected void bindVH(BaseViewHolder holder, int position) {

    }
}
