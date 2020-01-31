package sylvie.dieula.calendar_sysy.Calendar;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class UtilsCalendar {
    public static final int getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
                displaymetrics);
        return displaymetrics.widthPixels;
    }
}
