package com.duvarax.gamerasksapp.ui.Preguntas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PreguntasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PreguntasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}