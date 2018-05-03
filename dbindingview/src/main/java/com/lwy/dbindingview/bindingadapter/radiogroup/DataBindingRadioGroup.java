package com.lwy.dbindingview.bindingadapter.radiogroup;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.lwy.dbindingview.data.KeyValue;
import com.lwy.dbindingview.utils.DensityUtil;

import java.util.List;


/**
 * Created by lwy on 2017/11/3.
 */

@InverseBindingMethods({
        @InverseBindingMethod(
                type = DataBindingRadioGroup.class,
                attribute = "selectedValue",
                event = "selectedValueAttrChanged",
                method = "getSelectedValue")
})
public class DataBindingRadioGroup extends RadioGroup {

    //    private InverseBindingListener mBindingListener;
    private KeyValue selectedValue;
    //    private OnValueChangedListener listener;
    private InverseBindingListener listener;

    public DataBindingRadioGroup(Context context) {
        super(context);
        init();
    }

    public DataBindingRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                if (checkedId > 0) {
                    DataBindingRadioButton radioButton = (DataBindingRadioButton) findViewById(checkedId);
                    KeyValue keyValue = new KeyValue(radioButton.getValue().key, radioButton.getValue().value);
                    setSelectedValue(radioButton.isChecked() ? keyValue : null);
                } else {
                    setSelectedValue(null);
                }
            }
        });
    }

    public KeyValue getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(KeyValue selectedValue) {
        if (selectedValue != null && this.selectedValue != null) {
            Integer inputKey = selectedValue.key;
            Integer originKey = this.selectedValue.key;
            if (isSame(originKey, inputKey)) {
                return;
            }
        }
        if (selectedValue == null && this.selectedValue == null)
            return;

        this.selectedValue = selectedValue;

        if (this.selectedValue == null) {
            clearCheck();
        } else {
            DataBindingRadioButton customRadioButton = findViewById(getCheckedRadioButtonId());
            Integer key = this.selectedValue.key;
            if (customRadioButton == null || !isSame(key, customRadioButton.getValue().key)) {

                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    if (child instanceof DataBindingRadioButton) {
                        Integer value = ((DataBindingRadioButton) child).getValue().key;
                        if (isSame(this.selectedValue.key, value)) {
                            ((DataBindingRadioButton) child).setChecked(true);
                        }
                    }
                }
            }
        }

        if (listener != null) {
            listener.onChange();
        }
    }

    public void setListener(InverseBindingListener listener) {
        this.listener = listener;
    }

    //    public void setListener(OnValueChangedListener listener) {
//        this.listener = listener;
//    }

//    public interface OnValueChangedListener {
//        void onValueChanged();
//    }

    @BindingAdapter("items")
    public static void setItems(DataBindingRadioGroup radioGroup, List<KeyValue> items) {
        if (items != null) {
            radioGroup.removeAllViews();
            if (items.size() > 0) {
                LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.rightMargin = DensityUtil.dip2px(radioGroup.getContext(), 10);
                for (KeyValue item : items) {
                    DataBindingRadioButton rb = new DataBindingRadioButton(radioGroup.getContext());
                    rb.setLayoutParams(params);
                    radioGroup.addView(rb);
                    DataBindingRadioButton.setValue(rb, item);
                }
            }
        }

    }

    @BindingAdapter("selectedValueAttrChanged")
    public static void setValueChangedListener(DataBindingRadioGroup view, final InverseBindingListener bindingListener) {
        if (bindingListener == null) {
            view.setListener(null);
        } else {
            // 通知 ViewModel
            view.setListener(bindingListener);
        }
    }

    static boolean isSame(Integer value1, Integer value2) {
        return (value1 == null && value2 == null) || (value1 != null && value1.equals(value2));
    }
}
