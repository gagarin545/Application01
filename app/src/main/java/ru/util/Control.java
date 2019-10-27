package ru.util;

public class Control {
    private Command slot;
    public Control() {        super();    }
    public void SetCommand(Command slot) {        this.slot = slot;    }
    public void press(int i){        slot.execute(i);    }
}
