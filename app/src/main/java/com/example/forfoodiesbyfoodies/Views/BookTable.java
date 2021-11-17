package com.example.forfoodiesbyfoodies.Views;

import androidx.annotation.NonNull;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import com.example.forfoodiesbyfoodies.R;
import android.widget.Toast;



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

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast toast =  Toast.makeText(getApplicationContext(),
                        "Selected Date: "  +dayOfMonth+ "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}

//    d MMM yyyy