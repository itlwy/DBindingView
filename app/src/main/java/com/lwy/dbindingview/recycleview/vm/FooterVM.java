package com.lwy.dbindingview.recycleview.vm;

import android.databinding.ObservableField;

import com.lwy.dbindingview.command.ReplyCommand;
import com.lwy.dbindingview.command.functions.Action1;
import com.lwy.dbindingview.data.RcVFooterVM;

/**
 * Created by lwy on 2018/4/10.
 */

public class FooterVM extends RcVFooterVM {

    private ReplyCommand<Integer> callback;
    public final ObservableField<String> noMoreTip = new ObservableField<>();
    /*
        state : 0 loading
        state : 1 idle
     */
    public final ObservableField<Integer> state = new ObservableField<>();


    public FooterVM(ReplyCommand<Integer> callback) {
        super();
        this.callback = callback;
        state.set(1);
        noMoreTip.set("暂无更多");
    }

    @Override
    protected ReplyCommand<Integer> geneOnLoadMoreCommand() {
        return new ReplyCommand<>(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                FooterVM.this.callback.execute(integer);
//                switchLoading(true);
            }
        });
    }

    @Override
    public void switchLoading(boolean flag) {
        if (flag) {
            state.set(0);
        } else {
            state.set(1);
        }
        super.switchLoading(flag);
    }
}
