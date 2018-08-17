package com.lwy.dbindingview.bindingadapter.swiperefresh;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.v4.widget.SwipeRefreshLayout;

import com.lwy.dbindingview.command.ReplyCommand;

@InverseBindingMethods({
        @InverseBindingMethod(type = SwipeRefreshLayout.class, attribute = "android:refreshing", method = "isRefreshing")
})
public class ViewBindingAdapter {


    @BindingAdapter(value = {"onRefreshCommand", "android:refreshingAttrChanged"}, requireAll = false)
    public static void onRefreshCommand(SwipeRefreshLayout swipeRefreshLayout, final ReplyCommand onRefreshCommand,
                                        final InverseBindingListener bindingListener) {
        if (onRefreshCommand != null)
            swipeRefreshLayout.setOnRefreshListener(new onRefreshComponentListener(onRefreshCommand, bindingListener));
    }

    public static class onRefreshComponentListener implements SwipeRefreshLayout.OnRefreshListener {

        private final ReplyCommand onRefreshCommand;
        private final InverseBindingListener bindingListener;

        public onRefreshComponentListener(ReplyCommand onRefreshCommand, InverseBindingListener bindingListener) {
            this.onRefreshCommand = onRefreshCommand;
            this.bindingListener = bindingListener;
        }

        @Override
        public void onRefresh() {
            if (this.bindingListener != null)
                this.bindingListener.onChange();
            onRefreshCommand.execute();
        }
    }
}
