package com.example.home.view;

import android.view.View;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.conmon.app.BaseApplication;
import com.example.conmon.utils.MsgUtils;
import com.example.core.view.BaseActivity;
import com.example.home.R;
import com.example.home.databinding.LayoutPaymentBinding;
import com.example.home.entity.PayEntity;
import com.example.home.viewmodel.PaymentViewModel;
import com.example.protocol.BasesRespEntity;
import com.example.router.RouterManager;
import com.example.router.RouterPath;

@Route(path = RouterPath.PAYMENT)
public class PaymentActivity extends BaseActivity<LayoutPaymentBinding, PaymentViewModel> {
    public String loadImg;
    @Autowired
    public String price;
    public PaymentActivity() {
        loadImg = "http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658";
    }

    @Override
    protected void initBinding() {
        ARouter.getInstance().inject(this);
        binding.setPay(this);
    }

    @Override
    protected PaymentViewModel createVM() {
        return new PaymentViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_payment;
    }

    @BindingAdapter({"imgSrc"})
    public static void loadImg(ImageView imageView,String path){
        Glide.with(BaseApplication.getApplication()).load(path).into(imageView);
    }
   public void goAli(View view){
       PayEntity payEntity = new PayEntity();
       payEntity.setId(1);
       payEntity.setPaytype(0);
       payEntity.setUserid(1);
       payEntity.setTransactionvalue(220F);
       LiveData<BasesRespEntity<Boolean>> buy = vm.getBuy(payEntity);
       buy.observe(this, new Observer<BasesRespEntity<Boolean>>() {
           @Override
           public void onChanged(BasesRespEntity<Boolean> booleanBasesRespEntity) {
               Boolean data = booleanBasesRespEntity.getData();
               if (data){
                   MsgUtils.INSTANCE.show(BaseApplication.getApplication(),"ok");
               }
           }
       });
       RouterManager.getInstance().route(RouterPath.ALIPAY);
   }
}
