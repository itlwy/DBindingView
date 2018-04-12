package com.lwy.dbindingview.bindingadapter.spinner;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.lwy.dbindingview.adapter.BindingArrayAdapter;
import com.lwy.dbindingview.data.KeyValue;

import java.util.List;


@InverseBindingMethods({
        @InverseBindingMethod(
                type = BindingSpinner.class,
                attribute = "selectedValue",
                event = "selectedValueAttrChanged",
                method = "getSelectedValue")
})
public class BindingSpinner extends AppCompatSpinner {

    private InverseBindingListener listener;
    private KeyValue selectedValue;
//    private List<KeyValue> items;

    public void setValueListener(InverseBindingListener listener) {
        this.listener = listener;
    }

    public BindingSpinner(Context context) {
        super(context);
        init(context, -1, null);
    }


    public BindingSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, -1, attrs);
    }

    private void init(Context context, int mode, AttributeSet attrs) {
        setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                BindingArrayAdapter bindingArrayAdapter = (BindingArrayAdapter) BindingSpinner.this.getAdapter();
                selectedValue = bindingArrayAdapter.getObjects().get(position);
                if (listener != null) {
                    listener.onChange();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    @BindingAdapter(value = {"selectedValue", "spinneritems"})
    public static void selectedValue(BindingSpinner spinner, KeyValue selectedValue, List<KeyValue> items) {
        if (spinner.getAdapter() == null) {
            // 第一次进来为初始化adapter时 ，先初始化adapter 然后再用setadapter方法的默认触发onitemselect事件重新触发来选中
            spinner.selectedValue = selectedValue;
            initAdapter(spinner, items, selectedValue);
            return;
        }
        if (spinner.getAdapter() instanceof BindingArrayAdapter) {
            BindingArrayAdapter adapter = (BindingArrayAdapter) spinner.getAdapter();
            if (items.size() != adapter.getObjects().size() || diff(adapter.getObjects(), items)) {
                initAdapter(spinner, items, selectedValue);
            }
            if (selectedValue != null && !selectedValue.value.equals(spinner.selectedValue.value)) {
                setSelection(spinner, selectedValue);
            }
        }

    }

    private static void setSelection(BindingSpinner spinner, KeyValue selectedValue) {
        if (selectedValue.key > -1) {
            // 有key 就用key来判断
            BindingArrayAdapter bindingArrayAdapter = (BindingArrayAdapter) spinner.getAdapter();
            for (int i = 0; i < bindingArrayAdapter.getObjects().size(); i++) {
                KeyValue item = bindingArrayAdapter.getObjects().get(i);
                if (item.key == selectedValue.key) {
                    spinner.setSelection(i);
                    spinner.selectedValue = selectedValue;
                    break;
                }
            }
        } else {
            for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
                String temp = (String) spinner.getAdapter().getItem(i);
                if (temp.equals(selectedValue.value)) {
                    spinner.setSelection(i);
                    spinner.selectedValue = selectedValue;
                    break;
                }
            }
        }
    }

    private static void initAdapter(BindingSpinner spinner, List<KeyValue> items, KeyValue selectedValue) {
        if (spinner.getAdapter() == null) {
            BindingArrayAdapter adapter = new BindingArrayAdapter(spinner.getContext(), android.R.layout.simple_spinner_item,
                    items);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        } else {
            BindingArrayAdapter arrayAdapter = (BindingArrayAdapter) spinner.getAdapter();
            arrayAdapter.clear();
            arrayAdapter.addAll(items);
        }
        if (selectedValue != null) {
            setSelection(spinner, selectedValue);
        }

    }

    private static boolean diff(List<KeyValue> items, List<KeyValue> items1) {
        boolean flag = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != items1.get(i)) {
                flag = true;
            }
        }
        return flag;
    }


    public KeyValue getSelectedValue() {
        return selectedValue;
    }

    @BindingAdapter("selectedValueAttrChanged")
    public static void setValueChangedListener(BindingSpinner view, final InverseBindingListener bindingListener) {
        if (bindingListener == null) {
            view.setValueListener(null);
        } else {
            // 通知 ViewModel
            view.setValueListener(bindingListener);
        }
    }

}
