package com.zhaofan.studaydemo.adapter_pattrn;

import java.io.File;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:默认适配器模式
 */
public class FileMonitor extends FileAlterationListenerAdaptor {
    @Override
    public void onFileCreate(File file) {
        super.onFileCreate(file);

    }


    @Override
    public void onFileDelete(File file) {
        super.onFileDelete(file);

    }
}
