package com.my.televip;

import static com.my.televip.MainHook.lpparam;

import android.content.Context;

import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XposedHelpers;

public class loadClass {
    public static  Class<?> ChatActivityClass;
    public static  Class<?> MessageObjectClass;
    public static  Class<?> ProfileActivityClass;
    public static  Class<?> BaseFragmentClass;
    public static  Class<?> drawableClass;
    public static Class<?> UserObjectClass;
    public static Class<?> MessagesControllerClass;

    //Context
    public static Context applicationContext;

    public static Class<?> getMessageObjectClass() {
        if (MessageObjectClass == null) {
            MessageObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessageObject"), lpparam.classLoader);
        }
        return MessageObjectClass;
    }
    public static Class<?> getChatActivityClass() {
        if (ChatActivityClass == null) {
            ChatActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ChatActivity"), lpparam.classLoader);
        }
        return ChatActivityClass;
    }
    public static Class<?> getProfileActivityClass() {
        if (ProfileActivityClass == null) {
            ProfileActivityClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ProfileActivity"), lpparam.classLoader);
        }
        return ProfileActivityClass;
    }
    public static Class<?> getBaseFragmentClass() {
        if (BaseFragmentClass == null) {
            BaseFragmentClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.ActionBar.BaseFragment"), lpparam.classLoader);
        }
        return BaseFragmentClass;
    }
    public static Class<?> getDrawableClass() {
        if (drawableClass == null) {
            drawableClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.R$drawable"), MainHook.lpparam.classLoader);
        }
        return drawableClass;
    }
    public static Class<?> getUserObjectClass() {
        if (UserObjectClass == null){
            UserObjectClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.UserObject"),lpparam.classLoader);
        }
        return UserObjectClass;
    }
    public static Class<?> getMessagesControllerClass() {
        if (MessagesControllerClass == null) {
            MessagesControllerClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.MessagesController"), lpparam.classLoader);
        }
        return MessagesControllerClass;
    }

}
