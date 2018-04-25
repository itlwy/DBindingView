package com.lwy.dbindingview.recycleview;

import com.lwy.dbindingview.recycleview.vm.RcvVM;

/**
 * Created by evan on 6/3/15.
 */
public class Listeners {
    private RcvVM viewModel;

    public Listeners(RcvVM viewModel) {
        this.viewModel = viewModel;
    }
   
    public void onAddItem() {
        viewModel.addItem();
    }

    public void onRemoveItem() {
        viewModel.removeItem();
    }
}
