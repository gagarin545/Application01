package ru.util;

import android.os.AsyncTask;
import android.text.Html;

import java.util.concurrent.TimeUnit;

import ru.entity.ViewTest;

import static ru.activity.TestActivity.tvTest;
import static ru.util.GetExecut.getTest;


public class AsyncGetTest extends AsyncTask<Void, Void, Void> {
    private String incident, viewTest;
    public AsyncGetTest(String incident) {
        this.incident = incident;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tvTest.setText(" Ждите тестирую " + incident);
    }

    @Override
    protected Void doInBackground(Void... voids) {
     viewTest = getTest(incident);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        tvTest.setText (Html.fromHtml( viewTest));
    }
}