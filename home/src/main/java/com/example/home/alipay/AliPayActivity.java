package com.example.home.alipay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.home.R;
import com.example.router.RouterPath;

import java.util.Map;

@Route(path = RouterPath.ALIPAY)
public class AliPayActivity extends AppCompatActivity {

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2016102400751982";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgMi+HJN+wFt7G/PfTP8KAnJIfilmvpm45v2LnaCz736uuC8LPPLh+9OuZQVcIkiqDoC0B0CVg2dK1kP5AmOzGqgM5P2VT7wMWQMXR00LQk9zF78btZP4qEkqUCUZ4ElRF+25YNWXb7q9AVgXT0ULYcy+KxbGLkTXUOL9dX/Hvj0SNo0LDrJAeoC/wUHcQDRRAq64ugng1YFf1rL5FGKogzpAkIei5VIp1KN2L0Ase0wpXL93h7iSeL8njoure1Kol11k/lkGNPrSoqbMTP9S/2N7QvgW5nlb81l5z4Tgqbvt7AzRWDSmtNXO+QrWujBqM+OyBvFtGBjQn2HQmYM01AgMBAAECggEAJbCJBBfF1Q0cbt63g+kdXtr3eho3zZ6vkkCYCW+2HbBbGQDFXbKgiaYgBBeeXXLeM8esrx+Em8vDyloJp7MTCg6X4ZErytqR9Fta72nv71jKeXtXZyT6s3VIPJoODVPqpeho5rHxfBQlAVL73gD+5CP9jH3ErtyEOKD9TP+00TKZKL8wc0QOOHh/JGREZ4oB/TKr7gzUe8SJ+KTvyMGMI/JJJCPWzftyDzY+HwLCDojEBC1cnORblH1xloBmRIVf50Zthhytw41+7d9D0M7LHHJG7oXx5jkFGJmghqXL6Y2YJHDbSBtDzqe7moPLMFaKOwe6yMrWXluy16YNmr4gAQKBgQDe4WtE4rMGuyJtAsROLzuNY6kAWWUmIDDfnzh5Hhw9LbF0zDcXIs+hfyjmV3Ik/xVJ4XW4tB2N32PAOJaB/1yAV6VHW64XSQhvtTpQmK8wd5VS7PoneI0e6IcdKP4rgJPR4rJCKh+LohofcJzdpBVKRxGvtFX22gptlULGnls1NQKBgQC4ADC7pG6lORNX5BO5xlz7eocaAUP/G7GCHSA8W5yYbKgJ0f+oAC0Dez/qGcVd9XTjoDDowb8/YicJdAbVvUo7j0xfSLMe9L8JNSVoSeAOAWmxkKLwCHgm1dImcOs1c3TkysF8KKo6RyaD7tJ19ySR63ONN9Suv+02K+XEGRo4AQKBgGWadYAtqFdFL+hp/nt15LwL1psmJYo/2ofW2bO9Gjp5fhA0mFifse95PHflHU/oSRgpA7VzxpVKZPrX0STp+I05Z0skbDSJQqNKBo9/Sz3ZBeWLym378rlIX2/XpCbIsppXsdOfyEmrWCVE/P29M0dtJo7mstmZhG94P7yzq/iRAoGBAJUPcmtQ1mH7TvRQzLofPIxtwq0KgW9VnAPwXIEJJl9FBwyNlzMekVjUq/6WShHIG52b1IN7EdfjSAScfj0BT/XYLOK0HofhdjGbYGaQEnbGJj23EoiqE8xCRDDoIWP32KbQqtZOizkTEtymyNDQGkwsFjw9EotO6HiOL2EhhOABAoGAUZKzN9Tk5/Hm6oN9Fc5qK4eIYcKeUyHqji2qTtmCY8ux9u63G1/bKOnZjQ+uPjNICPysyESazQBEtY2pC280tgMeHK3SENkLsfkwLKvDulsWpG/VB8AqO7R1poFwkHz+vGymQerz2qt9Jpgqoct7YCXll7V7RHMZE7v/FDea5U8=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(AliPayActivity.this, getString(R.string.pay_success) + payResult,Toast.LENGTH_LONG).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(AliPayActivity.this, getString(R.string.pay_failed) + payResult,Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(AliPayActivity.this, getString(R.string.auth_success) + authResult,Toast.LENGTH_LONG).show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(AliPayActivity.this, getString(R.string.auth_failed) + authResult,Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        payV2();

    }

    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            Toast.makeText(this, getString(R.string.error_missing_appid_rsa_private),Toast.LENGTH_LONG).show();
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(AliPayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
