package com.example.home.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.conmon.app.BaseApplication;
import com.example.core.view.BaseFragment;
import com.example.core.viewmodel.BaseViewModel;
import com.example.home.R;
import com.example.home.databinding.LayoutHomeBinding;
import com.example.home.viewmodel.HomeViewModel;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<LayoutHomeBinding, HomeViewModel> implements OnBannerListener {
    private Banner bannerHome;
    private ViewFlipper vfHome;
    private RelativeLayout rlNewProject;
    private ImageView ivAdvHome;
    private TextView tvItemFfipper;
    public ObservableField<String> imgPath = new ObservableField<>();
    public HomeFragment() {
        imgPath.set("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658");
    }

    @Override
    protected void initBinding() {
        binding.setMine(this);
    }

    @Override
    protected HomeViewModel createVM() {
        return new HomeViewModel();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.layout_home;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initData();
    }

    private void initData(){
        List<String> imgPath=new ArrayList<>();
        imgPath.add("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658");
        imgPath.add("http://hbimg.b0.upaiyun.com/8a75ab36c175489634b6c8621eea02fd8c83bb82c3869-Waz6eO_fw658");
        imgPath.add("http://hbimg.b0.upaiyun.com/a2a321fb4e128e2327674fee6c3be76bb7d6f70929ca3-IVhr33_fw658");
        imgPath.add("http://hbimg.b0.upaiyun.com/861f92e7514b297b0cd5833b3ffb52f8df37b4ec218f8-BmVyhw_fw658");

        List<String> strings=new ArrayList<>();
        strings.add("金融产品1");
        strings.add("金融产品2");
        strings.add("金融产品3");
        strings.add("金融产品4");

        bannerHome.setImages(imgPath);
        bannerHome.setBannerTitles(strings);
        bannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        bannerHome.setImageLoader(new MyBannerLoader());
        bannerHome.setBannerAnimation(Transformer.CubeOut);
        bannerHome.setDelayTime(1000);
        bannerHome.isAutoPlay(true);
        bannerHome.setIndicatorGravity(Gravity.CENTER)
                .setOnBannerListener(this)
                .start();
        for (int i = 0;i<10;i++){
            View inflate = getLayoutInflater().inflate(R.layout.item_viewffipper, null);
            TextView textView = inflate.findViewById(R.id.tv_item_ffipper);
            textView.setText("系统通知，内容"+i);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView tv = (TextView) view;
                    Toast.makeText(getActivity(),""+tv.getText(),Toast.LENGTH_SHORT).show();
                }
            });
            vfHome.addView(inflate);
        }
        vfHome.setFlipInterval(2000);
        vfHome.startFlipping();
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(),"position="+position,Toast.LENGTH_SHORT).show();;
    }

    @BindingAdapter({"imgSrc"})
    public static void bindImagePath(ImageView view,String path){
        Glide.with(BaseApplication.getApplication()).load(path).into(view);
    }


    private class MyBannerLoader implements ImageLoaderInterface{

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context).load(path).into((ImageView) imageView);
        }

        @Override
        public View createImageView(Context context) {
            return null;
        }
    }

    @Override
    protected void initView(View view) {
        bannerHome = (Banner)view.findViewById(R.id.banner_home);
        vfHome = (ViewFlipper)view. findViewById(R.id.vf_home);
        rlNewProject = (RelativeLayout)view. findViewById(R.id.rl_newProject);
        ivAdvHome = (ImageView)view. findViewById(R.id.iv_adv_home);
        tvItemFfipper = (TextView)view. findViewById(R.id.tv_item_ffipper);
    }

}
