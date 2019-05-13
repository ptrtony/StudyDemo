package com.zhaofan.studaydemo.iterator;

import java.util.ArrayList;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public class ProjectIterator implements IProjectIterator{
    private ArrayList<IProject> projectList = new ArrayList<>();
    private int currentItem=0;
    public ProjectIterator(ArrayList<IProject> _projectList){
        this.projectList = _projectList;
    }



    @Override
    public boolean hasNext() {
        boolean b = true;
        if (this.currentItem>=projectList.size()||this.projectList.get(this.currentItem) == null) b = false;
        return b;
    }

    @Override
    public Object next() {
        return this.projectList.get(this.currentItem++);
    }

    @Override
    public void remove(){
        this.projectList.get(currentItem);
    }
}
