package com.my.televip.virtuals;

import com.my.televip.ClientChecker;
import com.my.televip.MainHook;
import com.my.televip.Utils;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class EventType {

    public static int eventType;

    public static int IconSettings() {
        eventType = (int) XposedHelpers.callStaticMethod(XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.Theme"), MainHook.lpparam.classLoader), AutomationResolver.resolve("Theme", "getEventType", AutomationResolver.ResolverType.Method));
        int drawableResource = 0;
        if (!ClientChecker.check(ClientChecker.ClientType.Nagram)) {
            if (loadClass.getDrawableClass() != null) {
                if (eventType == 0) {
                    drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "msg_settings_ny");
                } else if (eventType == 1) {
                    drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "msg_settings_14");
                } else if (eventType == 2) {
                    drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "msg_settings_hw");
                } else {
                    drawableResource = XposedHelpers.getStaticIntField(loadClass.getDrawableClass(), "msg_settings_old");
                }
            } else {
                Utils.log("Not found rg.telegram.messenger.R$drawable, " + Utils.issue);
            }
        }else {
            drawableResource = 0x7f080806;
        }
        return drawableResource;
    }
}
