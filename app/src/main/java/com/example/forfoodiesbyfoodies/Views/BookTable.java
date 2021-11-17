package com.example.forfoodiesbyfoodies.Views;

import androidx.annotation.NonNull;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import com.example.forfoodiesbyfoodies.R;

public class BookTable extends AppCompatActivity {

    //Define the type of CalendarView
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);

        //Getting calendarView using findViewByID
        calendar = (CalendarView) findViewById(R.id.calendar);

        //Add listener in calendar with a method to get the value of days,month and years
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@androidx.annotation.NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String Date = dayOfMonth + "-" + (month + 1) + "-" + year;
            }
        });
    }

}