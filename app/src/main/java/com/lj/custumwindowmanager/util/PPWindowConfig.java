package com.lj.custumwindowmanager.util;

/**
 * Created by ophunter on 17/1/23.
 */
public class PPWindowConfig {
    /**
     * 服务端弹窗
     * //新手引导,这个已经没有了
     * //    public static final int NEW_USER_GUIDE = 101;
     * //明星来了 先不加进去了
     * //    public static final int STAR_COME = 103;
     */
    public interface Server {
        //升级弹窗
        public static final int DIALOG = 100;

        //声音的战争  ok
        public static final int POPUPWINDOW = 102;
    }

    /**
     * 手动弹窗
     */
    public interface Manual {
        //===================== 上面是服务端弹窗,下面是用户操作弹窗 ======================
    }

    //===========================================================
    /**
     * 引导弹窗
     */
    public interface Guide {

    }
}
