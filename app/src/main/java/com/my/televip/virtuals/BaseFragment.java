package com.my.televip.virtuals;

import android.content.Context;

import de.robv.android.xposed.XposedHelpers;

public class BaseFragment {

    private Object baseFragment;

    public BaseFragment(Object obj){
        baseFragment = obj;
    }
    public Context getContext(){
        return (Context) XposedHelpers.callMethod(baseFragment, "getContext");
    }

    public ActionBar getActionBar(){
        return new ActionBar(XposedHelpers.getObjectField(baseFragment, "actionBar"));
    }

}
