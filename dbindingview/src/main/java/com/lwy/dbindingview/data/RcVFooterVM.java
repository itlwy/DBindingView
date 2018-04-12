package com.lwy.dbindingview.data;

import com.lwy.dbindingview.command.ReplyCommand;

/**
 * Created by mac on 2018/4/10.
 */

public abstract class RcVFooterVM {

    private ReplyCommand<Integer> onLoadMoreCommand;

    private final BooleanWrapper isFooterLoading = new BooleanWrapper();

    public RcVFooterVM() {
        onLoadMoreCommand = geneOnLoadMoreCommand();
    }

    public ReplyCommand<Integer> getOnLoadMoreCommand() {
        return onLoadMoreCommand;
    }

    public BooleanWrapper getIsFooterLoading() {
        return isFooterLoading;
    }

    public void switchLoading(boolean flag) {
        isFooterLoading.set(flag);
    }

    /**
     * child class should always override this method to provide a callback for recycleview
     *
     * @return a command and when the last item in recycleview show up,it will be called
     */
    protected abstract ReplyCommand<Integer> geneOnLoadMoreCommand();
}
