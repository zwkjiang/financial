package com.example.wiget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.example.conmon.utils.LogUtils;

import javax.crypto.spec.DESedeKeySpec;

public class FinalceProcessBar extends View {


    /**
     * 边框宽度
     */
    private Paint storkPaint;
    /**
     * 画笔
     */
    private Paint txtPaint;
    /**
     * 背景画笔
     */
    private Paint backgroundPaint;

    /**
     * 默认线条颜色
     */
    private int defaultColor = Color.YELLOW;
    /**
     * 默认字体大小
     */
    private float defaultTextSize = 20.0f;
    /**
     * 默认线条宽度
     */
    private float defaultStorkWidth = 5.0f;
    /**
     * 文本颜色
     */
    private int txtColor;
    /**
     * 文本大小
     */
    private float txtSize;
    /**
     * 边框颜色
     */
    private int storkColor;
    /**
     * 边框大小
     */
    private float storkWidth;
    /**
     * 默认控件的宽与高
     */
    private int default_width=100;
    private int default_height=100;

    /**
     * 默认的相对角度
     */
    private float sweepAngle=360;

    /**
     * 文本显示内容
     */
    private String txtContent="";

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
    }

    public FinalceProcessBar(Context context) {
        super(context);
    }

    public FinalceProcessBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        doCustomProp(context,attrs);
        initPaint();
    }

    private void initPaint() {
        storkPaint = new Paint();
        txtPaint = new Paint();
        backgroundPaint = new Paint();

        storkPaint.setColor(storkColor==0?defaultColor:storkColor);
        storkPaint.setStyle(Paint.Style.STROKE);
        storkPaint.setStrokeWidth(storkWidth==0?defaultStorkWidth:storkWidth);
        storkPaint.setAntiAlias(true);
        storkPaint.setDither(true);

        backgroundPaint.setColor(Color.LTGRAY);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(storkWidth==0?defaultStorkWidth:storkWidth);
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setDither(true);

        txtPaint.setColor(txtColor==0?defaultColor:txtColor);
        txtPaint.setTextSize(txtSize==0?defaultTextSize:txtSize);
        txtPaint.setAntiAlias(true);
        txtPaint.setDither(true);
        txtPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void doCustomProp(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FinalceProcessBar);
        if (typedArray!=null){
           txtColor = typedArray.getColor(R.styleable.FinalceProcessBar_textColor,defaultColor);
           txtSize = typedArray.getDimension(R.styleable.FinalceProcessBar_textSize,defaultTextSize);

           storkColor = typedArray.getColor(R.styleable.FinalceProcessBar_stork_color,defaultColor);
           storkWidth = typedArray.getDimension(R.styleable.FinalceProcessBar_stork_width,defaultStorkWidth);

           sweepAngle = typedArray.getFloat(R.styleable.FinalceProcessBar_sweepangle,sweepAngle);
           txtContent = typedArray.getString(R.styleable.FinalceProcessBar_txtcontent);
        }
        typedArray.recycle();
    }

    /**
     *widthMeasureSpec|heightMeasureSpec 是一个32位的整形 前两位代表Mode 后30位是具体的大小值
     * Mode 有3中模式
     * EXACTLY - match_parent
     * AT_MOST - wrap_content
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(default_width,default_height);
        }else if (widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(default_width,heightSize);
        }else if (heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,default_height);
        }
    }

    public void startAnimator(long duration){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, this.sweepAngle);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                sweepAngle = value;
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    invalidate();
                }else{
                    postInvalidate();
                }
            }
        });
        valueAnimator.start();
    }

    public void startContextAnimator(long duration,float endValue){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, endValue);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float result = (float) valueAnimator.getAnimatedValue();
                if (result%1==0){
                    setTxtContent(result+"%");
                }
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    invalidate();
                }else{
                    postInvalidate();
                }
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 确定圆弧的绘制范围 最小外接矩形 并考虑padding的因素
         */
        float left = 0.0f + getPaddingLeft();
        float top = 0.0f +getPaddingTop();
        float right = this.getMeasuredWidth()-getPaddingRight();
        float bottom = this.getMeasuredHeight() - getPaddingBottom();
        RectF rectF = new RectF(left, top, right, bottom);
        //圆弧背景
        canvas.drawArc(rectF,0,360,false,backgroundPaint);
        //绘制圆弧
        canvas.drawArc(rectF,0,sweepAngle,false,storkPaint);

        Paint.FontMetrics fontMetrics = txtPaint.getFontMetrics();
        float distance = (fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom;
        float baseline = rectF.centerY()+distance;
        if (txtContent!=null){
            canvas.drawText(txtContent,rectF.centerX(),baseline,txtPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (null!=barClickListener){
                    barClickListener.onClick(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onTouchEvent(event);
    }

    private FinalceParoessBarClickListener barClickListener;

    public void setBarClickListener(FinalceParoessBarClickListener barClickListener) {
        this.barClickListener = barClickListener;
    }

    public interface FinalceParoessBarClickListener{
        void onClick(View view);
    }
}
