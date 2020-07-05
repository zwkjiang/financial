package com.example.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import org.w3c.dom.Text;

public class TitleBar extends RelativeLayout implements View.OnClickListener {

    private TitleBarClickListener titleBarClickListener;
    private TextView left;
    private TextView title;
    private TextView right;

    private final int DEFAULT_ICON_WH=50;


    public TitleBar(Context context) {
        super(context);
    }
    public void setTitleBarClickListener(TitleBarClickListener titleBarClickListener) {
        this.titleBarClickListener = titleBarClickListener;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_titlebar, null, false);
        left = view.findViewById(R.id.view_titlebar_left);
        right = view.findViewById(R.id.view_titlebar_right);
        title = view.findViewById(R.id.view_titlebar_title);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        doCustomProps(context,attrs);
        addView(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void doCustomProps(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        if (typedArray!=null){
            String left_txt = typedArray.getString(R.styleable.TitleBar_left_txt);

            int left_icon = typedArray.getResourceId(R.styleable.TitleBar_left_icon, 0);
            boolean left_icon_visible = typedArray.getBoolean(R.styleable.TitleBar_left_iocn_visible, false);

            String title_txt = typedArray.getString(R.styleable.TitleBar_title_txt);
            float title_txt_size = typedArray.getFloat(R.styleable.TitleBar_title_txt_size,20F);

            String right_txt = typedArray.getString(R.styleable.TitleBar_right_txt);
            int right_icon = typedArray.getResourceId(R.styleable.TitleBar_right_icon, 0);
            boolean right_icon_visible = typedArray.getBoolean(R.styleable.TitleBar_right_icon_visible, false);

            int icon_wh = typedArray.getInteger(R.styleable.TitleBar_icon_wh, DEFAULT_ICON_WH);
            if (!TextUtils.isEmpty(left_txt)){
                left.setText(left_txt);
            }
            if (left_icon_visible){
                left.setVisibility(View.VISIBLE);
            }
            else{
                left.setVisibility(GONE);
            }
            if (!TextUtils.isEmpty(title_txt)){
                title.setText(title_txt);
            }
            title.setTextSize(title_txt_size);
            if (!TextUtils.isEmpty(right_txt)){
                right.setText(right_txt);
            }
            if (right_icon!=0){
                Drawable drawable = context.getDrawable(right_icon);
                drawable.setBounds(0,0,icon_wh,icon_wh);
                right.setCompoundDrawables(drawable,null,null,null);
            }
            if (right_icon_visible){
                right.setVisibility(VISIBLE);
            }else{
                right.setVisibility(INVISIBLE);
            }
        }
        typedArray.recycle();
    }

    public void setLeftVisible(boolean isVisible){
        this.right.setVisibility(isVisible?VISIBLE:INVISIBLE);
    }

    public void setRightVisible(boolean isVisible){
        this.right.setVisibility(isVisible?VISIBLE:INVISIBLE);
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.view_titlebar_left){
            if (this.titleBarClickListener!=null){
                this.titleBarClickListener.leftClick(view);
            }
        }else if(view.getId()==R.id.view_titlebar_right){
            if (this.titleBarClickListener!=null){
                this.titleBarClickListener.rightClick(view);
            }
        }
    }

    public interface TitleBarClickListener{
        void leftClick(View view);
        void rightClick(View view);
    }
    public void setTitle(String _title){
        title.setText(_title);
    }
}
