package com.example.home.view;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.conmon.utils.LogUtils;
import com.example.core.view.BaseActivity;
import com.example.core.viewmodel.BaseViewModel;
import com.example.home.R;
import com.example.home.adapter.InvestAdapter;
import com.example.home.databinding.LayoutInvestBinding;
import com.example.home.entity.TabEntity;
import com.example.home.viewmodel.InvestViewModel;
import com.example.router.RouterManager;
import com.example.router.RouterPath;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;
import java.util.HashMap;

@Route(path = RouterPath.INVEST)
public class InvestActivity extends BaseActivity<LayoutInvestBinding, InvestViewModel> {
    private CommonTabLayout tabInvest;
    private ArrayList<CustomTabEntity> listTab;
    private RecyclerView recyclerInvest;

    @Autowired
    public String rateNewProject;
    @Autowired
    public String SPriceProject;

    private InvestAdapter investAdapter;

    public String price;

    @Override
    protected void initBinding() {
        ARouter.getInstance().inject(this);
        binding.setInvest(this);
        initView();
        initData();
    }

    private void initData() {
        initTab();
        initComment();
    }

    private void initComment() {
        if (investAdapter==null){
            investAdapter = new InvestAdapter(this);
             recyclerInvest.setAdapter(investAdapter);
        }
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        investAdapter.loadDataSource(list);
    }

    private void initTab() {
        if (listTab==null) {
            listTab = new ArrayList<>();
            listTab.add(new TabEntity("全部"));
            listTab.add(new TabEntity("热度"));
            tabInvest.setTabData(listTab);
        }
    }

    private void initView() {
        recyclerInvest = (RecyclerView) findViewById(R.id.recycler_invest);
        tabInvest = (CommonTabLayout) findViewById(R.id.tab_invest);
        recyclerInvest.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected InvestViewModel createVM() {
        return new InvestViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_invest;
    }

    public  void goPayment(View view){
        HashMap<String, Object> data = new HashMap<>();
        data.put("price",price);
        RouterManager.getInstance().route(RouterPath.PAYMENT,data);
    }
}
