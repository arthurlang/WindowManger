package com.lj.custumwindowmanager.view;

import com.lj.custumwindowmanager.util.PPWindowManager;

/**
 * Description
 * Created by langjian on 2016/12/29.
 * Version
 */

public abstract class AbsWindow {

    protected boolean ifShow = false;
    protected PPWindowManager.ManagerInteractListener mManagerInteractListener;
    protected int row,column;
    public AbsWindow(int i, int j,
            PPWindowManager.ManagerInteractListener managerInteractListener) {
        row = i;
        column = j;
        mManagerInteractListener = managerInteractListener;
    }

    public void setIfShow(boolean ifShow) {
        this.ifShow = ifShow;
    }

    public boolean checkIfShow() {
        return ifShow;
    }

    protected Object windowObject;

    public Object getWindowObject() {
        return windowObject;
    }

    public void setWindowObject(Object window) {
        if(window != windowObject){
            this.windowObject = window;
        }
    }

    public void show(){
        setIfShow(false);
    }
}
