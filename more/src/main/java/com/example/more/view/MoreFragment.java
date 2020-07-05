package com.example.more.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.core.view.BaseFragment;
import com.example.core.viewmodel.BaseViewModel;
import com.example.more.R;
import com.example.more.databinding.LayoutMoreBinding;
import com.example.more.viewmodel.MoreViewModel;

public class MoreFragment extends BaseFragment<LayoutMoreBinding,MoreViewModel> {

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initBinding() {

    }

    @Override
    protected MoreViewModel createVM() {
        return new MoreViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_more;
    }
}
