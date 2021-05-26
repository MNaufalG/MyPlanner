package com.example.myplanner.ui.recycleBin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecycleBinViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RecycleBinViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Recycle Bin Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}