package sylvie.dieula.calendar_sysy.Calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import sylvie.dieula.calendar_sysy.R;


public class MonthDayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    //    private final DateModel dateModel;
    private final Calendar mCalendar;
    private final Time today;
    protected boolean mHasToday = false;
    protected int mToday = -1;
    protected int mWeekStart = 1;
    protected int mNumDays = 7;
    protected int mNumCells;
    private int mDayOfWeekStart = 0;
    private int clickCount = 0;

    private final int itemWidth;
    private OnDayClickListener mOnDayClickListener;
    private List<Day> mDayList;

    public MonthDayAdapter(Context context, List<Day> list, int year, int month) {
        this.mContext = context;
        mCalendar = Calendar.getInstance();
        today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        mDayList = list;
        mLayoutInflater = LayoutInflater.from(mContext);
        itemWidth = UtilsCalendar.getScreenWidth(mContext) / 7;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.layout_day_item, null, false);
        return new DayHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final DayHolder dayHolder = (DayHolder) holder;
        final Day day = mDayList.get(position);

        ViewGroup.LayoutParams layoutParams = dayHolder.tvDay.getLayoutParams();
        layoutParams.height = itemWidth;
        dayHolder.tvDay.setLayoutParams(layoutParams);

        if (CalendarUtils.isToday(day)) {
            dayHolder.tvDay.setBackgroundResource(R.drawable.circle_border);
        } else {
            dayHolder.tvDay.setBackgroundResource(0);
        }
        if (CalendarUtils.prevDay(day)) {
            dayHolder.tvDay.setTextColor(mContext.getResources().getColor(R.color.transparent50White));
        } else {
            dayHolder.tvDay.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        dayHolder.tvDay.setText(day.getDay() == 0 ? "" : String.valueOf(day.getDay()));

        if (day.isSingleChosen()) {
            dayHolder.tvDay.setTextColor(mContext.getResources().getColor(R.color.rating_text));
            dayHolder.tvDay.setBackgroundResource(R.drawable.circle_day);
        }
        if (day.isFirst()) {
            dayHolder.tvDay.setTextColor(mContext.getResources().getColor(R.color.rating_text));
            dayHolder.tvDay.setBackgroundResource(R.drawable.left_half_circle_day);
        }
        if (day.isLast()) {
            dayHolder.tvDay.setTextColor(mContext.getResources().getColor(R.color.rating_text));
            dayHolder.tvDay.setBackgroundResource(R.drawable.right_half_circle_day);
        }
        if (day.isOrder()) {
            dayHolder.tvDay.setTextColor(mContext.getResources().getColor(R.color.rating_text));
            dayHolder.tvDay.setBackgroundResource(R.drawable.btn_inc_dec);
        }
        dayHolder.tvDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDayClick(position, day);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDayList.size();
    }

    class DayHolder extends RecyclerView.ViewHolder {
        TextView tvDay;

        public DayHolder(View itemView) {
            super(itemView);
            tvDay = (TextView) itemView.findViewById(R.id.tv_day);
        }
    }

    private int calculateNumRows() {
        int offset = findDayOffset();
        int dividend = (offset + mNumCells) / mNumDays;
        int remainder = (offset + mNumCells) % mNumDays;
        return (dividend + (remainder > 0 ? 1 : 0));
    }

    private int findDayOffset() {
        return (mDayOfWeekStart < mWeekStart ? (mDayOfWeekStart + mNumDays) : mDayOfWeekStart)
                - mWeekStart;
    }


    private String getMonthAndYearString() {
        int flags = DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_NO_MONTH_DAY;
//        mStringBuilder.setLength(0);
        long millis = mCalendar.getTimeInMillis();
        return DateUtils.formatDateRange(mContext, millis, millis, flags);
    }

    private void onDayClick(int position, Day day) {
        if (mOnDayClickListener != null && !CalendarUtils.prevDay(day)) {
            mOnDayClickListener.onDayClick(position, day);
        }
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        mOnDayClickListener = onDayClickListener;
    }

    public interface OnDayClickListener {
        void onDayClick(int position, Day day);
    }
}
