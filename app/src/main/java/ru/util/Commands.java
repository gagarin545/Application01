package ru.util;

import android.content.Context;
import android.util.Log;
import com.example.application01.R;
import ru.service.getDivision;
import static ru.Api.Constants.Debug;
import static ru.activity.MainActivity.et1;

public class Commands {
    private Context context;
    private String Imei;
    public Commands(Context ctx) {
        super();
        this.context = ctx;
    }
    public Commands(Context ctx, String Imei) {
        super();
        this.Imei = Imei;
        this.context = ctx;
    }
    void commit(int i) {
        Log.e( Debug, "commit");
        switch (i){
            case R.id.BT1:
                Log.e(Debug, "Послать" + et1.getText());
                break;
            case R.id.BT2:
                Log.e(Debug, "Тестировать" + et1.getText());
                break;
            case R.id.BT7:
                Log.e(Debug, "Bt7");
                new getDivision( context).getdivision();
                break;
        }

    }
}
