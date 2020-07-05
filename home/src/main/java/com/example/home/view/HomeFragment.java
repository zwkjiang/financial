package com.example.home.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.bumptech.glide.Glide;
import com.example.conmon.app.BaseApplication;
import com.example.conmon.utils.LogUtils;
import com.example.core.view.BaseFragment;
import com.example.home.R;
import com.example.home.databinding.LayoutHomeBinding;
import com.example.home.entity.BannerEntity;
import com.example.home.entity.ProductEntity;
import com.example.home.entity.SysMsgEntity;
import com.example.home.viewmodel.HomeViewModel;
import com.example.protocol.BasesRespEntity;
import com.example.router.RouterManager;
import com.example.router.RouterPath;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends BaseFragment<LayoutHomeBinding, HomeViewModel> implements OnBannerListener {
    private final String TAG = HomeFragment.class.getSimpleName();
    private Banner bannerHome;
    private ViewFlipper vfHome;
    private RelativeLayout rlNewProject;
    private ImageView ivAdvHome;
    private TextView tvItemFfipper;
    private TextView pbNewProject;
    private TextView rateNewProject;
    private TextView SPriceProject;
    private TextView IDateNewProject;
    private TextView oneProject;
    private TextView one1Project;
    private TextView one3;
    private TextView one4Project;
    private TextView twoProject;
    private TextView two1Project;
    private TextView two3;
    private TextView two4Project;
    private TextView threeProject;
    private TextView three1Project;
    private TextView three3;
    private TextView three4Project;
    private TextView fourProject;
    private TextView four1Project;
    private TextView four3;
    private TextView four4Project;
    private Button goinvestHome;
    public ObservableField<String> imgPath = new ObservableField<>();
    public HomeFragment() {
        imgPath.set("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658");
    }

    @Override
    protected void initBinding() {
        binding.setMine(this);
    }

    private void initEvent() {
        goinvestHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("rateNewProject",rateNewProject.getText().toString().trim());
                data.put("SPriceProject",SPriceProject.getText().toString().trim());
                RouterManager.getInstance().route(RouterPath.INVEST,data);
            }
        });
    }

    @Override
    protected HomeViewModel createVM() {
        return new HomeViewModel(this);
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
        initBannerData();
        initSysMesg();
        initNewProject();
        initProject();
    }

    private void initProject() {
        final LiveData<BasesRespEntity<List<ProductEntity>>> product = vm.getProduct();
        product.observe(this, new Observer<BasesRespEntity<List<ProductEntity>>>() {
            @Override
            public void onChanged(BasesRespEntity<List<ProductEntity>> listBasesRespEntity) {
                if (listBasesRespEntity.getData()!=null){
                    List<ProductEntity> data = listBasesRespEntity.getData();
                    ProductEntity productEntity = data.get(0);
                    ProductEntity productEntity1 = data.get(1);
                    ProductEntity productEntity2 = data.get(2);
                    ProductEntity productEntity3 = data.get(3);
                    oneProject.setText(productEntity.getProductname());
                    one1Project.setText(productEntity.getProductdesc());
                    one3.setText((productEntity.getYearrate()*100)+"");
                    one4Project.setText(productEntity.getLockdays()+"天");

                    twoProject.setText(productEntity1.getProductname());
                    two1Project.setText(productEntity1.getProductdesc());
                    two3.setText((productEntity1.getYearrate()*100)+"");
                    two4Project.setText(productEntity1.getLockdays()+"天");

                    threeProject.setText(productEntity2.getProductname());
                    three1Project.setText(productEntity2.getProductdesc());
                    three3.setText((productEntity2.getYearrate()*100)+"");
                    three4Project.setText(productEntity2.getLockdays()+"天");

                    fourProject.setText(productEntity3.getProductname());
                    four1Project.setText(productEntity3.getProductdesc());
                    four3.setText((productEntity3.getYearrate()*100)+"");
                    four4Project.setText(productEntity3.getLockdays()+"天");
                }
            }
        });
    }

    private void initNewProject() {
        LiveData<BasesRespEntity<List<ProductEntity>>> newProduct = vm.getNewProduct();
        newProduct.observe(this, new Observer<BasesRespEntity<List<ProductEntity>>>() {
            @Override
            public void onChanged(BasesRespEntity<List<ProductEntity>> listBasesRespEntity) {
                if (listBasesRespEntity.getData()!=null){
                    List<ProductEntity> data = listBasesRespEntity.getData();
                    ProductEntity productEntity = data.get(0);
                    pbNewProject.setText(productEntity.getProductname());
                    rateNewProject.setText((productEntity.getYearrate()*100)+"");
                    SPriceProject.setText(productEntity.getMinbugamount()+"元");
                    IDateNewProject.setText(productEntity.getLockdays()+"天");
                }
            }
        });
    }

    private void initSysMesg() {
        LiveData<BasesRespEntity<List<SysMsgEntity>>> systemMegs = vm.getSystemMegs();
        systemMegs.observe(this, new Observer<BasesRespEntity<List<SysMsgEntity>>>() {
            @Override
            public void onChanged(BasesRespEntity<List<SysMsgEntity>> listBasesRespEntity) {
                List<SysMsgEntity> data = listBasesRespEntity.getData();
                if (data!=null){
                    for (int i = 0;i<data.size();i++){
                        View inflate = getLayoutInflater().inflate(R.layout.item_viewffipper, null);
                        TextView textView = inflate.findViewById(R.id.tv_item_ffipper);
                        textView.setText(data.get(i).getMsg());
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
            }
        });

    }

    private void initBannerData() {
        LiveData<BasesRespEntity<List<BannerEntity>>> banner = vm.getBanner();
        banner.observe(this, new Observer<BasesRespEntity<List<BannerEntity>>>() {
            @Override
            public void onChanged(BasesRespEntity<List<BannerEntity>> listBasesRespEntity) {
                if (listBasesRespEntity==null||listBasesRespEntity.getData()==null){
                    LogUtils.INSTANCE.i(TAG,"the banner is null");
                    return;
                }
                List<String> imgs=new ArrayList<>();
                List<String> titles=new ArrayList<>();
                for (BannerEntity entity:
                        listBasesRespEntity.getData()) {
                    imgs.add(entity.getImgurl());
                    titles.add(entity.getDesc());
                }
                bindBannerControl(imgs,titles);
            }
        });
    }

    private void bindBannerControl(List<String> imgs, List<String> titles) {
        bannerHome.setImages(imgs);
        bannerHome.setBannerTitles(titles);
        bannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        bannerHome.setImageLoader(new MyBannerLoader());
        bannerHome.setBannerAnimation(Transformer.CubeOut);
        bannerHome.setDelayTime(1000);
        bannerHome.isAutoPlay(true);
        bannerHome.setIndicatorGravity(Gravity.CENTER)
                .setOnBannerListener(this)
                .start();
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

        pbNewProject = (TextView)view. findViewById(R.id.pb_newProject);
        rateNewProject = (TextView)view. findViewById(R.id.rate_newProject);
        SPriceProject = (TextView) view.findViewById(R.id.SPrice_Project);
        IDateNewProject = (TextView) view.findViewById(R.id.IDate_newProject);

        oneProject = (TextView)view. findViewById(R.id.one_project);
        one1Project = (TextView) view.findViewById(R.id.one1_project);
        one3 = (TextView) view.findViewById(R.id.one3);
        one4Project = (TextView)view. findViewById(R.id.one4_project);

        twoProject = (TextView) view.findViewById(R.id.two_project);
        two1Project = (TextView)view. findViewById(R.id.two1_project);
        two3 = (TextView) view.findViewById(R.id.two3);
        two4Project = (TextView)view. findViewById(R.id.two4_project);

        threeProject = (TextView) view.findViewById(R.id.three_project);
        three1Project = (TextView)view. findViewById(R.id.three1_project);
        three3 = (TextView)view. findViewById(R.id.three3);
        three4Project = (TextView)view. findViewById(R.id.three4_project);

        fourProject = (TextView) view.findViewById(R.id.four_project);
        four1Project = (TextView)view. findViewById(R.id.four1_project);
        four3 = (TextView) view.findViewById(R.id.four3);
        four4Project = (TextView)view. findViewById(R.id.four4_project);
        goinvestHome = (Button)view. findViewById(R.id.goinvest_home);
        initEvent();

    }

}
