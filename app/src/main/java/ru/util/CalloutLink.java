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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import ru.entity.ViewTest;
import ru.service.GetTest;

import static ru.activity.MainActivity.executor;

public class CalloutLink extends ClickableSpan {
    String debug = "DEbug";
    private Context context;
    private int color;
    private static String num_incident;
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
        Log.e(debug, String.valueOf(i));

        switch (widget.getId() ) {
            case  2131230734:
                num_incident =  s.subSequence(s.getSpanStart(this), s.getSpanEnd(this)).toString();
                Log.e(debug, "Link:0 " + i + " " + s.subSequence(s.getSpanStart(this), s.getSpanEnd(this)).toString());
                Future <ViewTest> future_test = executor.submit( new GetTest( num_incident).task);
                try {
                    ViewTest viewTest = future_test.get();
                    Log.e(debug, "Link:1 " + i + " " + viewTest.getService());
                } catch (ExecutionException | InterruptedException e) { e.printStackTrace();                }



          //      Intent intent = new Intent(context, thirdActivity.class);
           //     intent.putExtra("word", s.subSequence(s.getSpanStart(this), s.getSpanEnd(this)).toString());
            //    context.startActivity(intent);
                break;
            case 2131230742:
          //  case 2131230738:
                //Log.e(debug, "next " + i + " !" + s.subSequence(s.getSpanStart(this), s.getSpanEnd(this)).toString());
                switch (s.subSequence(s.getSpanStart(this),s.getSpanEnd(this)).toString().substring(0,2)) {
                    case "+7":
                        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + s.subSequence(s.getSpanStart(this), s.getSpanEnd(this)).toString())));
                        break;
                    case "![":
              //          Intent intentFour = new Intent(context, fourActivity.class);
                //        intentFour.putExtra("word", s.subSequence( s.getSpanStart(this), s.getSpanEnd(this)).toString() );
                       // Log.e(debug, "s: -> " + s.subSequence( s.getSpanStart(this), s.getSpanEnd(this)));
               //         intentFour.putExtra("word", s.subSequence( s.getSpanStart(this), s.getSpanEnd(this)).toString());
                 //       context.startActivity(intentFour);
                        break;
                    default:
                    //    Intent intentSecond = new Intent(context, SecondActivity.class);
                        //intentSecond.putExtra("word", s.subSequence( s.getSpanStart(this), s.getSpanEnd(this)).toString().substring( s.subSequence( s.getSpanStart(this), s.getSpanEnd(this)).length()-9) );
                      //  Log.e(debug, "Link0: " + i + " " +  s.subSequence( s.getSpanStart(this), s.getSpanEnd(this)).toString().substring( s.subSequence( s.getSpanStart(this), s.getSpanEnd(this)).length()-9));
                     //   Log.e(debug, "Link1: " + i + " " + s.subSequence(s.getSpanStart(this), s.getSpanEnd(this)).toString());
                        Log.e(debug, "Link2: " + i + " " + num_incident);
                        //intentSecond.putExtra("word", s.subSequence( s.getSpanStart(this), s.getSpanEnd(this)).toString());
                      //  intentSecond.putExtra("word", num_incident );
                       // context.startActivity(intentSecond);
                }
                break;
            case 2131230739:
                Message msg = new Message();
                msg.obj =  s.subSequence( s.getSpanStart(this), s.getSpanEnd(this)).toString();
                //handtv5.sendMessage(msg);
        }
    }
}