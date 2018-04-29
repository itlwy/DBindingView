package com.lwy.dbindingview.bindingadapter.checkbox;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import com.lwy.dbindingview.data.KeyValue;

/**
 * Created by lwy on 2017/11/6.
 */

public class BindingCheckBox extends android.support.v7.widget.AppCompatCheckBox {

    private KeyValue value = new KeyValue();

    public KeyValue getValue() {
        return value;
    }

    public void setValue(KeyValue value) {
        this.value.key = value.key;
        this.value.value = value.value;
        setText(value.value);
    }

    public BindingCheckBox(Context context) {
        super(context);
        init(context, null, 0);
    }

    public BindingCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BindingCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (getParent() instanceof BindingCheckGroup) {
                    BindingCheckGroup bGroup = (BindingCheckGroup) getParent();
                    bGroup.notifyValuesChange(BindingCheckBox.this.getId()
                            , value, b);
                }
            }
        });
    }

}
