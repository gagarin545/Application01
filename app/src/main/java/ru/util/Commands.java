package ru.util;

import android.content.Context;
import android.util.Log;

import com.example.application01.R;

import java.util.List;

import ru.entity.Incident;
import ru.service.GetDivision;
import ru.service.GetIncidents;
import ru.service.GetWorkers;

import static ru.Api.Constants.Debug;

public class Commands {
    private Context context;
    private String Imei;
    List<Incident> incidents;
    public Commands(Context ctx) {
        super();
        this.context = ctx;
    }
    public Commands(Context ctx, String Imei) {
        super();
        this.Imei = Imei;
        this.context = ctx;
    }
    List<Incident> commit(int i) {
        Log.e( Debug, "commit");
        switch (i){
            case R.id.BT1:
                Log.e(Debug, "Сегодня" );
                GetWorkers worker = new GetWorkers( context, Imei);
                Thread t = new Thread(worker);
                t.start();
                while(t.isAlive()) {}
                GetIncidents getIncidents = new GetIncidents(worker.getwork().getIddivision());
                t = new Thread( getIncidents);
                t.start();
                while(t.isAlive()) {}
                incidents = getIncidents.getIncident();

             //   System.out.println(workers.getName());
                Log.e(Debug, String.valueOf(incidents.size()));
                break;
            case R.id.BT2:
                Log.e(Debug, "On week" );
                break;
            case R.id.BT3:
                Log.e(Debug, "Setting" );
                new GetDivision( context).getdivision();
                break;

        }
        return incidents;
    }
}
