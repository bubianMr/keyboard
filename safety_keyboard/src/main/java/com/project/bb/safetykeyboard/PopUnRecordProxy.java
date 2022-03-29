package com.project.bb.safetykeyboard;

import android.view.InflateException;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Handler;

/**
 * des:PopupWindow动态代理，防止被录屏
 * author:bubian
 * time:2022/3/23 11:58
 */
public class PopUnRecordProxy implements InvocationHandler {
    /**
     * PopupWindowManager类的windowManager对象
     */
    private Object mWindowManager;


    public static PopUnRecordProxy instance() {
        return new PopUnRecordProxy();
    }

    public void unRecord(PopupWindow popupWindow) {
        if (popupWindow == null) {
            throw new InflateException("PopupWindow cannot be empty");
        }

        try {
            //通过反射获取PopupWindow类的私有对象：mWindowManager
            Field mWindowManagerField = PopupWindow.class.getDeclaredField("mWindowManager");
            mWindowManagerField.setAccessible(true);
            mWindowManager = mWindowManagerField.get(popupWindow);
            if (mWindowManager == null) {
                return;
            }

            //创建WindowManager的动态代理对象proxy
            Object proxy = Proxy.newProxyInstance(Handler.class.getClassLoader(), new Class[]{WindowManager.class}, this);

            //注入动态代理对象proxy（即：mWindowManager对象由proxy对象来代理）
            mWindowManagerField.set(popupWindow, proxy);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            //拦截方法mWindowManager.addView(View view,ViewGroup.LayoutParams params)
            if (method != null && method.getName() != null && method.getName().equals("addView") && args != null && args.length == 2) {
                WindowManager.LayoutParams params = (WindowManager.LayoutParams) args[1];
                setAllowScreenRecord(params);
//                setUnScreenRecord(params);
            }
        } catch (Exception ec) {
            ec.printStackTrace();
        }
        return method.invoke(mWindowManager, args);
    }

    /**
     * 设置禁止录屏
     * @param params
     */
    private void setUnScreenRecord(WindowManager.LayoutParams params) {
        setFlags(params, WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    /**
     * 允许录屏
     */
    private void setAllowScreenRecord(WindowManager.LayoutParams params) {
        setFlags(params, 0, WindowManager.LayoutParams.FLAG_SECURE);
    }

    /**
     * 设置WindowManager.LayoutParams flag属性（参考系统类Window.setFlags(int flags, int mask)）
     *
     * @param params WindowManager.LayoutParams
     * @param flags  The new window flags (see WindowManager.LayoutParams).
     * @param mask   Which of the window flag bits to modify.
     */
    private void setFlags(WindowManager.LayoutParams params, int flags, int mask) {
        try {
            if (params == null) {
                return;
            }
            params.flags = (params.flags & ~mask) | (flags & mask);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
