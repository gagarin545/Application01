package ru.util;

import android.content.Context;
import android.content.Intent;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import ru.activity.TestActivity;

public class CalloutLink extends ClickableSpan {
    private Context context;
    public CalloutLink(Context ctx ) {
        context = ctx;
    }

    public void updateDrawState(TextPaint ds) {
        ds.setARGB(255, 51, 51, 51);
    }
    @Override
    public void onClick(View widget) {
        TextView tv = (TextView) widget;
        Spanned s = (Spanned) tv.getText();
        int i = widget.getId();

        switch (widget.getId() ) {
            case  2131230734:
                Intent intent = new Intent(context, TestActivity.class);
                intent.putExtra("test",  s.subSequence(s.getSpanStart(this), s.getSpanEnd(this)).toString());
                context.startActivity(intent);
                break;
            case 2131230742:
                break;
        }
    }
}