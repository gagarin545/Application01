package ru.util;

public class Control {
    private Command slot;
    public Control() {        super();    }
    public void SetCommand(Command command) { this.slot = command;    }
    public void press(int i){        slot.execute(i);    }
}
