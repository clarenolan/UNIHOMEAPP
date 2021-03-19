package com.example.unihomeapp;

import com.example.unihomeapp.Models.ReviewModel;

import java.util.List;

public interface IReviewCallbackListener {
    void onCommentLoadSuccess(List<ReviewModel> reviewModels);
    void onCommentLoadFailed(String message);
}
