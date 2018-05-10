package com.lwy.dbindingview.factory;

import android.content.Context;

import com.lwy.dbindingview.bindingadapter.checkbox.BindingCheckBox;
import com.lwy.dbindingview.bindingadapter.radiogroup.DataBindingRadioButton;

/**
 * Created by lwy on 2018/5/10.
 */

public final class ViewFactory {

    public static DBCustomViewFactory<DataBindingRadioButton> createDBRadioButton() {
        return new DBCustomViewFactory<DataBindingRadioButton>() {
            @Override
            public DataBindingRadioButton create(Context context) {
                return new DataBindingRadioButton(context);  // 此处可通过继承DataBindingRadioButton自定义view
            }
        };
    }

    public static DBCustomViewFactory<BindingCheckBox> createDBCheckbox() {
        return new DBCustomViewFactory<BindingCheckBox>() {
            @Override
            public BindingCheckBox create(Context context) {
                return new BindingCheckBox(context);   // 此处可通过继承BindingCheckBox自定义view
            }
        };
    }

}
