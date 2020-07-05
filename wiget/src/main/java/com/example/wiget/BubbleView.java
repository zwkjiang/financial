package com.example.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.example.conmon.utils.LogUtils;

public class BubbleView extends View {
    private Paint storkPaint;
    private Paint paint;
    private Paint txtPaint;

    private int backgroundColor;

    private float storkWidth;

    private int storkColor;

    private int contentColor;

    private float contentSize;

    private int defaultWidth,defaultHeight;

    private String content;

    float radius = 20;

    public BubbleView(Context context) {
        super(context);
    }

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        doCustomProp(context,attrs);
        initPaint();
    }


    private void doCustomProp(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BubbleView);
        if (typedArray!=null){
            backgroundColor = typedArray.getColor(R.styleable.BubbleView_backgroundColor, Color.RED);
            storkWidth = typedArray.getDimension(R.styleable.BubbleView_storkWidth, 0);
            storkColor = typedArray.getColor(R.styleable.BubbleView_storkColor, Color.WHITE);
            contentColor = typedArray.getColor(R.styleable.BubbleView_contentColor, Color.LTGRAY);
            contentSize = typedArray.getDimension(R.styleable.BubbleView_contentSize, 20);

            content = typedArray.getString(R.styleable.BubbleView_bubbleContent);

            radius=typedArray.getFloat(R.styleable.BubbleView_bubbleRadius,20.0F);
        }
        typedArray.recycle();
    }
    private void initPaint() {
        paint = new Paint();
        txtPaint = new Paint();
        storkPaint = new Paint();

        paint.setAntiAlias(true);
        paint.setStrokeWidth(storkWidth);
        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setDither(true);

        storkPaint.setStyle(Paint.Style.STROKE);
        storkPaint.setStrokeWidth(storkWidth);
        storkPaint.setColor(storkColor);
        storkPaint.setAntiAlias(true);
        storkPaint.setDither(true);

        txtPaint.setColor(contentColor);
        txtPaint.setTextSize(contentSize);
        txtPaint.setTextAlign(Paint.Align.CENTER);
        txtPaint.setAntiAlias(true);
        txtPaint.setDither(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getSize(heightMeasureSpec);
        int heightSize = MeasureSpec.getMode(heightMeasureSpec);

        if (MeasureSpec.AT_MOST==widthMode&&MeasureSpec.AT_MOST==heightMode){
            setMeasuredDimension(defaultWidth,defaultHeight);
        }else if(MeasureSpec.AT_MOST==widthMode){
            setMeasuredDimension(defaultWidth,heightSize);
        }else if(MeasureSpec.AT_MOST==heightMode){
            setMeasuredDimension(widthSize,defaultHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getMeasuredHeight()/2;
        float centerY = getMeasuredWidth()/2;
        canvas.drawCircle(centerX,centerY,radius,paint);

        canvas.drawCircle(centerX,centerY,radius+storkWidth,storkPaint);

        RectF rectF = new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
        Paint.FontMetrics fontMetrics = txtPaint.getFontMetrics();
        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseline = rectF.centerY() + distance;
        if (null!=content){
            canvas.drawText(content,rectF.centerX(),baseline,txtPaint);
        }
    }
}
