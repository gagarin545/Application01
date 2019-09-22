package ru.util;

import android.util.Log;

import static ru.Api.Constants.Debug;

public class CallCommand implements Command{
private Commands command;
private String Imei;
    public CallCommand(Commands command) {
        this.command = command;
    }
    public CallCommand(Commands command, String Imei) {
        this.command = command;
        this.Imei = Imei;
        Log.e(Debug, "IMEI->" + Imei);
    }
    @Override
    public void execute(int i) {        command.commit(i);    }
}
