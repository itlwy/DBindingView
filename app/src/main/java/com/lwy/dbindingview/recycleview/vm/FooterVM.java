package com.lwy.dbindingview.recycleview.vm;

import android.databinding.ObservableField;

import com.lwy.dbindingview.command.ReplyCommand;
import com.lwy.dbindingview.command.functions.Action0;
import com.lwy.dbindingview.command.functions.Action1;
import com.lwy.dbindingview.data.RcVFooterVM;

/**
 * Created by lwy on 2018/4/10.
 */

public class FooterVM extends RcVFooterVM {

    public final ReplyCommand clickCommand = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            if (!getIsFooterLoading().get()) {
                switchLoading(true);
                callback.execute();
            }

        }
    });

    private ReplyCommand<Integer> callback;


    public final ObservableField<String> noMoreTip = new ObservableField<>();
    /*
        state : 0 loading
        state : 1 idle
     */
    public final ObservableField<Integer> state = new ObservableField<>();


    public FooterVM(ReplyCommand callback) {
        super();
        this.callback = callback;
        switchLoading(false);
        noMoreTip.set("暂无更多");
    }

    @Override
    protected ReplyCommand<Integer> geneOnLoadMoreCommand() {
        return new ReplyCommand<>(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                FooterVM.this.callback.execute();
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
