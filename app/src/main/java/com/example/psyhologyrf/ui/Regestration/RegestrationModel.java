package com.example.psyhologyrf.ui.Regestration;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegestrationModel extends ViewModel {

    private  MutableLiveData<String> mText;

    public void registratAuth() {
        mText = new MutableLiveData<>();
        mText.setValue("This 4rewds");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
