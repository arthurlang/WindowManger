package com.lj.custumwindowmanager;

import static com.lj.custumwindowmanager.R.mipmap.guide;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.lj.custumwindowmanager.util.PPSerialWindowDispatcher;
import com.lj.custumwindowmanager.util.PPWindowConfig;
import com.lj.custumwindowmanager.util.PPWindowManager;
import com.lj.custumwindowmanager.util.ShowDelegate;
import com.lj.custumwindowmanager.view.MyDialog;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private PopupWindow mPopUpWindow;
    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        setContentView(mRootView);
    }

    /**
     * 按照先后顺序依次弹出弹窗
     * 按照优先级同时弹出来
     * @param view
     */
    public void MethodOne(View view) {

        PPWindowManager.getInstance().add(PPWindowManager.BIZ_TYPE_FROM_SERVER, 0, getDialog());
        PPWindowManager.getInstance().add(PPWindowManager.BIZ_TYPE_FROM_SERVER, 1, getPopUpWindow());

        PPWindowManager.getInstance().show();
    }

    /**
     * 每1个页面，都维护一套弹窗
     * 管理器将show（）接口暴露给用户
     * @param view
     */
    public void MethodTwo(View view){
        PPSerialWindowDispatcher.registerDispatcher(this);
        //----------------------window1 :MyDialog--------------------------
        final MyDialog dialog = getDialog();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                PPSerialWindowDispatcher.getDispatcher(MainActivity.this).onDismiss(PPWindowConfig.Server.DIALOG,true);
            }
        });
        ShowDelegate delegate = new ShowDelegate(this){
            @Override
            public void performShow() {
                dialog.show();
            }

            @Override
            public int getType() {
                return PPWindowConfig.Server.DIALOG;
            }
        };
        PPSerialWindowDispatcher.getDispatcher(this).show(delegate);

        //----------------------window2 :popupwindow--------------------------
        initPopupWindow();
        ShowDelegate popupWindowDelegate = new ShowDelegate(this){
            @Override
            public void performShow() {
                mPopUpWindow.showAtLocation(mRootView, Gravity.CENTER,100,100);
            }

            @Override
            public int getType() {
                return PPWindowConfig.Server.DIALOG;
            }
        };

        PPSerialWindowDispatcher.getDispatcher(this).show(popupWindowDelegate);

    }

    private void initPopupWindow() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(guide);

        mPopUpWindow = new PopupWindow(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopUpWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopUpWindow.setFocusable(true);
        mPopUpWindow.setOutsideTouchable(false);

    }

    @Override
    protected void onDestroy() {
        PPSerialWindowDispatcher.unregister(this);
        super.onDestroy();
    }

    private MyDialog getDialog() {
        MyDialog dialog = new MyDialog(this, 1);
        dialog.setTitle("1");
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public WeakReference getPopUpWindow() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(guide);

        mPopUpWindow = new PopupWindow(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopUpWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopUpWindow.setFocusable(true);
        mPopUpWindow.setOutsideTouchable(true);

        mPopUpWindow.showAtLocation(mRootView, Gravity.CENTER,100,100);

        HashMap hashMap = new HashMap();
        hashMap.put("window", mPopUpWindow);
        hashMap.put("view",mRootView);

        WeakReference weekHashMap = new WeakReference<>(hashMap);

        return weekHashMap;
    }
}
