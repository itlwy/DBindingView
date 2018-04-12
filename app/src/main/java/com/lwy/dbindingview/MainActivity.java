package com.lwy.dbindingview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lwy.dbindingview.databinding.RecyclerViewBinding;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewBinding mBinding;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.recycler_view);
        viewModel = new MyViewModel();
        mBinding.setViewModel(viewModel);
        mBinding.setListeners(new Listeners(viewModel));
//        mBinding.executePendingBindings();
    }
}
