package com.littlejoyindia.littlejoyindia.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerFragment extends AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "DatePickerFragment";
    public int type = 0;
    public String currentDate;
    final Calendar c = Calendar.getInstance();

    public static DatePickerFragment newInstance(int type , String currentDate) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putString("currentDate", currentDate);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Set the current date as the default date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        type = getArguments().getInt("type");
        currentDate = getArguments().getString("currentDate");

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), DatePickerFragment.this, year, month, day);
        Log.e("type", ""+type+"\n"+currentDate);
        if (type == 3){

        } else {
            if (type == 0) {
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            } else {

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date = null;
                try {
                    date = sdf.parse(currentDate);
                    dialog.getDatePicker().setMinDate(date.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }

        return dialog;
    }

    // called when a date has been selected
    public void onDateSet(DatePicker view, int year, int month, int day) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String selectedDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(c.getTime());

        Log.d(TAG, "onDateSet: " + selectedDate);
        // send date back to the target fragment
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                new Intent().putExtra("selectedDate", selectedDate).putExtra("type", type)
        );
    }
}
