package com.lwy.dbindingview.factory;

import android.content.Context;

/**
 * Created by lwy on 2018/5/10.
 */

public interface DBCustomViewFactory<T> {

    T create(Context context);

}
