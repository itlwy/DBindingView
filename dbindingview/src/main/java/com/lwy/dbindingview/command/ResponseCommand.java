package com.lwy.dbindingview.command;

import com.lwy.dbindingview.command.functions.Func0;
import com.lwy.dbindingview.command.functions.Func1;

public class ResponseCommand<T, R> {

    private Func0<R> execute0;
    private Func1<T, R> execute1;

    private boolean canExecute= true;

    /**
     * like {@link ReplyCommand},but ResponseCommand can return result when command has executed!
     *
     * @param execute function to execute when event occur.
     */
    public ResponseCommand(Func0<R> execute) {
        this.execute0 = execute;
    }


    public ResponseCommand(Func1<T, R> execute) {
        this.execute1 = execute;
    }


    public ResponseCommand(Func0<R> execute, boolean canExecute) {
        this.execute0 = execute;
        this.canExecute = canExecute;
    }


    public ResponseCommand(Func1<T, R> execute, boolean canExecute) {
        this.execute1 = execute;
        this.canExecute = canExecute;
    }


    public R execute() {
        if (execute0 != null && canExecute) {
            return execute0.call();
        }
        return null;
    }


    public R execute(T parameter) {
        if (execute1 != null && canExecute) {
            return execute1.call(parameter);
        }
        return null;
    }

}
