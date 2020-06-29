package com.example.core.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import com.example.core.viewmodel.BaseViewModel;

public abstract class BaseFragment<Binding extends ViewDataBinding,VM extends BaseViewModel> extends Fragment {
    protected Binding binding;
    protected VM vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        View view = binding.getRoot();
        vm = createVM();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBinding();
        initView(view);
        initData(savedInstanceState);
    }

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initView(View view);

    protected abstract void initBinding();

    protected abstract VM createVM();

    protected abstract int getLayoutId();
}
