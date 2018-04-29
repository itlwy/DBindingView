package com.lwy.dbindingview.command;


import com.lwy.dbindingview.command.functions.Action0;
import com.lwy.dbindingview.command.functions.Action1;

public class ReplyCommand<T> {

    private Action0 execute0;
    private Action1<T> execute1;
    private boolean canExecute = true;

    public ReplyCommand(Action0 execute) {
        this.execute0 = execute;
    }


    public ReplyCommand(Action1<T> execute) {
        this.execute1 = execute;
    }

    /**
     * @param execute    callback for event
     * @param canExecute if this function return true the action execute would be invoked! otherwise would't invoked!
     */
    public ReplyCommand(Action0 execute, boolean canExecute) {
        this.execute0 = execute;
        this.canExecute = canExecute;
    }

    /**
     * @param execute    callback for event,this callback need a params
     * @param canExecute if this function return true the action execute would be invoked! otherwise would't invoked!
     */
    public ReplyCommand(Action1<T> execute, boolean canExecute) {
        this.execute1 = execute;
        this.canExecute = canExecute;
    }


    public void execute() {
        if (execute0 != null && canExecute) {
            execute0.call();
        }
    }


    public void execute(T parameter) {
        if (execute1 != null && canExecute) {
            execute1.call(parameter);
        }
    }

}
