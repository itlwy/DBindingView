package com.lwy.dbindingview.app.base_widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.lwy.dbindingview.app.R;
import com.lwy.dbindingview.app.base.IView;
import com.lwy.dbindingview.app.databinding.ActivityWidgeBinding;

public class WidgeActivity extends AppCompatActivity implements IView {

    private ActivityWidgeBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_widge);
        mBinding.setViewmodel(new WidgetVM(this));
    }



    @Override
    public Context getContext() {
        return this;
    }
}
