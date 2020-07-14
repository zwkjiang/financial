package com.example.accout.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.accout.R;
import com.example.accout.databinding.LayoutAccoutBinding;
import com.example.accout.viewmodel.AccountViewModel;
import com.example.core.view.BaseFragment;
import com.example.core.viewmodel.BaseViewModel;
import com.example.router.RouterManager;
import com.example.router.RouterPath;

public class AccountFragment extends BaseFragment<LayoutAccoutBinding, AccountViewModel> {
    private LinearLayout selectPayAccout;
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View view) {
        selectPayAccout = (LinearLayout) view.findViewById(R.id.selectPay_accout);
        initEvent();
    }

    private void initEvent() {
        selectPayAccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RouterManager.getInstance().route(RouterPath.PAYSELECT);
            }
        });
    }

    @Override
    protected void initBinding() {

        binding.setAccout(this);
    }

    @Override
    protected AccountViewModel createVM() {
        return new AccountViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_accout;
    }

    public static void goBuy (View view){
        RouterManager.getInstance().route(RouterPath.TEXT);
    }

}
