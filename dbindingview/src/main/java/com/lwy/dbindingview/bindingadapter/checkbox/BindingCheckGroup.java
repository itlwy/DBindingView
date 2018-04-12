package com.lwy.dbindingview.bindingadapter.checkbox;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by mac on 2017/11/6.
 */

@InverseBindingMethods({
        @InverseBindingMethod(
                type = BindingCheckGroup.class,
                attribute = "selectedValues",
                event = "selectedValuesAttrChanged",
                method = "getSelectedValues")
})
public class BindingCheckGroup extends LinearLayout {

    private String selectedValues;
    private InverseBindingListener listener;

    public void setListener(InverseBindingListener listener) {
        this.listener = listener;
    }

    public String getSelectedValues() {
        return selectedValues;
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

    @BindingAdapter("selectedValues")
    public static void setSelectedValues(BindingCheckGroup view, String selectedValues) {
        if (selectedValues != null && !selectedValues.equals(view.selectedValues)) {
            List<String> selectedList = splitAsList(selectedValues,",");
            List<String> viewSelectedList = splitAsList(view.selectedValues,",");

            List<String> differentList = getdifferentList(viewSelectedList, selectedList);
            if (differentList.size() > 0) {
                // 有变化
                for (int i = 0; i < view.getChildCount(); i++) {
                    View childView = view.getChildAt(i);
                    if (childView instanceof BindingCheckBox) {
                        BindingCheckBox cb = ((BindingCheckBox) childView);
                        String cbSelValue = cb.getText().toString();
                        if (differentList.contains(cbSelValue)) {
                            if (viewSelectedList.contains(cbSelValue))
                                cb.setChecked(false);
                            else
                                cb.setChecked(true);
                        }
                    }
                }
            }
            view.selectedValues = selectedValues;
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

    public void notifyValuesChange(int checkBoxID, String checkBoxLabel, boolean ischeck) {
        List<String> selectedList = splitAsList(this.selectedValues, ",");
        if (ischeck) {
            // TODO: 2017/11/6 换成不允许重复的集合
            if (!selectedList.contains(checkBoxLabel))
                selectedList.add(checkBoxLabel);
        } else {
            selectedList.remove(checkBoxLabel);
        }

        this.selectedValues = joinFromList(selectedList, ",");
        if (this.listener != null) {
            this.listener.onChange();
        }
    }

    static List<String> getdifferentList(List<String> list1, List<String> list2) {
        Map<String, Integer> map = new HashMap();
        List<String> longList = list1;
        List<String> shortList = list2;
        if (list2.size() > list1.size()) {
            longList = list2;
            shortList = list1;
        }
        for (String string : shortList) {//将shortList放到map中，map的value任意数字即可
            map.put(string, 0);
        }
//        shortList.clear();//清空shortList，用于存放longList中有map中没有的数据
        List<String> diffList = new ArrayList<>();
        Integer in;
        for (String string : longList) {
            in = map.get(string);
            if (null == in) {
                diffList.add(string);//longList中有map中没有的数据
            }
        }
        return diffList;
    }

    static List<String> splitAsList(String str, String splitter) {
        ArrayList<String> list = new ArrayList<>();
        if (TextUtils.isEmpty(str))
            return list;
        String[] array = str.split(splitter);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static String joinFromList(List<String> list, String splitter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            sb.append(item).append(splitter);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
