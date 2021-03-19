package com.example.unihomeapp;

import androidx.lifecycle.MutableLiveData;

import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.Models.ReviewModel;

public class PropertyDetailViewModel {

    private MutableLiveData<ReviewModel>mutableLiveDataReview;
    private MutableLiveData<PropertyData>mutableLiveDataProperty;

    public void setReviewModel(ReviewModel reviewModel){
        if (mutableLiveDataReview != null)
            mutableLiveDataReview.setValue(reviewModel);
    }

    public MutableLiveData<ReviewModel> getMutableLiveData() {
            return mutableLiveDataReview;
    }

//    public void setMutableLiveData(MutableLiveData<PropertyData> mutableLiveDataReview) {
//        this.mutableLiveDataReview = mutableLiveDataReview;
//    }

    PropertyDetailViewModel(){ mutableLiveDataReview = new MutableLiveData<>(); }


    public void setPropertyData(PropertyData propertyData) {
        if (mutableLiveDataProperty != null)
            mutableLiveDataProperty.setValue(propertyData);
    }
}
