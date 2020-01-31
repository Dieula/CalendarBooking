package sylvie.dieula.calendar_sysy.Calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import sylvie.dieula.calendar_sysy.R;


public class DateHeureActivity extends AppCompatActivity {

    String title;
    private CalendarAdapter mCalendarAdapter;
    private RecyclerView recyclerView;
    private TextView tvSelectedDate,tvArrive,tvDepart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_heure);



        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        tvArrive = (TextView)findViewById(R.id.tvArrive);
        tvDepart = (TextView)findViewById(R.id.tvDepart);

        initListener();
    }

    private void initListener() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       /* recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));*/
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        mCalendarAdapter = new CalendarAdapter(this, new DatePickerController() {

            @SuppressLint("StringFormatInvalid")
            @Override
            public void onDayOfMonthSelected(Day day) {
                tvArrive.setText(String.format(getString(R.string.single_date), day.getYear(),
                        day.getMonth(), day.getDay()));

            }

            @SuppressLint("StringFormatInvalid")
            @Override
            public void onDateRangeSelected(Day firstDay, Day lastDay, int spanDays) {
               tvArrive.setText("Arrive: "+ String.format(getString(R.string.depart), firstDay.getYear(),
                        firstDay.getMonth(), firstDay.getDay()));

                tvDepart.setText("Depart: "+ String.format(getString(R.string.depart),lastDay.getYear(),
                        lastDay.getMonth(), lastDay.getDay()));
            }

            @Override
            public void onDateInllegal() {

            }
        });
        recyclerView.setAdapter(mCalendarAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
