package com.example.unihomeapp.Reviews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.Models.ReviewModel;

import java.util.List;

public class ReviewViewModel  extends ViewModel {
    private MutableLiveData<List<ReviewModel>> mutableLiveDataPropertyList;

    public ReviewViewModel(){
        mutableLiveDataPropertyList = new MutableLiveData<>();
    }

    public MutableLiveData<List<ReviewModel>> getMutableLiveDataPropertyList(){
        return mutableLiveDataPropertyList;
    }

    public void setMutableLiveDataPropertyList(List<ReviewModel> reviewList)
    {
        mutableLiveDataPropertyList.setValue(reviewList);
    }

}
