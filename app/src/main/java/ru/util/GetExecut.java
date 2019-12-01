package ru.util;

import com.github.mikephil.charting.data.BarData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import ru.entity.Incident;
import ru.entity.Result;
import ru.entity.ViewTest;
import ru.entity.WorkerResult;
import ru.entity.Workers;
import ru.service.GetIncident;
import ru.service.GetIncidentListDay;
import ru.service.GetIncidentRepet;
import ru.service.GetResult;
import ru.service.GetTest;
import ru.service.GetWorker;
import ru.service.GetWorkerResult;

import static ru.Api.Constants.Imei;
import static ru.Api.Constants.change;

public class GetExecut {
    private static int potok = 3;
    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(potok);
    private static Future<List<Incident>> future;
    private static ArrayList<BarData> list = new ArrayList<>();
    private static BarData date;
    private static List <Incident> incidents;
    public GetExecut(List<Incident> incidents) {        GetExecut.incidents = incidents;    }

    public static Workers getWorker() {
        Workers workers = null;
        Future<Workers> futurework = executor.submit( new GetWorker(  Imei).task);
        try {
            workers = futurework.get();
            change = false;
        } catch (ExecutionException|InterruptedException e) { e.printStackTrace(); }
        return workers;
    }
    public static List <Incident> getIncident(int[] iddivision) {
        try {
            future = executor.submit((new GetIncident(iddivision)).task);
            incidents = future.get();
        } catch (ExecutionException | InterruptedException e) { e.printStackTrace();        }
        return incidents;
    }
    public static List <Incident> getIncidentNday(int[] iddivision) {
        try {
            future = executor.submit(new GetIncidentListDay(iddivision, 30).task);
            incidents = future.get();
        } catch (ExecutionException|InterruptedException e) { e.printStackTrace(); }
        return incidents;
    }
    public static List <Incident> getIncidentRepet(int[] iddivision) {
        try {
            future = executor.submit( (new GetIncidentRepet(iddivision)).task);
            incidents = future.get();
        } catch (ExecutionException|InterruptedException e) { e.printStackTrace(); }
        return incidents;
    }
    public static ArrayList<BarData> getOnOrder(int[] iddivision ) {
        Future<List<Result>> future_res = executor.submit((new GetResult(iddivision).task));
        try {
            for (int i : iddivision)
                if ((date = new GenerateData().getBarData(future_res.get(), i)) != null)
                    list.add(date);
        } catch (ExecutionException | InterruptedException e) {            e.printStackTrace();        }
        return list;
    }
    public static  ArrayList<BarData> getWeekOnWorker(int[] iddivision) {
        Calendar calendar = Calendar.getInstance();
        try {
            Future <List<WorkerResult>> future_reswork = executor.submit((new GetWorkerResult( iddivision)).task);
            List<WorkerResult> workerResult = future_reswork.get();
           calendar.setTimeInMillis(workerResult.get(0).getTimeclose());
          for(int i=0; i < 7; i++) {
             if ((date = new GenerateData().getBarData(workerResult, calendar.getTimeInMillis())) != null)
                list.add(date);
           calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ExecutionException|InterruptedException e) { e.printStackTrace(); }
        return list;
    }
    public static String getTest(String incident) {
        ViewTest viewTest  = null;
        Future <ViewTest> future_reswork = executor.submit((new GetTest( incident)).task);
        try {
            viewTest = future_reswork.get();
        } catch (ExecutionException | InterruptedException e) {            e.printStackTrace(); }
        if( viewTest == null)
            return " ";
        else
            return viewTest.toString();
    }
}
