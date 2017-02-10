package com.lj.custumwindowmanager.view;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;

/**
 * Description
 * Created by langjian on 2016/12/28.
 * Version
 */

public class MyDialog extends Dialog {
    private int mBizType;
    int index = 0;
    public MyDialog(Context context) {
        super(context);
    }

    protected MyDialog(Context context, boolean cancelable,
            OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public MyDialog(Context context, int themeResId) {
        super(context);
        index = themeResId;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
