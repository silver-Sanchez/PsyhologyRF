package com.example.psyhologyrf.ui.gallery;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> myweb;

    public GalleryViewModel() {
        myweb = new MutableLiveData<>();
     //   myweb.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return myweb;
    }
}