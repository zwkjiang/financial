package com.example.finalce.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.conmon.utils.LogUtils;
import com.example.core.view.BaseFragment;
import com.example.finalce.R;
import com.example.finalce.adapter.FinalceListAdapter;
import com.example.finalce.databinding.LayoutFinalceBinding;
import com.example.finalce.entity.FinalceEntity;
import com.example.finalce.viewmodel.FinalceViewModel;
import com.example.protocol.BasesRespEntity;
import com.example.wiget.FinalceProcessBar;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class FinalceFragment extends BaseFragment<LayoutFinalceBinding, FinalceViewModel> {
    private SmartRefreshLayout srlFinalceMain;
    private TabLayout tabLayoutMain;
    private RecyclerView rvFinalceMain;
    private FinalceListAdapter adapter;

    private final String TAG = FinalceFragment.class.getSimpleName();

    private int currentType = 0;
    private int currentPage = 0;
    private int pageSize = 10;

    @Override
    protected void initData(Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvFinalceMain.setLayoutManager(linearLayoutManager);
        adapter = new FinalceListAdapter(getActivity());
        rvFinalceMain.setAdapter(adapter);
       initFinalce(false);
    }


    @Override
    protected void initBinding() {

    }
    private void initEvent() {
        tabLayoutMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().toString().equals("新手标")){
                    currentType = 0;
                    currentPage = 0;
                }else if (tab.getText().toString().equals("理财标")){
                    currentType = 1;
                    currentPage = 0;
                }else{
                    currentType = 2;
                    currentPage = 0;
                }
                initFinalce(false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        srlFinalceMain.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                currentPage++;
                initFinalce(true);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currentPage = 0;
                initFinalce(false);
                refreshLayout.finishRefresh(2000/*,false*/);
            }
        });
    }
    private void initFinalce(final boolean is) {
        LiveData<BasesRespEntity<List<FinalceEntity>>> finalce = vm.getFinalce(currentType, currentPage, pageSize);
        finalce.observe(this, new Observer<BasesRespEntity<List<FinalceEntity>>>() {
            @Override
            public void onChanged(BasesRespEntity<List<FinalceEntity>> listBasesRespEntity) {
                if (listBasesRespEntity==null||listBasesRespEntity.getData()==null){
                    LogUtils.INSTANCE.i(TAG,"null");
                    return;
                }
                if (!is){
                    adapter.loadDataSource(listBasesRespEntity.getData());
                }else {
                    adapter.appendDataSource(listBasesRespEntity.getData());
                }
            }
        });
    }
    @BindingAdapter({"sweepangle","txtcontent"})
    public static void setContent(FinalceProcessBar bar,String totalmount,String selamount){
        float total = Float.parseFloat(totalmount);
        float sela = Float.parseFloat(selamount);
        float result = (total-sela)/total;
        bar.setTxtContent(result*100+"%");
        bar.setSweepAngle(360*result);
        bar.startAnimator(10000);
        bar.startContextAnimator(10000,result*100);
        bar.setBarClickListener(new FinalceProcessBar.FinalceParoessBarClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.INSTANCE.i("","FinalceProcessBar click....");
            }
        });

    }
    @Override
    protected FinalceViewModel createVM() {
        return new FinalceViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_finalce;
    }
    @Override
    protected void initView(View view) {
        srlFinalceMain = (SmartRefreshLayout)view. findViewById(R.id.srl_finalce_main);
        tabLayoutMain = (TabLayout)view.findViewById(R.id.tabLayout_main);
        rvFinalceMain = (RecyclerView) view. findViewById(R.id.rv_finalce_main);
        initEvent();
    }


}
