package com.example.accout.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.accout.R;
import com.example.accout.adapter.PaySelectAdapter;
import com.example.accout.databinding.LayoutPayselectBinding;
import com.example.accout.entity.PaySelectEntity;
import com.example.accout.viewmodel.PaySelectViewModel;
import com.example.conmon.async.CacheThreadPool;
import com.example.conmon.utils.LogUtils;
import com.example.core.view.BaseActivity;
import com.example.protocol.BasesRespEntity;
import com.example.router.RouterManager;
import com.example.router.RouterPath;
import com.example.wiget.TitleBar;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.PAYSELECT)
public class PaySelectActivity extends BaseActivity<LayoutPayselectBinding,PaySelectViewModel> {
    private TitleBar barPaySelect;
    private RecyclerView recyclerPaySelect;
    private CheckBox allPaySelect;
    private TextView deletePaySelect;
    private PaySelectAdapter adapter;
    private List<PaySelectEntity> listData;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void initBinding() {
        binding.setPselect(this);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        allPaySelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    adapter.setType(1);
                }else{
                    adapter.setType(0);
                }
                adapter.notifyDataSetChanged();
            }
        });
        deletePaySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setDelete(true);
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setCallBackT(new PaySelectAdapter.CallBackT() {
            @Override
            public void getPosition(int position) {
                listData.remove(position);
                adapter.setDelete(false);
                adapter.loadDataSource(listData);
                CacheThreadPool.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            handler.sendEmptyMessage(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                LogUtils.INSTANCE.i("ADA","getPosition");
            }
        });
    }

    private void initData() {
        if (adapter == null){
            adapter = new PaySelectAdapter(this);
            recyclerPaySelect.setAdapter(adapter);
        }
        if (listData==null){
            listData = new ArrayList<>();
        }
        LiveData<BasesRespEntity<List<PaySelectEntity>>> yin = vm.getYin();
        yin.observe(this, new Observer<BasesRespEntity<List<PaySelectEntity>>>() {
            @Override
            public void onChanged(BasesRespEntity<List<PaySelectEntity>> listBasesRespEntity) {
                if (listBasesRespEntity.getData()!=null){
                    List<PaySelectEntity> data = listBasesRespEntity.getData();
                    listData.clear();
                    listData.addAll(data);
                    adapter.loadDataSource(listData);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void goAdd(View view){
        RouterManager.getInstance().route(RouterPath.ADDYIN,101,this);
    }

    private void initView() {
        deletePaySelect = (TextView) findViewById(R.id.delete_paySelect);
        barPaySelect = (TitleBar) findViewById(R.id.bar_paySelect);
        recyclerPaySelect = (RecyclerView) findViewById(R.id.recycler_paySelect);
        allPaySelect = (CheckBox) findViewById(R.id.all_paySelect);
        recyclerPaySelect.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected PaySelectViewModel createVM() {
        return new PaySelectViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_payselect;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101&&resultCode==100){
            initData();
        }
    }
}
