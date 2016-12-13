package com.example.wangguanghong.moveback;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by wangguanghong on 2016/12/6.
 */

public class MyMoveLayout extends LinearLayout {
    private int mMove;  //动态获得滑动距离
    private int mYDown,mYMove; //手指按下的位置和移动时手指的位置
    private int mMoveBack; //回弹时需要回弹的距离

    public MyMoveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y =(int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mYDown=y;
                break;
            case MotionEvent.ACTION_MOVE:
                mYMove=y;
                if(mYMove-mYDown>0){
                    mMove=mYMove-mYDown;
                    mMoveBack+=mMove;
                    layout(getLeft(),getTop()+mMove,getRight(),getBottom()+mMove);
                }
                break;
            case MotionEvent.ACTION_UP:
                layout(getLeft(),getTop()-mMoveBack,getRight(),getBottom()-mMoveBack);
                mMoveBack=0;
                break;

        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y=(int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mYDown=y;
                break;
            case MotionEvent.ACTION_MOVE:
                mYMove=y;
                if(mYMove-mYDown<0){
                    return false;
                }else if(mYMove-mYDown>0){
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }
}
