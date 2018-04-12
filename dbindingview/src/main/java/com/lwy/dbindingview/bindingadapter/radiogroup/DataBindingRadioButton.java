package com.lwy.dbindingview.bindingadapter.radiogroup;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.widget.RadioGroup;

import com.lwy.dbindingview.data.KeyValue;

import static com.lwy.dbindingview.bindingadapter.radiogroup.DataBindingRadioGroup.isSame;


/**
 * Created by mac on 2017/11/3.
 */

public class DataBindingRadioButton extends AppCompatRadioButton {
    private Integer value;

    public DataBindingRadioButton(Context context) {
        super(context);
    }

    public DataBindingRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DataBindingRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


    @Override
    public void toggle() {
        if (isChecked()) {
            if (getParent() instanceof RadioGroup) {
                // 点击选中的 RadioButton，可以取消选择
                ((RadioGroup) getParent()).clearCheck();
            }
        } else {
            setChecked(true);
        }
    }

    @BindingAdapter(value = {"value"})
    public static void setValue(DataBindingRadioButton radioButton, Integer value) {
        radioButton.setValue(value);
        ViewParent parent = radioButton.getParent();
        if (parent instanceof DataBindingRadioGroup) {
            KeyValue checkedValue = ((DataBindingRadioGroup) parent).getCheckedValue();
            if (checkedValue != null) {
                radioButton.setChecked(isSame(checkedValue.key, value));
            } else
                radioButton.setChecked(false);
        }
    }


}
