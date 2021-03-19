package com.example.unihomeapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.unihomeapp.Landlord.LandlordLogin;
import com.example.unihomeapp.Student.StudentLogin;
import com.example.unihomeapp.Student.StudentSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class UserWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);



    }

    public void startLandlord(View view) {
        Intent intToProfile = new Intent(UserWelcome.this, LandlordLogin.class);
        startActivity(intToProfile);
    }

    public void startStudent(View view) {
        Intent intToProfile = new Intent(UserWelcome.this, StudentLogin.class);
        startActivity(intToProfile);
    }
}