package com.example.forfoodiesbyfoodies.Views;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;


public class BookTable extends AppCompatActivity {

    //Define the type of CalendarView
    CalendarView calendar;
    Button btn_book_table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);

        //Getting calendarView and the button using findViewByID
        calendar = (CalendarView) findViewById(R.id.calendar);
        btn_book_table = (Button) findViewById(R.id.btn_book_table);

        //Add listener in calendar with a method to get the value of days,month and years
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            //TODO - change the position for the toast message on top of the "Book a table" button
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Selected Date: " + dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });
    }
}


