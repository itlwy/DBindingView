package com.lwy.dbindingview.bindingadapter.checkbox;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lwy.dbindingview.data.KeyValue;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lwy on 2017/11/6.
 */

@InverseBindingMethods({
        @InverseBindingMethod(
                type = BindingCheckGroup.class,
                attribute = "selectedValues",
                event = "selectedValuesAttrChanged",
                method = "getSelectedValues")
})
public class BindingCheckGroup extends LinearLayout {

    private List<KeyValue> viewSelectedValues = new ArrayList<>();
    private List<KeyValue> sourcesSelectedValues;
    private InverseBindingListener listener;
    private List<KeyValue> dataSources;


    public void setListener(InverseBindingListener listener) {
        this.listener = listener;
    }

    public BindingCheckGroup(Context context) {
        super(context);
        init(context, null, 0);
    }


    public BindingCheckGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BindingCheckGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    }


    @BindingAdapter("items")
    public static void setItems(BindingCheckGroup view, List<KeyValue> dataSources) {
        view.dataSources = dataSources;
        if (dataSources != null) {
            view.removeAllViews();
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            for (KeyValue dataSource : dataSources) {
                BindingCheckBox cb = new BindingCheckBox(view.getContext());
                cb.setLayoutParams(params);
                cb.setValue(dataSource);
                view.addView(cb);
            }
        }
    }

    @BindingAdapter("selectedValues")
    public static void setSelectedValues(BindingCheckGroup view, List<KeyValue> selectedValues) {
        view.sourcesSelectedValues = selectedValues;
        if (selectedValues != null) {
            if (!isSameList(view.viewSelectedValues, selectedValues)) {
                // have changed
                for (int i = 0; i < view.getChildCount(); i++) {
                    View childView = view.getChildAt(i);
                    if (childView instanceof BindingCheckBox) {
                        BindingCheckBox cb = ((BindingCheckBox) childView);
                        KeyValue cbSelValue = cb.getValue();
                        if (selectedValues.contains(cbSelValue))
                            cb.setChecked(true);
                        else
                            cb.setChecked(false);
                    }
                }
            }
            view.viewSelectedValues.clear();
            view.viewSelectedValues.addAll(selectedValues);
        }
    }

    @BindingAdapter("selectedValuesAttrChanged")
    public static void setValueChangedListener(BindingCheckGroup view, final InverseBindingListener bindingListener) {
        if (bindingListener == null) {
            view.setListener(null);
        } else {
            // 通知 ViewModel
            view.setListener(bindingListener);
        }
    }

    public void notifyValuesChange(int checkBoxID, KeyValue checkedValue, boolean ischeck) {
        if (ischeck) {
            // TODO: 2017/11/6 换成不允许重复的集合
            if (!viewSelectedValues.contains(checkedValue)) {
                viewSelectedValues.add(checkedValue);
                sourcesSelectedValues.add(checkedValue);
            }
        } else {
            viewSelectedValues.remove(checkedValue);
            sourcesSelectedValues.remove(checkedValue);
        }

        if (this.listener != null) {
            this.listener.onChange();
        }
    }

    static boolean isSameList(List<KeyValue> list1, List<KeyValue> list2) {
        if (list1 == list2)
            return true;
        if (list1.size() != list2.size())
            return false;
        for (KeyValue keyValue : list2) {
            if (!list1.contains(keyValue)) {
                return false;
            }
        }
        return true;
    }

}
