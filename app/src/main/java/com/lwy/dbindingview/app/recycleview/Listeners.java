package com.lwy.dbindingview.app.recycleview;

import com.lwy.dbindingview.app.recycleview.vm.RcvVM;

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
