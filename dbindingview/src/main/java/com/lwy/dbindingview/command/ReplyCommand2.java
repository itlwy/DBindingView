package com.lwy.dbindingview.command;


import com.lwy.dbindingview.command.functions.Action2;
import com.lwy.dbindingview.command.functions.Func0;

public class ReplyCommand2<T1, T2> {

    private Action2<T1, T2> execute2;
    private Func0<Boolean> canExecute0;

    public ReplyCommand2(Action2<T1, T2> execute) {
        this.execute2 = execute;
    }


    /**
     * @param execute     callback for event
     * @param canExecute0 if this function return true the action execute would be invoked! otherwise would't invoked!
     */
    public ReplyCommand2(Action2<T1, T2> execute, Func0<Boolean> canExecute0) {
        this.execute2 = execute;
        this.canExecute0 = canExecute0;
    }


    private boolean canExecute0() {
        if (canExecute0 == null) {
            return true;
        }
        return canExecute0.call();
    }


    public void execute(T1 parameter1, T2 parameter2) {
        if (execute2 != null && canExecute0()) {
            execute2.call(parameter1, parameter2);
        }
    }

}
