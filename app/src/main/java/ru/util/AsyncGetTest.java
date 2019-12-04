package ru.util;

import android.os.AsyncTask;
import android.text.Html;

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
        tvTest.setText(Html.fromHtml("\n<font COLOR='#D81B60'><b>\t\tЖдите тестирую " + incident + "<blink> ... </blink>"));
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