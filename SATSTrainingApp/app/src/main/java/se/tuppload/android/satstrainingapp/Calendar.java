package se.tuppload.android.satstrainingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class Calendar extends View {

    Paint paint;
    final boolean filled;
    final int position;
    public static final int notFilledRadius = 30;
    public static final int filledRadius = 35;
    int radius;
    float earlierY = 298;

    public Calendar(Context context, final boolean filled, final int position) {
        super(context);
        this.filled = filled;
        this.position = position;
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(getResources().getColor(R.color.orange));
        paint.setStrokeWidth(11);

        if (filled == true) {
            paint.setStyle(Paint.Style.FILL);
            radius = filledRadius;
        } else {
            paint.setStyle(Paint.Style.STROKE);
            radius = notFilledRadius;
        }

        float case5Y = 73;
        float case4Y = 150;
        float case3Y = 226;
        float case2Y = 298;
        float case1Y = 373;
        float caseDefaultY = 73;


        switch (position) {
            case 5:
                canvas.drawCircle(110, case5Y, radius, paint);
                canvas.drawLine(-110, earlierY, 110, case5Y, paint);
                writeText(102, 80, "5", canvas);
                break;
            case 4:
                canvas.drawCircle(110, case4Y, radius, paint);
                canvas.drawLine(-110, earlierY, 110, case4Y, paint);
                writeText(102, 160, "4", canvas);
                break;
            case 3:
                canvas.drawCircle(110, case3Y, radius, paint);
                canvas.drawLine(-110, earlierY, 110, case3Y, paint);
                writeText(102, 236, "3", canvas);
                break;
            case 2:
                canvas.drawCircle(110, case2Y, radius, paint);
                canvas.drawLine(-110, earlierY, 110, case2Y, paint);
                writeText(102, 308, "2", canvas);
                break;
            case 1:
                canvas.drawCircle(110, case1Y, radius, paint);
                canvas.drawLine(-110, earlierY, 110, case1Y, paint);
                writeText(102, 383, "1", canvas);
                break;
            default:
                canvas.drawCircle(110, caseDefaultY, radius, paint);
                canvas.drawLine(-110, earlierY, 110, caseDefaultY, paint);
                writeText(92, 80, "+5", canvas);
                break;
        }

        CalendarAdapter.earlierPos = position;

        switch ((int) CalendarAdapter.earlierPos) {
            case 5:
                earlierY = case5Y;
                break;
            case 4:
                earlierY = case4Y;
                break;
            case 3:
                earlierY = case3Y;
                break;
            case 2:
                earlierY = case2Y;
                break;
            case 1:
                earlierY = case1Y;
                break;
            case 0:
                earlierY = 300;
                break;
            default:
                earlierY = caseDefaultY;
                break;
        }

        Log.e("Earlier Y", String.valueOf(earlierY));

        super.onDraw(canvas);

//        paint.setColor(getResources().getColor(R.color.orange));
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(11);
//        canvas.drawCircle(110, 65, 30, paint);
//        paint.setStrokeWidth(4);
//        paint.setTextSize(32);
//        paint.setColor(Color.BLACK);
//        canvas.drawText("+7", 92, 75, paint);
//
//
//
//        paint.setTextSize(80);
//        paint.setStrokeWidth(200);
//        paint.setColor(getResources().getColor(R.color.orange));
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(300, 300, 80, paint);

        paint.setColor(Color.WHITE);
        canvas.drawText("3", 275, 330, paint);
    }

    public void writeText(int x, int y, String text, Canvas canvas) {
        paint.setStrokeWidth(4);
        paint.setTextSize(32);

        if (filled) {
            paint.setColor(Color.WHITE);
        } else {
            paint.setColor(Color.BLACK);
        }

        canvas.drawText(text, x, y, paint);
    }
}