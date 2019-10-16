package com.example.fasheonic.ui.myorderbids;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyOrderBidsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyOrderBidsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My order bids fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}