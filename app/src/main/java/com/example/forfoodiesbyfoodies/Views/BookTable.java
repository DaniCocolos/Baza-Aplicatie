package com.example.forfoodiesbyfoodies.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.forfoodiesbyfoodies.R;
import com.squareup.picasso.Picasso;

import java.util.Date;


public class BookTable extends AppCompatActivity {

    //Define the type of CalendarView
    CalendarView calendar;
    Button btn_book_table;
    ImageView photo;
    Boolean isDateSelected = false;
    String reservation_date ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);

        //Getting calendarView and the button using findViewByID
        calendar = (CalendarView) findViewById(R.id.calendar);
        btn_book_table = (Button) findViewById(R.id.btn_book_table);
        photo = (ImageView) findViewById(R.id.bk_restaurant_image);

        Intent it = getIntent();
        String url_image = it.getStringExtra("url_image");



        Picasso.get().load(url_image).into(photo);
        //String new_date = String.valueOf(calendar.getDate());



        btn_book_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDateSelected) {
                    //Log.d("tag", reservation_date);
                    String opentable = it.getStringExtra("url_opentable");
                    opentable = opentable.replace("$data", reservation_date);
                   // Log.d("tag", opentable);
                    Uri uri = Uri.parse(opentable);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } else
                {
                    Toast.makeText(BookTable.this,   "Please select the date", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //Add listener in calendar with a method to get the value of days,month and years
        //TODO - change the position for the toast message on top of the "Book a table" button
        calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                reservation_date = year + "-" + (month + 1) + "-" + dayOfMonth;
                isDateSelected = true;

        });
    }

}


