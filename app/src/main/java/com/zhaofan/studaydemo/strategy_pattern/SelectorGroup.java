package com.zhaofan.studaydemo.strategy_pattern;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class SelectorGroup {

    public static final int MODE_SINGLE_CHOICE = 1;//单选
    public static final int MODE_MULTIPLE_CHOICE = 2;//多选

    private Set<Selector> selectors  = new HashSet<>();

    public void addSelector(Selector selector){
        selectors.add(selector);
    }

    public void onSelectorClick(Selector selector){
        cancelPreClick(selector);
    }
    private void cancelPreClick(Selector selector) {
        for (Selector s:selectors){
            if (!s.equals(selector) && selector.isSelected()){
                s.setSelected(false);
            }
        }
    }

    private ChoiceAction choiceAction;
    public void setChoiceMode(ChoiceAction choiceAction){
        this.choiceAction = choiceAction;
    }


    //通过默认方法设置默认行为
    public void setChoiceMode(int mode){
        switch (mode){
            case MODE_SINGLE_CHOICE:
                choiceAction = new SingleAction();
                break;
            case MODE_MULTIPLE_CHOICE:
                choiceAction = new MultiAction();
                break;
        }

    }

    private StateListener onStateListener;
    public void setOnSelectClick(Selector Selector){
        if (choiceAction!=null){
            choiceAction.onChoice(selectors,Selector,onStateListener);
        }
    }



    public interface ChoiceAction{
        void onChoice(Set<Selector> selectorSet,Selector selector,StateListener stateLisneter);
    }


    public interface StateListener{
        void onStateChange(String tag,boolean isSelector);
    }

    //单选
    public class SingleAction implements ChoiceAction{

        @Override
        public void onChoice(Set<Selector> selectorSet, Selector selector, StateListener stateLisneter) {
            selector.setSelected(true);
            cancelPreClick(selector);
        }
    }
    //多选
    public class MultiAction implements ChoiceAction{
        @Override
        public void onChoice(Set<Selector> selectorSet, Selector selector, StateListener stateLisneter) {
            boolean isPreChoice = selector.isSelected();
            selector.setSelected(!isPreChoice);
        }
    }




}
