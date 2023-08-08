package com.littlejoyindia.littlejoyindia.ui.dashboard.timeslot;

import com.littlejoyindia.littlejoyindia.data.DataManager;
import com.littlejoyindia.littlejoyindia.ui.base.BaseViewModel;
import com.littlejoyindia.littlejoyindia.utils.rx.SchedulerProvider;

public class SelectTimeViewModel extends BaseViewModel<SelectTimeNavigator> {

    public SelectTimeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onClickNext(){
        getNavigator().clickOnNext();
    }

    public void setCurrentTimeSlot(int slot){
        getNavigator().setDefaultsTimeUnit(slot);
    }

    public void onClickSelectDate(){
        getNavigator().clickOnDateSelect();
    }


}