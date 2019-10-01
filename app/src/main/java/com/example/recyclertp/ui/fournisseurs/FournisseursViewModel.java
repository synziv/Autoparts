package com.example.recyclertp.ui.fournisseurs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FournisseursViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FournisseursViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}