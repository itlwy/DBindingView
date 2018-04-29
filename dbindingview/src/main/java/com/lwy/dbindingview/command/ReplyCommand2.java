package com.lwy.dbindingview.command;


import com.lwy.dbindingview.command.functions.Action2;

public class ReplyCommand2<T1, T2> {

    private Action2<T1, T2> execute2;
    private boolean canExecute = true;

    public ReplyCommand2(Action2<T1, T2> execute) {
        this.execute2 = execute;
    }


    /**
     * @param execute    callback for event
     * @param canExecute if this function return true the action execute would be invoked! otherwise would't invoked!
     */
    public ReplyCommand2(Action2<T1, T2> execute, boolean canExecute) {
        this.execute2 = execute;
        this.canExecute = canExecute;
    }


    public void execute(T1 parameter1, T2 parameter2) {
        if (execute2 != null && canExecute) {
            execute2.call(parameter1, parameter2);
        }
    }

}
