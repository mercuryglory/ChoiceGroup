package com.mercury.choicegroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wang.zhonghao on 2016/10/20
 * desciprt:  自定义单选框
 */
public class CustomCheckText extends TextView implements View.OnTouchListener{
  
    private boolean isTouched = false;//是否被按下  
  
    private int touch = 1;//按钮被按下的次数

    Context mContext;

    public int getTouch() {
        return touch;
    }

    public CustomCheckText(Context context){
        this(context,null);
        mContext = context;
        init();  
    }  
  
    public CustomCheckText(Context context, AttributeSet attributeSet){
//        this(context,attributeSet,android.R.attr.borderlessButtonStyle);
        this(context, attributeSet, 0);
        init();  
    }  
  
    public CustomCheckText(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);
    }

    protected void init(){
        setOnTouchListener(this);
    }  
  
    public void setTouch(int touch){  
        this.touch = touch;  
    }  
  
    @Override  
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int padding = DensityUtil.dip2px(mContext, 10);
        if (touch % 2 == 0) {
            //被选中
//            this.setTextSize(DensityUtil.px2sp(mContext,30));
            this.setTextSize(16);
            this.setPadding(0,padding,0,padding);
            this.setTextColor(Color.WHITE);
            this.setBackgroundResource(R.drawable.input_rectangle_blue);
        } else {
//            this.setTextSize(DensityUtil.px2sp(mContext,30));
            this.setTextSize(16);
            this.setPadding(0,padding,0,padding);
            this.setTextColor(getResources().getColor(R.color.black));
            this.setBackgroundResource(R.drawable.input_rectangle_white);
        }
        invalidate();  
    }  
  
    public void setTouched(boolean isTouched){  
        this.isTouched = isTouched;  
    }  
  
    @Override  
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){  
            case MotionEvent.ACTION_DOWN:

                isTouched = true;  

                break;  
            case MotionEvent.ACTION_UP:
                onValueChangedListner.onValueChanged(this.getText().toString());
                touch ++;
                isTouched = false;  
                break;  
        }  
        return true;  
    }  
  
    public interface OnValueChangedListner{  
        void onValueChanged(String value);
    }  
  
    //实现接口，方便将当前按钮的值回调  
    OnValueChangedListner onValueChangedListner;  
  
    public void setOnValueChangedListner(OnValueChangedListner onValueChangedListner){  
        this.onValueChangedListner = onValueChangedListner;  
    }  
}  
