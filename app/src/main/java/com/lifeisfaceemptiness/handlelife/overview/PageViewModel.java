package com.lifeisfaceemptiness.handlelife.overview;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {
    private String TAG=".PageViewModel";
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public int getIndex() { return mIndex.getValue(); }
}