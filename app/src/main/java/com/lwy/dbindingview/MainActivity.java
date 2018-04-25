package com.lwy.dbindingview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lwy.dbindingview.base.IView;
import com.lwy.dbindingview.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements IView {


    private MainVM mViewModel;
    private ActivityMainBinding mDatabinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new MainVM(this);
        mDatabinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mDatabinding.setViewmodel(mViewModel);

//        mBinding.executePendingBindings();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
