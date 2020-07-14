package com.example.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;
import com.example.conmon.utils.LogUtils;

public class CustomEditText extends AppCompatEditText {
    private final String TAG = CustomEditText.class.getSimpleName();

    private CustomEditText previous;

    private CustomEditText next;

    private int maxLength = 0;

    private int txtSize = 0;



    public CustomEditText(Context context) {
        super(context);

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        doCustomProp(context,attrs);
        initControllerConfig();
    }

    private void doCustomProp(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        if (typedArray!=null){
            maxLength = typedArray.getInteger(R.styleable.CustomEditText_cet_maxlength,4);
            txtSize = (int) typedArray.getDimension(R.styleable.CustomEditText_cet_txtsize,30);
        }
        typedArray.recycle();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initControllerConfig() {
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setTextSize(txtSize);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        this.addTextChangedListener(new CustomTextWatcher());
    }

    public void setNext(CustomEditText next) {
        this.next = next;
        next.previous = this;
    }

    public void setCustomText(String _content){
        if (TextUtils.isEmpty(_content)){
            LogUtils.INSTANCE.w(TAG,"setCustomText method params _content is null...");
            return;
        }
        String[] strArray = _content.split(" ");
        if (strArray==null||strArray.length==0){
            LogUtils.INSTANCE.w(TAG,"strArray is null or length = 0");
            return;
        }
        String tempTxt = "";
        for (int i=0;i<strArray.length;i++){
            if (TextUtils.isEmpty(strArray[i].trim())){
                continue;
            }
            tempTxt = strArray[i];
            setText(tempTxt);
            break;
        }
        String substring = _content.substring(_content.indexOf(tempTxt) + tempTxt.length());
        if (next!=null){
            next.setCustomText(substring);
        }
        setSelection(getText().length());
    }

    private class CustomTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String s = getText().toString();
            int length = s.length();
            if (length>=maxLength){
                setTextColor(Color.LTGRAY);
                if (next!=null){
                    LogUtils.INSTANCE.i(TAG,"afterTextChanged");
                    clearFocus();
                    next.requestFocus();
                }
            }else if (length==0){
                if (previous!=null){
                    LogUtils.INSTANCE.i(TAG,"afterTextChanged");
                    clearFocus();
                    previous.requestFocus();
                }
            }
        }
    }
}
