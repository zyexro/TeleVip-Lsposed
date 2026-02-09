package com.my.televip.ui;

import static com.my.televip.MainHook.*;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.my.televip.AlertDialog.onClickDialog;
import com.my.televip.ClientChecker;
import com.my.televip.Utils;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.features.FeatureManager;
import com.my.televip.language.Language;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.ActiveTheme;
import com.my.televip.virtuals.EventType;
import com.my.televip.xSharedPreferences;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class Theme {

    public static int id_item_add = -1;
    private static Field itemsField;
    private static Constructor<?> itemConstructor;
    public static MediaPlayer mediaPlayer;
    public static boolean playing=false;
    public static int regr=0;
    public static String audioUrl;

    private static void openDialog(Context applicationContext, final XC_MethodHook.MethodHookParam param){
        final Class<?> alertDialogBuilderClass = XposedHelpers.findClassIfExists(
                AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog.Builder"),
                lpparam.classLoader
        );
        if (alertDialogBuilderClass != null) {
            ActiveTheme.setActiveTheme();

            if (loadClass.applicationContext == null) {
                loadClass.applicationContext = (Context) XposedHelpers.getStaticObjectField(
                        XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), lpparam.classLoader),
                        AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
                );
            }
            xSharedPreferences.SharedPre = applicationContext.getSharedPreferences(strTelevip, Activity.MODE_PRIVATE);
            FeatureManager.readFeature();
            Object alertDialog = XposedHelpers.newInstance(alertDialogBuilderClass, applicationContext);
            // عرض رسالة أو تخصيص النافذة
            Language.init(loadClass.applicationContext);
            ArrayList<String> list = getArrayList();
            final String[] items = list.toArray(new String[0]);
            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setTitle", AutomationResolver.ResolverType.Method), Ghost_Mode);
            // إنشاء تخطيط جديد
            LinearLayout layout = new LinearLayout(applicationContext);
            layout.setOrientation(LinearLayout.VERTICAL);

// إضافة CheckBox لكل عنصر في القائمة مع إعدادات النص
            final List<CheckBox> checkBoxes = new ArrayList<>();
            for (String item : items) {
                CheckBox checkBox = new CheckBox(applicationContext);
                if (item.equals(TelegramPremium) && FeatureManager.isTelePremium()) {
                    checkBox.setChecked(true);
                } else if (item.equals(HideSeenUser) && FeatureManager.isHideSeenPrivate()) {
                    checkBox.setChecked(true);
                } else if (item.equals(HideSeenGroups) && FeatureManager.isHideSeenGroup()) {
                    checkBox.setChecked(true);
                }
                if (item.equals(HideTyping) && FeatureManager.isHideTyping()) {
                    checkBox.setChecked(true);
                } else if (item.equals(HideStoryView) && FeatureManager.isNoStoryRead()) {
                    checkBox.setChecked(true);
                } else if (item.equals(UnlockAllRestricted) && FeatureManager.isUnlockChannelFeature()) {
                    checkBox.setChecked(true);
                } else if (item.equals(AllowSavingvideos) && FeatureManager.isAllowSaveToGallery()) {
                    checkBox.setChecked(true);
                } else if (item.equals(HideOnline) && FeatureManager.isHideOnline()) {
                    checkBox.setChecked(true);
                } else if (item.equals(PreventMedia) && FeatureManager.isPreventMedia()) {
                    checkBox.setChecked(true);
                    try {
                        checkBox.setOnLongClickListener(_view -> {
                            if (!playing) {
                                regr = (int) (Math.random() * 3);
                                if (regr == 0) {
                                    audioUrl = "https://qurango.net/radio/abdulbasit_abdulsamad_mojawwad";
                                } else if (regr == 1) {
                                    audioUrl = "https://qurango.net/radio/yasser_aldosari";
                                } else {
                                    audioUrl = "https://backup.qurango.net/radio/maher";
                                }
                                mediaPlayer = new MediaPlayer();
                                //noinspection deprecation
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                                        .setUsage(AudioAttributes.USAGE_MEDIA)
                                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                        .build();

                                mediaPlayer.setAudioAttributes(audioAttributes);
                                try {
                                    mediaPlayer.setDataSource(audioUrl);
                                } catch (IllegalArgumentException |
                                         IllegalStateException |
                                         IOException e) {
                                    throw new RuntimeException(e);
                                }
                                mediaPlayer.prepareAsync();

                                mediaPlayer.setOnPreparedListener(mp -> {
                                    mediaPlayer.start();
                                    playing = true;
                                });
                            } else {
                                if (mediaPlayer.isPlaying()) {
                                    mediaPlayer.stop();
                                    mediaPlayer.release();

                                    mediaPlayer = new MediaPlayer();
                                    //noinspection deprecation
                                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                    AudioAttributes audioAttributes = new AudioAttributes.Builder()
                                            .setUsage(AudioAttributes.USAGE_MEDIA)
                                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                            .build();

                                    mediaPlayer.setAudioAttributes(audioAttributes);
                                    try {
                                        mediaPlayer.setDataSource(audioUrl);
                                    } catch (IllegalArgumentException |
                                             IllegalStateException |
                                             IOException e) {
                                        throw new RuntimeException(e);
                                    }

                                    playing = false;

                                }
                            }
                            return true;
                        });
                    } catch (Exception ex) {
                        Utils.log(ex.getMessage());
                    }
                } else if (item.equals(HidePhone) && FeatureManager.isHidePhone()) {
                    checkBox.setChecked(true);
                } else if (item.equals(ShowDeletedMessages) && FeatureManager.ishowDeletedMessages()) {
                    checkBox.setChecked(true);
                } else if (item.equals(DisableStories) && FeatureManager.isDisableStories()) {
                    checkBox.setChecked(true);
                }

                checkBox.setText(item);
                if (!ActiveTheme.isCurrentThemeDay) {
                    checkBox.setTextColor(Color.BLACK); // تغيير لون النص إلى الأبيض
                } else {
                    checkBox.setTextColor(Color.WHITE);
                }
                checkBox.setPadding(10, 10, 10, 10); // إضافة هامش صغير حول النص
                checkBox.setTypeface(Typeface.DEFAULT_BOLD); // جعل النص مائلًا قليلاً
                checkBoxes.add(checkBox);
                layout.addView(checkBox);
            }

// إعداد AlertDialog واستخدام setView
            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setView", AutomationResolver.ResolverType.Method), layout);


// نحصل على الكلاس الداخلي OnButtonClickListener
            Object onDoneListener;
            Object onCnelListener;
            Class<?> listenerClass = XposedHelpers.findClassIfExists(
                    AutomationResolver.resolve("org.telegram.ui.ActionBar.AlertDialog$OnButtonClickListener"),
                    lpparam.classLoader
            );
            if (listenerClass != null) {
                onDoneListener = Proxy.newProxyInstance(
                        lpparam.classLoader,
                        new Class[]{listenerClass},
                        (proxy, method, args) -> {
                            if (method.getName().equals("onClick")) {
                                onClickDialog.onClickSave(checkBoxes);
                            }
                            return null;
                        }
                );
                onCnelListener = Proxy.newProxyInstance(
                        lpparam.classLoader,
                        new Class[]{listenerClass},
                        (proxy, method, args) -> {
                            if (method.getName().equals("onClick")) {
                                onClickDialog.onClickOpenUrl(applicationContext, param);
                            }
                            return null;
                        }
                );

            } else {
                onDoneListener = (DialogInterface.OnClickListener) (dialog, which) -> onClickDialog.onClickSave(checkBoxes);
                onCnelListener = (DialogInterface.OnClickListener) (dialog, which) -> onClickDialog.onClickOpenUrl(applicationContext, param);
            }
            // إعداد الزر الموجب
            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setPositiveButton", AutomationResolver.ResolverType.Method),
                    Save, onDoneListener
            );
            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "setNegativeButton", AutomationResolver.ResolverType.Method),
                    DeveloperChannel, onCnelListener
            );


            XposedHelpers.callMethod(alertDialog, AutomationResolver.resolve("AlertDialog", "show", AutomationResolver.ResolverType.Method));
        } else {
            Utils.log("Not found org.telegram.ui.ActionBar.AlertDialog.Builder, " + Utils.issue);
        }
    }

    public static void newTheme(){

        Class<?> SettingsActivityClass = XposedHelpers.findClass(
                AutomationResolver.resolve("org.telegram.ui.SettingsActivity"),
                lpparam.classLoader
        );
        Class<?> SettingsActivity$SettingCell$FactoryClass = XposedHelpers.findClass(
                AutomationResolver.resolve("org.telegram.ui.SettingsActivity$SettingCell$Factory"),
                lpparam.classLoader
        );

        AbstractMethodHook fillItemsHook = new AbstractMethodHook() {
            @Override
            protected void afterMethod(final MethodHookParam param) {
                if (loadClass.applicationContext == null) {
                    loadClass.applicationContext = (Context) XposedHelpers.getStaticObjectField(
                            XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), lpparam.classLoader),
                            AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
                    );
                }
                Language.init(loadClass.applicationContext);
                ArrayList<Object> arrayList = (ArrayList<Object>) param.args[0];
                if (arrayList != null) {

// بعض القيم الثابتة
                    int color1 = 0xFFF46F6F;
                    int color2 = 0xFFDF5555;
                    int id = 8353847;

// إنشاء عنصر واجهة (UItem) باستخدام Factory
                    Object uitem = XposedHelpers.callStaticMethod(SettingsActivity$SettingCell$FactoryClass, "of", id,
                            color1,
                            color2,
                            EventType.IconSettings(),
                            GhostMode,
                            byMustafa);
                    if (id_item_add == -1) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            Object obj = arrayList.get(i);
                            int id_item = XposedHelpers.getIntField(obj, "id");
                            if (id_item > 0) {
                                arrayList.add(i, uitem);
                                id_item_add = i;
                                break;
                            }
                        }
                    } else {
                        arrayList.add(id_item_add, uitem);
                    }

                }

            }
        };

        XposedHelpers.findAndHookMethod(SettingsActivityClass, AutomationResolver.resolve("SettingsActivity", "fillItems", AutomationResolver.ResolverType.Method),
                AutomationResolver.merge(AutomationResolver.resolveObject("10"), fillItemsHook));

        AbstractMethodHook onClickHook = new AbstractMethodHook() {
            @Override
            protected void afterMethod(final MethodHookParam param) {
                Object uitem = param.args[0];
                if (uitem != null){
                    int id = XposedHelpers.getIntField(uitem, "id");
                    if (id == 8353847) {

                        Object SettingsActivity = param.thisObject;
                        Context applicationContext = (Context) XposedHelpers.callMethod(SettingsActivity, "getContext");
                        openDialog(applicationContext, null);
                    }
                }
            }
        };


        XposedHelpers.findAndHookMethod(
                SettingsActivityClass,
                AutomationResolver.resolve("SettingsActivity", "onClick", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("12"), onClickHook));
    }

    public static void oldTheme(){
        final Class<?> itemClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.ui.Adapters.DrawerLayoutAdapter$Item"), lpparam.classLoader);

        if (itemClass != null) {
            XposedHelpers.findAndHookMethod(
                    AutomationResolver.resolve("org.telegram.ui.Adapters.DrawerLayoutAdapter"), // اسم الكلاس
                    lpparam.classLoader,
                    AutomationResolver.resolve("DrawerLayoutAdapter", "resetItems", AutomationResolver.ResolverType.Method),                                   // اسم الدالة
                    new AbstractMethodHook() {
                        @Override
                        protected void afterMethod(MethodHookParam param) throws Throwable {
                            Object drawerLayoutAdapterInstance = param.thisObject;

                            // العثور على المتغير الخاص
                            Class<?> drawerLayoutAdapterClass = drawerLayoutAdapterInstance.getClass();
                            if (itemsField == null) {
                                itemsField = drawerLayoutAdapterClass.getDeclaredField(AutomationResolver.resolve("DrawerLayoutAdapter", "items", AutomationResolver.ResolverType.Field));
                                itemsField.setAccessible(true);
                            }
                            ArrayList<?> items = (ArrayList<?>) itemsField.get(drawerLayoutAdapterInstance);

                            // استدعاء الكلاس Item باستخدام Class.forName
                            if (itemConstructor == null) {
                                itemConstructor = itemClass.getDeclaredConstructor(AutomationResolver.resolveObject("para4"));
                                itemConstructor.setAccessible(true);
                            }
                            // استدعاء الطريقة مباشرة
                            if (loadClass.applicationContext == null) {
                                loadClass.applicationContext = (Context) XposedHelpers.getStaticObjectField(
                                        XposedHelpers.findClass(AutomationResolver.resolve("org.telegram.messenger.ApplicationLoader"), lpparam.classLoader),
                                        AutomationResolver.resolve("ApplicationLoader", "applicationContext", AutomationResolver.ResolverType.Field)
                                );
                            }
                            Language.init(loadClass.applicationContext);
                            Object newItem = itemConstructor.newInstance(8353847, GhostMode, EventType.IconSettings());

                            // إضافة الكائن الجديد إلى القائمة
                            if (items instanceof ArrayList<?>) {
                                ArrayList<Object> typedItems = (ArrayList<Object>) items;
                                if (!ClientChecker.check(ClientChecker.ClientType.TelegramPlus)) {
                                    typedItems.add(newItem);
                                } else {
                                    typedItems.add(10, newItem);
                                }
                            }
                        }
                    }
            );
            Class<?> launchActivityClass = XposedHelpers.findClass(
                    AutomationResolver.resolve("org.telegram.ui.LaunchActivity"),
                    lpparam.classLoader
            );

            AbstractMethodHook onCreateHook = new AbstractMethodHook() {
                @Override
                protected void afterMethod(final MethodHookParam param) {

                    Object LaunchActivtiy = param.thisObject;
                    // تنفيذ الكود بعد استدعاء الدالة
                    ActiveTheme.setActiveTheme();
                    Object drawerLayoutAdapter = XposedHelpers.getObjectField(LaunchActivtiy, AutomationResolver.resolve("LaunchActivity", "drawerLayoutAdapter", AutomationResolver.ResolverType.Field));
                    if (drawerLayoutAdapter != null) {

                        // استدعاء getId وطباعته
                        int result = (int) XposedHelpers.callMethod(drawerLayoutAdapter, AutomationResolver.resolve("DrawerLayoutAdapter", "getId", AutomationResolver.ResolverType.Method), param.args[1]);
                        if (result == 8353847) {
                            final Context applicationContext = (Context) LaunchActivtiy;
                            openDialog(applicationContext, param);
                        }

                    } else {
                        Utils.log("Not found DrawerLayoutAdapter, " + Utils.issue);
                    }
                }
            };
            XposedHelpers.findAndHookMethod(
                    launchActivityClass,
                    AutomationResolver.resolve("LaunchActivity", "lambda$onCreate$8", AutomationResolver.ResolverType.Method), AutomationResolver.merge(AutomationResolver.resolveObject("para5"), onCreateHook));
        }

    }

}
