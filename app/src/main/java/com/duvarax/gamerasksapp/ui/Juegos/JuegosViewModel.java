package com.duvarax.gamerasksapp.ui.Juegos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JuegosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public JuegosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}