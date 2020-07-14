package com.example.accout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.accout.BR;
import com.example.accout.R;
import com.example.accout.entity.PaySelectEntity;
import com.example.adapter.BaseAdapter;
import com.example.adapter.BaseViewHolder;
import com.example.conmon.utils.LogUtils;

public class PaySelectAdapter extends BaseAdapter<PaySelectEntity, BaseViewHolder> {
    private int type;
    private boolean delete;
    private CallBackT callBackT;

    public void setCallBackT(CallBackT callBackT) {
        this.callBackT = callBackT;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PaySelectAdapter(Context _context) {
        super(_context);
    }

    @Override
    protected BaseViewHolder createVH(ViewGroup parent, int viewType) {
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_view, parent, false);
        return new BaseViewHolder(inflate);
    }

    @Override
    protected void bindVH(BaseViewHolder holder, int position) {
        ViewDataBinding binging = holder.getBinging();
        TextView viewById = binging.getRoot().findViewById(R.id.txt);
        String paytype = mDataSource.get(position).getPaytype();
        switch (paytype){
            case "0":viewById.setText("微信");
                break;
            case "1":viewById.setText("支付宝");
                break;
            case "200":viewById.setText("中国银行");
                break;
            case "201":viewById.setText("建设银行");
                break;
            case "202":viewById.setText("招商银行");
                break;
            case "203":viewById.setText("农业银行");
                break;

        }
        binging.setVariable(BR.payEn,mDataSource.get(position));
        CheckBox box = binging.getRoot().findViewById(R.id.check);
        if (delete&&box.isChecked()){
            LogUtils.INSTANCE.i("ADA","isChecked");
            if (callBackT!=null){
                LogUtils.INSTANCE.i("ADA","getPosition");
                callBackT.getPosition(position);
            }
        }
        if (type==0){
            box.setChecked(false);
        }else{
            box.setChecked(true);
        }


    }
    public interface CallBackT{
        void getPosition(int position);
    }
}
