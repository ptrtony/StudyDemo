package com.zhaofan.studaydemo.strategy_pattern;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:策略模式实战
 */
public abstract class Selector extends FrameLayout implements View.OnClickListener {
    private static String tag;
    private SelectorGroup selectorGroup;
    public Selector(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view  = setContentView();
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(view,params);
        this.setOnClickListener(this);
    }

    protected abstract View setContentView();

    public Selector setGroup(SelectorGroup selectorGroup){
        this.selectorGroup = selectorGroup;
        selectorGroup.addSelector(this);
        return this;
    }

    public void setSelected(boolean isSelected){
        //设置按钮的点击状态
        boolean isPreSelected = isSelected();
        super.setSelected(isPreSelected);
        if (isPreSelected!=isSelected){
            onSwitchSelected(isSelected);
        }
    }

    //按钮选中状态变更（在子类中自定义变更效果）
    protected abstract void onSwitchSelected(boolean isSelected);

    public String getSelectTag(){
        return tag;
    }

    @Override
    public void onClick(View v) {
        //通知选中组 ，当前以选中
        if (selectorGroup!=null){
            selectorGroup.onSelectorClick(this);
        }
    }
}
