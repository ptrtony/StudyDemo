package com.zhaofan.studaydemo.dagger2.mvp;

import java.lang.annotation.Retention;
import javax.inject.Scope;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/8
 * description:
 */


@Scope
@Retention(RUNTIME)
public @interface ActivityScope {}
