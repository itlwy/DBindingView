package com.lwy.dbindingview.bindingadapter.checkbox;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/**
 * Created by mac on 2017/11/6.
 */

public class BindingCheckBox extends android.support.v7.widget.AppCompatCheckBox {
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
                            , BindingCheckBox.this.getText().toString(), b);
                }
            }
        });
    }

}
