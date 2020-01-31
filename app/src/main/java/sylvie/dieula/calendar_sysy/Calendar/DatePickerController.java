package sylvie.dieula.calendar_sysy.Calendar;

public interface DatePickerController {
    void onDayOfMonthSelected(Day day);

    void onDateRangeSelected(Day firstDay, Day lastDay, int spanDays);

    void onDateInllegal();
}
