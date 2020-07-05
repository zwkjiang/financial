package com.example.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class BubbleContainer extends RelativeLayout {
    private BubbleView bvBubblecontainer;
    public BubbleContainer(Context context) {
        super(context);
    }

    public BubbleContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewgroup_bubble, null, false);
        bvBubblecontainer = inflate.findViewById(R.id.bv_bubblecontainer);
    }
}
