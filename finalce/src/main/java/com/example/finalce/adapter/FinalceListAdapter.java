package com.example.finalce.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.adapter.BaseAdapter;
import com.example.adapter.BaseViewHolder;
import com.example.finalce.BR;
import com.example.finalce.R;
import com.example.finalce.entity.FinalceEntity;

public class FinalceListAdapter extends BaseAdapter<FinalceEntity, BaseViewHolder> {

    private RatingBar bar;
    public FinalceListAdapter(Context _context) {
        super(_context);
    }

    @Override
    protected BaseViewHolder createVH(ViewGroup parent, int viewType) {
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_finalce, parent, false);
        bar = inflate.getRoot().findViewById(R.id.rb_bar);
        doBarHeight(bar);
        return new BaseViewHolder(inflate);
    }

    private void doBarHeight(RatingBar bar) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.start_checked);
        int height = bitmap.getHeight();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height);
        bar.setLayoutParams(layoutParams);
    }

    @Override
    protected void bindVH(BaseViewHolder holder, int position) {
        ViewDataBinding binging = holder.getBinging();
        binging.setVariable(BR.finalce,mDataSource.get(position));
    }
}
