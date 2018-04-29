package com.lwy.dbindingview.command;


import com.lwy.dbindingview.command.functions.Action3;

public class ReplyCommand3<T1, T2, T3> {

    private Action3<T1, T2, T3> execute3;
    private boolean canExecute= true;

    public ReplyCommand3(Action3<T1, T2, T3> execute) {
        this.execute3 = execute;
    }


    /**
     * @param execute    callback for event
     * @param canExecute if this function return true the action execute would be invoked! otherwise would't invoked!
     */
    public ReplyCommand3(Action3<T1, T2, T3> execute, boolean canExecute) {
        this.execute3 = execute;
        this.canExecute = canExecute;
    }


    public void execute(T1 parameter1, T2 parameter2, T3 parameter3) {
        if (execute3 != null && canExecute) {
            execute3.call(parameter1, parameter2, parameter3);
        }
    }

}
