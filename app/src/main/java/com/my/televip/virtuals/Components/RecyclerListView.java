package com.my.televip.virtuals.Components;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.my.televip.MainHook;

import de.robv.android.xposed.XposedHelpers;

public class RecyclerListView  {

    private final Object recycleristView;

    public RecyclerListView(Object obj){
        recycleristView = obj;
    }

    public RecyclerListView(Context context){
        Class<?> recyclerListView = XposedHelpers.findClassIfExists("org.telegram.ui.Components.RecyclerListView", MainHook.lpparam.classLoader);
        recycleristView = XposedHelpers.newInstance(recyclerListView, context);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        XposedHelpers.callMethod(recycleristView, "setAdapter", adapter);
    }

    public void setLayoutManager(@Nullable RecyclerView.LayoutManager layoutManager){
        XposedHelpers.callMethod(recycleristView, "setLayoutManager", layoutManager);
    }

    public void setItemAnimator(@Nullable RecyclerView.ItemAnimator itemAnimator){
        XposedHelpers.callMethod(recycleristView, "setItemAnimator", itemAnimator);
    }

    public RecyclerView getRecyclerListView(){
        return (RecyclerView) recycleristView;
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }

}
