package com.lj.custumwindowmanager.util;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.ViewUtils;

/**
 * Created by ophunter on 17/1/23.
 */
public abstract class ShowDelegate {
    private Activity mActivity;

    public ShowDelegate(Activity activity) {
        mActivity = activity;
    }

    public boolean isShowing() {
        return mIsShowing;
    }

    public void setIsShowing(boolean isShowing) {
        mIsShowing = isShowing;
    }

    private boolean mIsShowing = false;


    public void show() {
        if (!isActivityFinished(mActivity)) {
            performShow();
        } else {
            PPSerialWindowDispatcher.unregister(mActivity);
        }
    }

    private boolean isActivityFinished(Activity activity) {
        if(activity == null){
            return true;
        }else{
            if(activity.isFinishing()){
                return true;
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if(activity.isDestroyed()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public abstract void performShow();

    public abstract int getType();

}
