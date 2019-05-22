package com.zhaofan.studaydemo.strategy_pattern;

import java.util.Set;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class OrderModeChoice implements SelectorGroup.ChoiceAction{

    @Override
    public void onChoice(Set<Selector> selectorSet, Selector selector, SelectorGroup.StateListener stateListener) {
        String prefix = getPreTag(selector.getSelectTag());
        cancelPreSelectorSameTag(selectorSet,prefix,stateListener);
        selector.setSelected(true);
    }

    //在同一组中取消之前的选项
    private void cancelPreSelectorSameTag(Set<Selector> selectors,String tagPrefix,SelectorGroup.StateListener stateListener){
        for (Selector selector:selectors){
            String prefix = getPreTag(selector.getSelectTag());
            if (prefix.equals(tagPrefix) && selector.isSelected()){
                selector.setSelected(false);
                if (stateListener!=null){
                    stateListener.onStateChange(prefix,false);
                }
            }
        }
    }

    //获取标签前缀
    public String getPreTag(String tag){
        int index = tag.indexOf("_");
        return tag.substring(0,index);
    }
}
