package com.lj.custumwindowmanager.view;

import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.lj.custumwindowmanager.util.PPWindowManager;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Description
 * Created by langjian on 2017/2/10.
 * Version
 */

public class MyPopupWindow extends AbsWindow {
    private View mView;

    public MyPopupWindow(
            int i, int j, PPWindowManager.ManagerInteractListener managerInteractListener) {
        super(i,j,managerInteractListener);

    }

    @Override
    public void show() {
        if(windowObject instanceof WeakReference){
            HashMap hashMap = ((WeakReference<HashMap>)windowObject).get();
            if(hashMap != null){
                PopupWindow window = (PopupWindow) hashMap.get("window");
                mView = (View) hashMap.get("view");
            }
            PopupWindow window = (PopupWindow)windowObject;
            if(window != null){
                window.showAtLocation(mView, Gravity.CENTER,100,100);
            }
        }
    }
}
