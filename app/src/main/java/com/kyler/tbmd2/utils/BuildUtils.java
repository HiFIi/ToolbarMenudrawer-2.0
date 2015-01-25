package com.kyler.tbmd2.utils;

import android.os.Build;

public class BuildUtils {

    public static boolean isL() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}