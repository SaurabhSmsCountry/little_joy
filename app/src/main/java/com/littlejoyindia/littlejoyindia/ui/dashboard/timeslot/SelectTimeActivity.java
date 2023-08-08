package com.littlejoyindia.littlejoyindia.ui.dashboard.timeslot;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import com.littlejoyindia.littlejoyindia.BR;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ViewModelProviderFactory;
import com.littlejoyindia.littlejoyindia.data.model.Address;
import com.littlejoyindia.littlejoyindia.databinding.ActivitySelectTimeBinding;
import com.littlejoyindia.littlejoyindia.ui.base.BaseActivity;
import com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderConfirm.ShoppingOrderConfirmationScreen;
import com.littlejoyindia.littlejoyindia.ui.dashboard.paymentselection.PaymentActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;



public class SelectTimeActivity extends BaseActivity<ActivitySelectTimeBinding, SelectTimeViewModel>
        implements SelectTimeNavigator , DatePickerDialog.OnDateSetListener {

    @Inject
    ViewModelProviderFactory factory;

    private ActivitySelectTimeBinding mBinding;
    private SelectTimeViewModel viewModel;
    ArrayList<String>  mTimingsList = new ArrayList<>();
    ArrayAdapter<String> adapterTime ;

    Address address = null;
    String userName =  null;
    String userMobile =  null, userEmail = null;
    Date currentDate = null;
    int currentTimeSlot = 0;
    int checkoutType = 0;
    int cartId = -1;
    boolean isCodAvailable = false;
    public static Intent newIntent(Context context) {
        return new Intent(context, SelectTimeActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_time;
    }

    @Override
    public SelectTimeViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SelectTimeViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);

        Calendar cal = Calendar.getInstance();
        currentDate = cal.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, yyyy");
        String today = formatter.format(currentDate);
        System.out.println("Today : " + today);



        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null){
            address = (Address) mBundle.getSerializable("address");
            userName = mBundle.getString("userName");
            userMobile = mBundle.getString("userMobile");
            userEmail = mBundle.getString("userEmail");
            checkoutType = mBundle.getInt("checkoutType");
            cartId = mBundle.getInt("cartId");
            isCodAvailable = mBundle.getBoolean("isCodAvailable");
        }


        mBinding = getViewDataBinding();
        setUpToolBar();

        mBinding.tvCurrentDayAndDate.setText(today);

        setDefaultsTimeUnit(currentTimeSlot);



        Calendar c = Calendar.getInstance();
        int dayOfTheMonth = c.get(Calendar.DATE);
        mTimingsList.addAll(getTimeDropdowns(dayOfTheMonth));



//
//        adapterTime =
//                new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, mTimingsList);
//        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        getViewDataBinding().spTimeSlot.setAdapter(adapterTime);



        mBinding.imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                cal.setTime(currentDate);
                cal.add(Calendar.DAY_OF_MONTH, 1); //Adds a day
                currentDate = cal.getTime();

                SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, yyyy");
                String today = formatter.format(currentDate);
                System.out.println("Today : " + today);

                //String dayLongName = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
                mBinding.tvCurrentDayAndDate.setText(today);
            }
        });

        mBinding.imgPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(currentDate);
                cal.add(Calendar.DAY_OF_MONTH, -1); //Goes to previous day
                currentDate = cal.getTime();
               // String dayLongName = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

                SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, yyyy");
                String today = formatter.format(currentDate);
                System.out.println("Today : " + today);

                mBinding.tvCurrentDayAndDate.setText(today);
            }
        });

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (DatePickerDialog.OnDateSetListener) (datePicker, year, month, dayOfMonth) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(year, month, dayOfMonth);
            String mDate = new SimpleDateFormat("EEE, MMM d, yyyy").format(calendar1.getTime());
            mBinding.tvCurrentDayAndDate.setText(mDate);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        mBinding.tvCurrentDayAndDate.setOnClickListener(view -> {
            datePickerDialog.show();
        });

    }

    @Override
    public void setDefaultsTimeUnit(int slotPosition) {

        currentTimeSlot = slotPosition;

        mBinding.tv1.setBackground(getResources().getDrawable(slotPosition == 1 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv2.setBackground(getResources().getDrawable(slotPosition == 2 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv3.setBackground(getResources().getDrawable(slotPosition == 3 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv4.setBackground(getResources().getDrawable(slotPosition == 4 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv5.setBackground(getResources().getDrawable(slotPosition == 5 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv6.setBackground(getResources().getDrawable(slotPosition == 6 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv7.setBackground(getResources().getDrawable(slotPosition == 7 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv8.setBackground(getResources().getDrawable(slotPosition == 8 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv9.setBackground(getResources().getDrawable(slotPosition == 9 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv10.setBackground(getResources().getDrawable(slotPosition == 10 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv11.setBackground(getResources().getDrawable(slotPosition == 11 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv12.setBackground(getResources().getDrawable(slotPosition == 12 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv13.setBackground(getResources().getDrawable(slotPosition == 13 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv14.setBackground(getResources().getDrawable(slotPosition == 14 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv15.setBackground(getResources().getDrawable(slotPosition == 15 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv16.setBackground(getResources().getDrawable(slotPosition == 16 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv17.setBackground(getResources().getDrawable(slotPosition == 17 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv18.setBackground(getResources().getDrawable(slotPosition == 18 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv19.setBackground(getResources().getDrawable(slotPosition == 19 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));
        mBinding.tv20.setBackground(getResources().getDrawable(slotPosition == 20 ? R.drawable.round_time_slot_item_active : R.drawable.round_time_slot_item_non_active));


        // setting text colour

        mBinding.tv1.setTextColor(getResources().getColor(slotPosition == 1 ? R.color.white : R.color.black_effective));
        mBinding.tv2.setTextColor(getResources().getColor(slotPosition == 2 ? R.color.white : R.color.black_effective));
        mBinding.tv3.setTextColor(getResources().getColor(slotPosition == 3 ? R.color.white : R.color.black_effective));
        mBinding.tv4.setTextColor(getResources().getColor(slotPosition == 4 ? R.color.white : R.color.black_effective));
        mBinding.tv5.setTextColor(getResources().getColor(slotPosition == 5 ? R.color.white : R.color.black_effective));
        mBinding.tv6.setTextColor(getResources().getColor(slotPosition == 6 ? R.color.white : R.color.black_effective));
        mBinding.tv7.setTextColor(getResources().getColor(slotPosition == 7 ? R.color.white : R.color.black_effective));
        mBinding.tv8.setTextColor(getResources().getColor(slotPosition == 8 ? R.color.white : R.color.black_effective));
        mBinding.tv9.setTextColor(getResources().getColor(slotPosition == 9 ? R.color.white : R.color.black_effective));
        mBinding.tv10.setTextColor(getResources().getColor(slotPosition == 10 ? R.color.white : R.color.black_effective));
        mBinding.tv11.setTextColor(getResources().getColor(slotPosition == 11 ? R.color.white : R.color.black_effective));
        mBinding.tv12.setTextColor(getResources().getColor(slotPosition == 12 ? R.color.white : R.color.black_effective));
        mBinding.tv13.setTextColor(getResources().getColor(slotPosition == 13 ? R.color.white : R.color.black_effective));
        mBinding.tv14.setTextColor(getResources().getColor(slotPosition == 14 ? R.color.white : R.color.black_effective));
        mBinding.tv15.setTextColor(getResources().getColor(slotPosition == 15 ? R.color.white : R.color.black_effective));
        mBinding.tv16.setTextColor(getResources().getColor(slotPosition == 16 ? R.color.white : R.color.black_effective));
        mBinding.tv17.setTextColor(getResources().getColor(slotPosition == 17 ? R.color.white : R.color.black_effective));
        mBinding.tv18.setTextColor(getResources().getColor(slotPosition == 18 ? R.color.white : R.color.black_effective));
        mBinding.tv19.setTextColor(getResources().getColor(slotPosition == 19 ? R.color.white : R.color.black_effective));
        mBinding.tv20.setTextColor(getResources().getColor(slotPosition == 20 ? R.color.white : R.color.black_effective));


    }

    private ArrayList<String> getTimeDropdowns(int selectedDate) {

        ArrayList<String>  mTimings = new ArrayList<>();
        Calendar rightNow = Calendar.getInstance();
        int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY); // return the hour in 24 hrs format (ranging from 0-23)

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int dayOfTheMonth = c.get(Calendar.DATE);
        mTimingsList.clear();
        if(selectedDate > dayOfTheMonth){

            mTimings.add("09:00 AM - 09:30 AM"); mTimings.add("09:30 AM - 10:00 AM");
            mTimings.add("10:00 AM - 10:30 AM"); mTimings.add("10:30 AM - 11:00 AM");
            mTimings.add("11:00 AM - 11:30 AM"); mTimings.add("11:30 AM - 12:00 PM");

            mTimings.add("12:00 PM - 12:30 PM"); mTimings.add("12:30 PM - 01:00 PM");
            mTimings.add("01:00 PM - 01:30 PM"); mTimings.add("01:30 PM - 02:00 PM");
            mTimings.add("02:00 PM - 02:30 PM"); mTimings.add("02:30 PM - 03:00 PM");
            mTimings.add("03:00 PM - 03:30 PM"); mTimings.add("03:30 PM - 04:00 PM");


            mTimings.add("04:00 PM - 04:30 PM"); mTimings.add("04:30 PM - 05:00 PM");
            mTimings.add("05:00 PM - 05:30 PM"); mTimings.add("05:30 PM - 06:00 PM");
            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");

        } else if(currentHourIn24Format <= 6 ){

            mTimings.add("09:00 AM - 09:30 AM"); mTimings.add("09:30 AM - 10:00 AM");
            mTimings.add("10:00 AM - 10:30 AM"); mTimings.add("10:30 AM - 11:00 AM");
            mTimings.add("11:00 AM - 11:30 AM"); mTimings.add("11:30 AM - 12:00 PM");
            mTimings.add("12:00 PM - 12:30 PM"); mTimings.add("12:30 PM - 01:00 PM");
            mTimings.add("01:00 PM - 01:30 PM"); mTimings.add("01:30 PM - 02:00 PM");
            mTimings.add("02:00 PM - 02:30 PM"); mTimings.add("02:30 PM - 03:00 PM");
            mTimings.add("03:00 PM - 03:30 PM"); mTimings.add("03:30 PM - 04:00 PM");
            mTimings.add("04:00 PM - 04:30 PM"); mTimings.add("04:30 PM - 05:00 PM");
            mTimings.add("05:00 PM - 05:30 PM"); mTimings.add("05:30 PM - 06:00 PM");
            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");

        } else if(currentHourIn24Format <= 7 ){

            mTimings.add("10:00 AM - 10:30 AM"); mTimings.add("10:30 AM - 11:00 AM");
            mTimings.add("11:00 AM - 11:30 AM"); mTimings.add("11:30 AM - 12:00 PM");
            mTimings.add("12:00 PM - 12:30 PM"); mTimings.add("12:30 PM - 01:00 PM");
            mTimings.add("01:00 PM - 01:30 PM"); mTimings.add("01:30 PM - 02:00 PM");
            mTimings.add("02:00 PM - 02:30 PM"); mTimings.add("02:30 PM - 03:00 PM");
            mTimings.add("03:00 PM - 03:30 PM"); mTimings.add("03:30 PM - 04:00 PM");
            mTimings.add("04:00 PM - 04:30 PM"); mTimings.add("04:30 PM - 05:00 PM");
            mTimings.add("05:00 PM - 05:30 PM"); mTimings.add("05:30 PM - 06:00 PM");
            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");
        } else if(currentHourIn24Format == 8 ){

            mTimings.add("11:00 AM - 11:30 AM"); mTimings.add("11:30 AM - 12:00 PM");
            mTimings.add("12:00 PM - 12:30 PM"); mTimings.add("12:30 PM - 01:00 PM");
            mTimings.add("01:00 PM - 01:30 PM"); mTimings.add("01:30 PM - 02:00 PM");
            mTimings.add("02:00 PM - 02:30 PM"); mTimings.add("02:30 PM - 03:00 PM");
            mTimings.add("03:00 PM - 03:30 PM"); mTimings.add("03:30 PM - 04:00 PM");
            mTimings.add("04:00 PM - 04:30 PM"); mTimings.add("04:30 PM - 05:00 PM");
            mTimings.add("05:00 PM - 05:30 PM"); mTimings.add("05:30 PM - 06:00 PM");
            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");
        } else if(currentHourIn24Format == 9 ){

            mTimings.add("12:00 PM - 12:30 PM"); mTimings.add("12:30 PM - 01:00 PM");
            mTimings.add("01:00 PM - 01:30 PM"); mTimings.add("01:30 PM - 02:00 PM");
            mTimings.add("02:00 PM - 02:30 PM"); mTimings.add("02:30 PM - 03:00 PM");
            mTimings.add("03:00 PM - 03:30 PM"); mTimings.add("03:30 PM - 04:00 PM");
            mTimings.add("04:00 PM - 04:30 PM"); mTimings.add("04:30 PM - 05:00 PM");
            mTimings.add("05:00 PM - 05:30 PM"); mTimings.add("05:30 PM - 06:00 PM");
            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");
        } else if(currentHourIn24Format == 10 ){

            mTimings.add("01:00 PM - 01:30 PM"); mTimings.add("01:30 PM - 02:00 PM");
            mTimings.add("02:00 PM - 02:30 PM"); mTimings.add("02:30 PM - 03:00 PM");
            mTimings.add("03:00 PM - 03:30 PM"); mTimings.add("03:30 PM - 04:00 PM");
            mTimings.add("04:00 PM - 04:30 PM"); mTimings.add("04:30 PM - 05:00 PM");
            mTimings.add("05:00 PM - 05:30 PM"); mTimings.add("05:30 PM - 06:00 PM");
            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");
        } else if(currentHourIn24Format == 11 ){

            mTimings.add("02:00 PM - 02:30 PM"); mTimings.add("02:30 PM - 03:00 PM");
            mTimings.add("03:00 PM - 03:30 PM"); mTimings.add("03:30 PM - 04:00 PM");
            mTimings.add("04:00 PM - 04:30 PM"); mTimings.add("04:30 PM - 05:00 PM");
            mTimings.add("05:00 PM - 05:30 PM"); mTimings.add("05:30 PM - 06:00 PM");
            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");
        } else if(currentHourIn24Format == 12 ){

            mTimings.add("03:00 PM - 03:30 PM"); mTimings.add("03:30 PM - 04:00 PM");
            mTimings.add("04:00 PM - 04:30 PM"); mTimings.add("04:30 PM - 05:00 PM");
            mTimings.add("05:00 PM - 05:30 PM"); mTimings.add("05:30 PM - 06:00 PM");
            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");
        } else if(currentHourIn24Format == 13 ){

            mTimings.add("04:00 PM - 04:30 PM"); mTimings.add("04:30 PM - 05:00 PM");
            mTimings.add("05:00 PM - 05:30 PM"); mTimings.add("05:30 PM - 06:00 PM");
            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");
        } else if(currentHourIn24Format == 14 ){

            mTimings.add("05:00 PM - 05:30 PM"); mTimings.add("05:30 PM - 06:00 PM");
            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");
        } else if(currentHourIn24Format == 15 ){

            mTimings.add("06:00 PM - 06:30 PM"); mTimings.add("06:30 PM - 07:00 PM");
        } else {
            mTimings.add("NA");
        }


        return mTimings;
    }


    private void setUpToolBar() {

        setSupportActionBar(mBinding.toolbarLayout.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Select Date/Time");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        }
    }


    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void clickOnNext() {

        SimpleDateFormat formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatter = new SimpleDateFormat("YYYY-MM-dd");
        }
        String etDateString = formatter.format(currentDate);

        if (currentTimeSlot == 0){
            Toast.makeText(this,"Please Choose Time !",Toast.LENGTH_SHORT).show();
            return;
        }

        String spTimeString = getTimeString(currentTimeSlot);

        //salon at home
        Intent mIntent = ShoppingOrderConfirmationScreen.newIntent(this);
        mIntent.putExtra("address", address);
        mIntent.putExtra("userName", userName);
        mIntent.putExtra("userMobile", userMobile);
        mIntent.putExtra("checkoutType", checkoutType);
        mIntent.putExtra("cartId", cartId);
        mIntent.putExtra("isCodAvailable", isCodAvailable);
        mIntent.putExtra("dateOfService",etDateString);
        mIntent.putExtra("timeOfService",spTimeString);
        mIntent.putExtra("userEmail", userEmail);
        startActivity(mIntent);
    }

    private String getTimeString(int currentTimeSlot) {
        String time = "";
       switch (currentTimeSlot){
           case 1 : time =  mBinding.tv1.getText().toString();
            break;
           case 2 : time =  mBinding.tv2.getText().toString();
           break;
           case 3 : time =  mBinding.tv3.getText().toString();
           break;
           case 4 : time =  mBinding.tv4.getText().toString();
           break;
           case 5 : time =  mBinding.tv5.getText().toString();
           break;
           case 6 : time =  mBinding.tv6.getText().toString();
           break;
           case 7 : time =  mBinding.tv7.getText().toString();
           break;
           case 8 : time = mBinding.tv8.getText().toString();
           break;
           case 9 : time = mBinding.tv9.getText().toString();
           break;
           case 10 : time = mBinding.tv10.getText().toString();
           break;
           case 11 : time = mBinding.tv11.getText().toString();
           break;
           case 12 : time = mBinding.tv12.getText().toString();
           break;
           case 13 : time = mBinding.tv13.getText().toString();
           break;
           case 14 : time = mBinding.tv14.getText().toString();
           break;
           case 15 : time = mBinding.tv15.getText().toString();
           break;
           case 16 : time = mBinding.tv16.getText().toString();
           break;
           case 17 : time = mBinding.tv17.getText().toString();
           break;
           case 18 : time = mBinding.tv18.getText().toString();
           break;
           case 19 : time = mBinding.tv19.getText().toString();
           break;
           case 20 : time = mBinding.tv20.getText().toString();
           break;
       }
       return time;
    }

    @Override
    public void clickOnDateSelect() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        month = month+1;

        String monthString = "";
        String dayString = "";
        if(month<10)
        {
            monthString = "0"+month;
        }
        else
        {monthString = ""+month;}

        if(day<10)
        {
            dayString = "0"+day;
        } else  {dayString = ""+day;}


      //  mBinding.tvSelectDate.setText(""+year+"-"+monthString+"-"+dayString);

        mTimingsList.addAll(getTimeDropdowns(Integer.parseInt(dayString)));
        adapterTime.notifyDataSetChanged();



    }
}