package com.lwy.dbindingview.command;


import com.lwy.dbindingview.command.functions.Action3;
import com.lwy.dbindingview.command.functions.Func0;

public class ReplyCommand3<T1, T2, T3> {

    private Action3<T1, T2, T3> execute3;
    private Func0<Boolean> canExecute0;

    public ReplyCommand3(Action3<T1, T2, T3> execute) {
        this.execute3 = execute;
    }


    /**
     * @param execute     callback for event
     * @param canExecute0 if this function return true the action execute would be invoked! otherwise would't invoked!
     */
    public ReplyCommand3(Action3<T1, T2, T3> execute, Func0<Boolean> canExecute0) {
        this.execute3 = execute;
        this.canExecute0 = canExecute0;
    }


    private boolean canExecute0() {
        if (canExecute0 == null) {
            return true;
        }
        return canExecute0.call();
    }


    public void execute(T1 parameter1, T2 parameter2, T3 parameter3) {
        if (execute3 != null && canExecute0()) {
            execute3.call(parameter1, parameter2, parameter3);
        }
    }

}
