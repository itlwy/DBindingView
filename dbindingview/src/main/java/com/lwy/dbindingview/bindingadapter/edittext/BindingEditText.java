package com.lwy.dbindingview.bindingadapter.edittext;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_FLAG_SIGNED;

/**
 * Created by lwy on 2018/8/22.
 */

@InverseBindingMethods({
        @InverseBindingMethod(type = BindingEditText.class, attribute = "textInt", method = "getTextInt")
})
public class BindingEditText extends AppCompatEditText {
    public BindingEditText(Context context) {
        super(context);
    }

    public BindingEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BindingEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @BindingAdapter("textInt")
    public static void initTextInt(BindingEditText bindingEditText, Integer textInt) {
        String text;
        if (textInt == null)
            text = "";
        else
            text = textInt.toString();
        String curStr = bindingEditText.getText().toString();
//        对"-"的判断主要是为了做负数的输入做兼容
        if (!"-".equals(curStr) && !curStr.equals(text))
            bindingEditText.setText(text);
    }

    public Integer getTextInt() {
        Integer retInt = null;
        String text = getText().toString();
        if (!"-".equals(text) && !TextUtils.isEmpty(text))
            retInt = Integer.parseInt(text);
        return retInt;
    }

    @BindingAdapter("textIntAttrChanged")
    public static void setValueChangedListener(BindingEditText bindingEditText, InverseBindingListener inverseBindingListener) {
        bindingEditText.setInputType(TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_SIGNED);
        bindingEditText.addTextChangedListener(new CustomTextWatcher(bindingEditText, inverseBindingListener));
    }

    public static class CustomTextWatcher implements TextWatcher {

        private final BindingEditText bindingEditText;
        private final InverseBindingListener inverseBindingListener;

        public CustomTextWatcher(BindingEditText bindingEditText, InverseBindingListener inverseBindingListener) {
            this.bindingEditText = bindingEditText;
            this.inverseBindingListener = inverseBindingListener;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = s.toString();
            try {
                if (!"-".equals(text)) {
                    if (!TextUtils.isEmpty(text)) {
                        Integer.parseInt(text);
                    }
                }
                if (this.inverseBindingListener != null) {
                    this.inverseBindingListener.onChange();
                }
            } catch (NumberFormatException e) {
                //截取新字符串
                String newStr = text.substring(0, text.length() - 1);
                bindingEditText.setText(newStr);
                bindingEditText.setSelection(newStr.length());
            }

        }
    }
}
