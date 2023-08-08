package com.littlejoyindia.littlejoyindia.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends AppCompatDialogFragment implements TimePickerDialog.OnTimeSetListener{

    public int type = 0;
    public String currentTime;

    public static TimePickerFragment newInstance(int type , String currentTime) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putString("currentTime", currentTime);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);




        type = getArguments().getInt("type");
        currentTime = getArguments().getString("currentTime");

        TimePickerDialog dialog =  new TimePickerDialog(getActivity(),this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

//        if (type == 0){
//            dialog.
//        } else {
//
//        }
//
//        dialog.setMinTime(10, 0, 0);

        //Create and return a new instance of TimePickerDialog
        return dialog;
    }

    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        //Do something with the user chosen time
        //Get reference of host activity (XML Layout File) TextView widget
        // send date back to the target fragment
        getTargetFragment().onActivityResult(
                getTargetRequestCode(),
                Activity.RESULT_OK,
                new Intent().putExtra("selectedTime", hourOfDay).
                        putExtra("selectedTimeMinutes", minute).
                        putExtra("type", type)
        );
    }
}