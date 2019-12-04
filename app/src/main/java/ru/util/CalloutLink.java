package ru.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ru.activity.SecondActivity;
import ru.activity.TestActivity;

import static java.lang.Thread.sleep;
import static ru.activity.TestActivity.handtv;
import static ru.util.GetExecut.getTest;

public class CalloutLink extends ClickableSpan {
    private Context context;
    private int color;
    public CalloutLink(Context ctx, int color) {
        this.color = color;
        context = ctx;
    }

    public void updateDrawState(TextPaint ds) {
        ds.setARGB(255, 51, 51, 51);
        ds.setColor(color);
    }
    @Override
    public void onClick(View widget) {
        TextView tv = (TextView) widget;
        Spanned s = (Spanned) tv.getText();
        int i = widget.getId();
        Message msg = new Message();

        switch (widget.getId() ) {
            case  2131230734:
    //            Log.e(debug, "Link:0 " + i + " " + s.subSequence(s.getSpanStart(this), s.getSpanEnd(this)).toString());
              //  String incident = s.subSequence(s.getSpanStart(this), s.getSpanEnd(this)).toString();

              //  tv.append("\n\n     Ждите тестирую " + incident);

               // Toast toast = Toast.makeText(context, "Ждите тестирую " + incident, Toast.LENGTH_LONG);
               // toast.show();

                Intent intent = new Intent(context, TestActivity.class);
                intent.putExtra("test",  s.subSequence(s.getSpanStart(this), s.getSpanEnd(this)).toString());
                context.startActivity(intent);

                break;
            case 2131230742:
                break;
        }
    }
}