package com.zhaofan.studaydemo.observal;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
class Spy extends Thread {
    private HanFeiZi hanFeiZi;
    private LiSi lisi;
    private String type;

    public Spy(HanFeiZi _hanFeiZi, LiSi _liSi,String _type){
        this.hanFeiZi = _hanFeiZi;
        this.lisi = _liSi;
        this.type = _type;
    }

    @Override
    public void run() {
        while (true){
            if (this.type.equals("breakfast")){
                if (this.hanFeiZi.isHavingBrankfast()){
                    this.lisi.update("韩非子在吃饭");
                    this.hanFeiZi.setHavingBrankfast(false);
                }
            }else{
                if (this.hanFeiZi.isHavingFun()){
                    this.lisi.update("韩非子在娱乐");
                    this.hanFeiZi.setHavingFun(false);
                }
            }
        }
    }
}
