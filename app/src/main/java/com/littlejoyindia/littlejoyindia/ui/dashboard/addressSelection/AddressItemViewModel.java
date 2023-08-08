package com.littlejoyindia.littlejoyindia.ui.dashboard.addressSelection;

import androidx.databinding.ObservableField;

import com.littlejoyindia.littlejoyindia.data.model.Address;
import com.littlejoyindia.littlejoyindia.data.model.saloon.CartModel;


public class AddressItemViewModel {

    public Address serviceModel ;
    public ObservableField<String> userAddress = new ObservableField<>();

    public ModelListener modelListener ;

    public AddressItemViewModel(Address serviceModel, ModelListener listener) {
        this.serviceModel = serviceModel;
        this.modelListener = listener;

//        userAddress.set(serviceModel.getHouseNo() + "\n"+ serviceModel.getStreet()+","+serviceModel.getLandmark()
//        +","+serviceModel.getCity()+","+serviceModel.getState()+","+serviceModel.getPincode());


        userAddress.set(serviceModel.getCompleteAddress());

//        mDuration.set(serviceModel.getDuration() + " mins");
//        mSellingPrice.set("\u20b9 " + serviceModel.getPrice());

    }



    public interface ModelListener {


    }
}
