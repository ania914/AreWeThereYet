package com.example.anna.arewethereyet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar now = Calendar.getInstance();
            Calendar inputDate = Calendar.getInstance();
            inputDate.set(year, month, dayOfMonth);
            if(now.after(inputDate)) {
                Toast toast = Toast.makeText(view.getContext(), "Wrong input date", Toast.LENGTH_LONG);
                toast.show();
            } else {
                long value = inputDate.getTimeInMillis();
                SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(MainActivity.DATE, value);
                editor.commit();
                long val = preferences.getLong(MainActivity.DATE, 0);
            }
    }
}
