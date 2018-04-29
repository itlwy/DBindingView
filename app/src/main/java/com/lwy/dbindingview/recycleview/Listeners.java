package com.lwy.dbindingview.recycleview;

import com.lwy.dbindingview.recycleview.vm.RcvVM;

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
