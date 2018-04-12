package com.lwy.dbindingview;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

public class ItemViewModel extends BaseObservable {
    public final boolean checkable;
    @Bindable
    private int index;
    @Bindable
    private boolean checked;

    public ItemViewModel(int index, boolean checkable) {
        this.index = index;
        this.checkable = checkable;
    }

    public int getIndex() {
        return index;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean onToggleChecked(View v) {
        if (!checkable) {
            return false;
        }
        checked = !checked;
//        notifyPropertyChanged(com.lwy.dbindingview.BR.checked);
        return true;
    }
}
