package com.lwy.dbindingview.recycleview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lwy.dbindingview.R;
import com.lwy.dbindingview.databinding.ActivityRecycleView1Binding;
import com.lwy.dbindingview.recycleview.vm.RcvVM;

public class RecycleViewActivity1 extends AppCompatActivity {

    private ActivityRecycleView1Binding mBinding;
    private RcvVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_recycle_view1);
        viewModel = new RcvVM();
        mBinding.setViewmodel(viewModel);
        mBinding.setListeners(new Listeners(viewModel));
    }
}
