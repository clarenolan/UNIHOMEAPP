package com.example.unihomeapp.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.unihomeapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PropertyInfo extends AppCompatActivity {

    TextView propertyDescription, propertyName, propertyAddress, rentPrice;
    ImageView propertyImage;
    String key="";
    String imageUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_info);

        propertyName = (TextView) findViewById(R.id.txtPropertyName);
        propertyDescription = (TextView) findViewById(R.id.txtDescription);
        propertyAddress = (TextView) findViewById(R.id.txtAddress);
        rentPrice = (TextView) findViewById(R.id.txtPrice);
        propertyImage = (ImageView) findViewById(R.id.ivImage);


        //Load property details into activity
        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null) {

            propertyName.setText(mBundle.getString("PropertyName"));
            propertyDescription.setText(mBundle.getString("Description"));
            propertyAddress.setText(mBundle.getString("PropertyAddress"));
            rentPrice.setText(mBundle.getString("Price"));
            key = mBundle.getString("keyValue");
            imageUrl = mBundle.getString("Image");

            //  propertyImage.setImageResource(mBundle.getInt("Image"));


            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(propertyImage);
        }

    }

}