package sylvie.dieula.calendar_sysy.Calendar;

import java.util.List;

public class MonthCalendar {
    private int month;
    private int year;
    private List<Day> days;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
