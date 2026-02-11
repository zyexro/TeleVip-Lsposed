package com.my.televip.virtuals.Cells;

import android.content.Context;
import android.widget.FrameLayout;

import com.my.televip.MainHook;

import de.robv.android.xposed.XposedHelpers;

public class TextCheckCell {
    Object textCell;

    public TextCheckCell(Context context){
        Class<?> textCheckClass = XposedHelpers.findClassIfExists("org.telegram.ui.Cells.TextCheckCell", MainHook.lpparam.classLoader);
        textCell = XposedHelpers.newInstance(textCheckClass, context);
    }

    public TextCheckCell(Object obj){
        textCell = obj;
    }

    public Object getTextCell(){
        return textCell;
    }

    public void setBackgroundColor(int color){
        XposedHelpers.callMethod(textCell, "setBackgroundColor", color);
    }

    public void setTextAndCheck(CharSequence text, boolean checked, boolean divider){
        XposedHelpers.callMethod(textCell, "setTextAndCheck", text, checked, divider);
    }
}
