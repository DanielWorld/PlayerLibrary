package com.danielpark.player;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * Copyright (C) 2014-2017 daniel@bapul.net
 * Created by Daniel on 2017-04-06.
 */

public class DeviceUtil {

    /**
     * Get device resolution
     * @param context {@link Context}
     * @return device resolution as {@link Display}
     * @throws Exception error
     */
    public static Display getResolutionSize(Context context) throws Exception {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display;
    }

    /**
     * Get device resolution width. return 0 if it fails <br>
     *     if it is LANDSCAPE MODE change height to width
     * @param context {@link Context}
     * @return device resolution width
     */
    public static int getResolutionWidth(Context context){
        try {
            return getResolutionSize(context).getWidth();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Get device resolution height. return 0 if it fails <br>
     *     if it is LANDSCAPE MODE change width to height
     * @param context {@link Context}
     * @return  device resolution height
     */
    public static int getResolutionHeight(Context context){
        try{
            return getResolutionSize(context).getHeight();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Get device status bar height.
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        } catch (Exception e){
            return result;
        }
    }
}
