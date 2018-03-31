package com.example.anna.arewethereyet;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public final static String DATE = "DATE";
    long date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TextView txtRemained= (TextView) findViewById(R.id.txtViewRemainingTime);
        txtRemained.setText(R.string.default_time);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getFragmentManager(), "datePicker");
            }
        });

        SharedPreferences preferences = getBaseContext().getSharedPreferences(getString(R.string.shared_file), MODE_PRIVATE);
        date = preferences.getLong(MainActivity.DATE, 0);
        if(date > 0){
            setDate();
        }
        preferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                long inputDate = sharedPreferences.getLong(MainActivity.DATE,0);
                if(inputDate != date){
                    date = inputDate;
                    setDate();
                }
            }
        });


    }
    private void setDate(){
        long longResult = Calendar.getInstance().getTimeInMillis() - date;
        if(longResult < 0) return;
        long seconds = longResult / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        TextView txtRemained= (TextView) findViewById(R.id.txtViewRemainingTime);
        txtRemained.setText(days + " days " + hours + " hours " + minutes + " minutes");
    }
}
