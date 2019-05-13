package com.zhaofan.studaydemo.dagger2.mvp;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/8
 * description:
 */
public class LoginPresenter {
    ICommonView iCommonView;

    @Inject
    public LoginPresenter(ICommonView iCommonView){
        this.iCommonView = iCommonView;
    }


    public void login(User user){
        Context mContext = iCommonView.getContext();
        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
    }
}
