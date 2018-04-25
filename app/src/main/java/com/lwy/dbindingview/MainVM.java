package com.lwy.dbindingview;

import android.content.Context;
import android.content.Intent;

import com.lwy.dbindingview.base.IView;
import com.lwy.dbindingview.base_widget.WidgeActivity;
import com.lwy.dbindingview.recycleview.RecycleViewActivity1;

import java.lang.ref.WeakReference;

/**
 * Created by mac on 2018/4/23.
 */

public class MainVM {

    private WeakReference<IView> mView;

    public MainVM(IView view) {
        mView = new WeakReference<>(view);
    }

    public MainVM() {
    }

    public void skip2Rcv() {
        Context context = mView.get().getContext();
        Intent intent = new Intent(context, RecycleViewActivity1.class);
        context.startActivity(intent);
    }

    public void skip2Normal() {
        Context context = mView.get().getContext();
        Intent intent = new Intent(context, WidgeActivity.class);
        context.startActivity(intent);
    }
}
