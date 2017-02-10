package com.lj.custumwindowmanager.util;

import com.lj.custumwindowmanager.view.AbsWindow;
import com.lj.custumwindowmanager.view.DialogWindow;
import com.lj.custumwindowmanager.view.MyPopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 弹窗优先级管理器
 * Created by langjian on 2016/12/28.
 * Version 8.1 add
 */

public class PPWindowManager {
    private static PPWindowManager mWindowManager;
    //服务端配置的弹窗
    public static Integer BIZ_TYPE_FROM_SERVER = 0;
    //客户端手动弹窗
    public static Integer BIZ_TYPE_FROM_MANUAL = 1;
    //客户端引导弹窗
    public static Integer BIZ_TYPE_GUIDE = 2;
    private ManagerInteractListener mManagerInteractListener;
    private List<List<AbsWindow>> mTypeList;
    private boolean mIfShowGuide;//是否展示引导类型弹窗

    public static PPWindowManager getInstance() {
        if(mWindowManager == null){
            synchronized (PPWindowManager.class){
                if(mWindowManager == null){
                    mWindowManager = new PPWindowManager();
                }
            }
        }
        return mWindowManager;
    }

    public PPWindowManager(){
        initWindowCollections();
    }

    private void initWindowCollections() {
        mManagerInteractListener = new ManagerInteractListener() {
            @Override
            public void checkIfShowNext(int i,int j) {
                next(i,j);
            }
        };
        //把弹层分为以下3种：服务端（明星来了>VIP礼包>声音的战争）、功能操作引发、引导
        mTypeList = new ArrayList<>(3);
        List<AbsWindow> type1List = new ArrayList();
        mTypeList.add(type1List);
        type1List.add(new DialogWindow(0,0,mManagerInteractListener));
        type1List.add(new MyPopupWindow(0,1,mManagerInteractListener));
    }

    /**
     * 判断当前位置window是否应该展示还是继续判断下一个window是否展示
     * @param row
     * @param column
     */
    private void next(int row, int column) {
        if(row < mTypeList.size()){
            if(column < mTypeList.get(row).size()){
                if(mTypeList.get(row).get(column).checkIfShow()){
                    mTypeList.get(row).get(column).show();
                    mIfShowGuide = false;//展示过一次弹窗 ，不需要展示引导弹窗
                }else{
                    next(row,column+1);
                }
            }
        }
    }

    /**
     * 收集弹窗
     * @param bizType 弹窗类型
     * @param index 弹窗优先级
     * @param windowObject 弹窗
     */
    public void add(Integer bizType, int index, Object windowObject) {
        mTypeList.get(bizType).get(index).setIfShow(true);
        mTypeList.get(bizType).get(index).setWindowObject(windowObject);

        if(bizType == BIZ_TYPE_GUIDE){
            mIfShowGuide = true;
        }else{
            mIfShowGuide = false;
        }
    }

    /**
     * 展示管理器中所有的弹窗
     */
    public void show() {
        next(0,0);//遍历第1种类型弹窗，筛选出优先级最高的弹窗
        next(1,0);//遍历第2种类型弹窗，筛选出优先级最高的弹窗
        if(mIfShowGuide){
            next(2,0);//遍历第3种类型弹窗
        }
    }

    public interface ManagerInteractListener{
        void checkIfShowNext(int row, int nextColumn);
    }
}
