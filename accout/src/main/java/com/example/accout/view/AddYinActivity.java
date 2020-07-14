package com.example.accout.view;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.accout.R;
import com.example.accout.databinding.LayoutAddyinBinding;
import com.example.accout.entity.PaySelectEntity;
import com.example.accout.viewmodel.AddYinViewModel;
import com.example.core.view.BaseActivity;
import com.example.protocol.BasesRespEntity;
import com.example.router.RouterPath;
import com.example.wiget.CustomEditText;
import com.example.wiget.TitleBar;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

@Route(path = RouterPath.ADDYIN)
public class AddYinActivity extends BaseActivity<LayoutAddyinBinding, AddYinViewModel> {
    private CustomEditText cet1;
    private CustomEditText cet2;
    private CustomEditText cet3;
    private CustomEditText cet4;
    private CustomEditText cet5;
    private TitleBar barAddyin;


    private Spinner spinnerAdd;
    private EditText editAdd;
    private String type;
    @Override
    protected void initBinding() {
        binding.setAddyin(this);
        initPermission();
        initView();
        initData();
        initEvent();
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.CAMERA},102);
        }
    }

    private void initEvent() {
        barAddyin.setTitleBarClickListener(new TitleBar.TitleBarClickListener() {
            @Override
            public void leftClick(View view) {

            }

            @Override
            public void rightClick(View view) {
                customScan();
            }
        });

        spinnerAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type  = spinnerAdd.getItemAtPosition(i).toString();
                Toast.makeText(AddYinActivity.this, type, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void initData() {
        cet1.setNext(cet2);
        cet2.setNext(cet3);
        cet3.setNext(cet4);
        cet4.setNext(cet5);
        cet1.setCustomText("3201 2301 2540 6010 2000");
    }

    private void initView() {
        barAddyin = (TitleBar) findViewById(R.id.bar_addyin);
        cet1 = (CustomEditText) findViewById(R.id.cet_1);
        cet2 = (CustomEditText) findViewById(R.id.cet_2);
        cet3 = (CustomEditText) findViewById(R.id.cet_3);
        cet4 = (CustomEditText) findViewById(R.id.cet_4);
        cet5 = (CustomEditText) findViewById(R.id.cet_5);

        spinnerAdd = (Spinner) findViewById(R.id.spinner_add);
        editAdd = (EditText) findViewById(R.id.edit_add);
        ZXingLibrary.initDisplayOpinion(this);
    }
    public void customScan() {
        Intent intent = new Intent(AddYinActivity.this, CaptureActivity.class);
        startActivityForResult(intent, 100);
//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.CODE_39);
//        integrator.setPrompt("请扫描二维码");//底部的提示文字，设为""可以置空
//        integrator.setCameraId(0);//前置或者后置摄像头
//        integrator.setBeepEnabled(true);//扫描成功的「哔哔」声，默认开启
//        integrator.setBarcodeImageEnabled(true);
//        integrator.initiateScan();
    }
    @Override
    protected AddYinViewModel createVM() {
        return new AddYinViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_addyin;
    }


    public void addYin(View view){
        String trim = editAdd.getText().toString().trim();
        PaySelectEntity paySelectEntity = new PaySelectEntity();
        paySelectEntity.setId("1");
        paySelectEntity.setUserid("50");
        paySelectEntity.setPaytype(type);
        paySelectEntity.setPaytypeaccount(trim);
        LiveData<BasesRespEntity<Boolean>> data = vm.addYin(paySelectEntity);
        data.observe(this, new Observer<BasesRespEntity<Boolean>>() {
            @Override
            public void onChanged(BasesRespEntity<Boolean> booleanBasesRespEntity) {
                if (booleanBasesRespEntity.getData()){
                    setResult(100);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            if (null!=data){
                Bundle extras = data.getExtras();
                if (extras==null){
                    return;
                }if (extras.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS){
                    String string = extras.getString(CodeUtils.RESULT_STRING);
                    cet1.setCustomText(string);
                }
            }
        }
    }
}
