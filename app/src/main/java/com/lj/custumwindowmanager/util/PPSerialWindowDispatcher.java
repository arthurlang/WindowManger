package com.lj.custumwindowmanager.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ophunter on 17/1/22. 针对不同页面的弹窗单独管理，用wallid和context区分
 */
public class PPSerialWindowDispatcher {

    public static final ArrayList<PPSerialWindowDispatcher> sDispatcherArray = new ArrayList<>();

    private static PPSerialWindowDispatcher find(Activity activity) {
        int key = getKey(activity);
        for (int i = 0; i < CollectionUtils.size(sDispatcherArray); i++) {
            if (sDispatcherArray.get(i).mKey == key) {
                return sDispatcherArray.get(i);
            }
        }
        return null;
    }


    private int mKey;
    private long mWallId = -1;
    private Activity mActivity;

    //不是用户行为实时出发的
    private ArrayList<ShowDelegate> mWindows;

    private PPSerialWindowDispatcher(Activity activity) {
        mActivity = activity;
        mWindows = new ArrayList<>();
        mKey = getKey(activity);
    }

    public PPSerialWindowDispatcher setWallId(long id) {
        mWallId = id;
        return this;
    }


    public static PPSerialWindowDispatcher registerDispatcher(Activity activity) {

        PPSerialWindowDispatcher dispatcher = find(activity);

        if (dispatcher == null) {
            dispatcher = new PPSerialWindowDispatcher(activity);
            sDispatcherArray.add(dispatcher);
        }
        return dispatcher;
    }


    public static void unregister(Activity activity) {
        PPSerialWindowDispatcher dispatcher = find(activity);
        if (sDispatcherArray != null) {
            sDispatcherArray.remove(dispatcher);
        }
    }


    public static PPSerialWindowDispatcher getDispatcher(Activity activity) {
        return find(activity);
    }

    /**
     * 针对一些弹窗会跳转到另一个Activity,那么使用activity作为标志不行
     */
    public static PPSerialWindowDispatcher getDispatcher(long wallId) {
        if (CollectionUtils.isEmpty(sDispatcherArray)) {
            return null;
        }

        for (int i = CollectionUtils.size(sDispatcherArray) - 1; i >= 0; i--) {
            PPSerialWindowDispatcher temp = sDispatcherArray.get(i);
            if (temp.mWallId == wallId) {
                return temp;
            }
        }
        return null;
    }


    //==========================================

    /**
     * 显示弹窗
     */
    public void show(ShowDelegate showDelegate) {
        if (showDelegate == null) {
            return;
        }
        mWindows.add(showDelegate);
        traverse();
    }


    /**
     * 隐藏弹窗
     */
    public void onDismiss(int type, boolean showNext) {
        if (CollectionUtils.isEmpty(mWindows)) {
            return;
        }

        Iterator<ShowDelegate> iterator = mWindows.iterator();

        while (iterator.hasNext()) {
            ShowDelegate delegate = iterator.next();
            if (delegate.getType() == type && delegate.isShowing()) {
                iterator.remove();
            }
        }

        traverse();
    }


    /**
     * 遍历服务端弹窗
     */
    public void traverse() {
        if (CollectionUtils.isEmpty(mWindows)) {
            return;
        }

        ShowDelegate showDelegate = mWindows.get(0);
        if (showDelegate.isShowing()) {
            //有正在显示的
            return;
        }

        showDelegate.setIsShowing(true);
        showDelegate.show();
    }


    private static int getKey(Activity activity) {
        if (activity == null) {
            return -1;
        } else {
            return activity.hashCode();
        }
    }


}
