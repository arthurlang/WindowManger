package com.lj.custumwindowmanager.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.PopupWindow;

import com.lj.custumwindowmanager.util.PPWindowManager;

/**
 * Description
 * Created by langjian on 2016/12/29.
 * Version
 */

public class DialogWindow extends AbsWindow {
    private Dialog mWindow;

    public DialogWindow(
            int i, int j, PPWindowManager.ManagerInteractListener managerInteractListener) {
        super(i,j,managerInteractListener);
    }

    @Override
    public void show() {
        if(windowObject instanceof Dialog){
            mWindow = (Dialog)windowObject;
            if(mWindow != null){
                mWindow.show();
            }
            mWindow.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if(mManagerInteractListener != null){
                        mManagerInteractListener.checkIfShowNext(row,column+1);
                    }
                }
            });
        }
    }
}
