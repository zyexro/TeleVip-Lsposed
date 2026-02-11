package com.my.televip.virtuals;

import android.content.Context;

import com.my.televip.language.Language;

import de.robv.android.xposed.XposedHelpers;

public class ActionBar {

    private Object actionbar;

    public ActionBar(Object obj){
        actionbar = obj;
    }

    public void setTitle(String name){
        XposedHelpers.callMethod(actionbar, "setTitle", name);
    }

}
