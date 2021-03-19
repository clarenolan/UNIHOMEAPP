package com.example.unihomeapp.Landlord;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unihomeapp.EditorActivity;
import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 *   adapted from ARSL TECH
 *   youtube | https://www.youtube.com/watch?v=t5Bd4mD0KgQ&t=418s
 */

public class UploadProperty extends AppCompatActivity {

    TextView textView1;
    ImageView propertyImage;
    Uri uri;
    EditText txt_name, txt_county, txt_address, txt_price;
    String imageUrl;
    String d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;
    CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
    Spinner spTotalBeds, spAvBeds, spBathrooms, spPeriod, spActive;


    private StorageReference storage;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;

//    final FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference ref = database.getReference("Landlords");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_property);

        textView1 = findViewById(R.id.text);
//        getSupportActionBar().setTitle("                NEW PROPERTY     ");

        propertyImage = (ImageView) findViewById(R.id.ivPropImage);
        txt_name = (EditText) findViewById(R.id.txt_PropName);
        txt_county = (EditText) findViewById(R.id.txt_Description);
        txt_address = (EditText) findViewById(R.id.txt_Address);
        txt_price = (EditText) findViewById(R.id.text_price);


        storage = FirebaseStorage.getInstance().getReference();
        databaseRef = database.getInstance().getReference().child("Properties");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());

        c1 = findViewById(R.id.wifi);
        c2 = findViewById(R.id.bins);
        c3 = findViewById(R.id.electricity);
        c4 = findViewById(R.id.heating);
        c5 = findViewById(R.id.washing);
        c6 = findViewById(R.id.dryer);
        c7 = findViewById(R.id.parking);
        c8 = findViewById(R.id.dish);
        c9 = findViewById(R.id.tv);
        c10 = findViewById(R.id.kitchen);

        spTotalBeds = findViewById(R.id.spTotalBeds);
        spAvBeds = findViewById(R.id.spAvBeds);
        spBathrooms = findViewById(R.id.spBathrooms);
        spPeriod = findViewById(R.id.spPeriod);
        spActive = findViewById(R.id.spActive);


        //Setting options 1-12, total number of beds spinner
        List<String> categories = new ArrayList<>();

        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");
        categories.add("6");
        categories.add("7");
        categories.add("8");
        categories.add("9");
        categories.add("10");
        categories.add("11");
        categories.add("12");

        ArrayAdapter<String> dataAdapter1;
        dataAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTotalBeds.setAdapter(dataAdapter1);


        //Setting options 1-12, number of available beds spinner
        List<String> categories2 = new ArrayList<>();

        categories2.add("1");
        categories2.add("2");
        categories2.add("3");
        categories2.add("4");
        categories2.add("5");
        categories2.add("6");
        categories2.add("7");
        categories2.add("8");
        categories2.add("9");
        categories2.add("10");
        categories2.add("11");
        categories2.add("12");

        ArrayAdapter<String> dataAdapter2;
        dataAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAvBeds.setAdapter(dataAdapter2);



        //Setting options 1-12, number of bathrooms spinner
        List<String> categories3 = new ArrayList<>();

        categories3.add("1");
        categories3.add("2");
        categories3.add("3");
        categories3.add("4");
        categories3.add("5");
        categories3.add("6");
        categories3.add("7");
        categories3.add("8");
        categories3.add("9");
        categories3.add("10");
        categories3.add("11");
        categories3.add("12");

        ArrayAdapter<String> dataAdapter3;
        dataAdapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBathrooms.setAdapter(dataAdapter3);



        //Setting options 1-12, rental period spinner
        List<String> categories4 = new ArrayList<>();

        categories4.add("Academic Year");
        categories4.add("Semester 1");
        categories4.add("Semester 2");
        categories4.add("Summer Period");
        categories4.add("Full Year");

        ArrayAdapter<String> dataAdapter4;
        dataAdapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories4);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPeriod.setAdapter(dataAdapter4);

        //Setting options yes/no
        List<String> activelist = new ArrayList<>();

        activelist.add("Yes");
        activelist.add("No");


        ArrayAdapter<String> dataAdapter5;
        dataAdapter5 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, activelist);
        dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spActive.setAdapter(dataAdapter5);

    }


    //Select Image from emulator gallery
    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, 1);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            uri = data.getData();
            propertyImage.setImageURI(uri);

        } else Toast.makeText(this, "Pick an image", Toast.LENGTH_SHORT).show();

    }


    //Upload property button calls upload image method
    public void btnUploadProperty(View view) {
        uploadImage();
    }


    //Upload Image to firebase
    public void uploadImage() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("imageUrl").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(taskSnapshot -> {

            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
            while (!uriTask.isComplete()) ;
            Uri urlImage = uriTask.getResult();
            imageUrl = urlImage.toString();
            //Call upload property method
            uploadProperty();
        });

    }


    //Upload property to firebase
    public void uploadProperty() {
        //Progress Dialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Property Uploading...");
        progressDialog.show();

        //Setting variables from amenity checkboxes
        if (c1.isChecked()) {
            d1 = "Included";
        }else {
            d1 = "Not Included";
        }

        if (c2.isChecked()) {
            d2 = "Included";
        }else {
            d2 = "Not Included";
        }

        if (c3.isChecked()) {
            d3 = "Included";
        } else {
            d3 = "Not Included";
        }

        if (c4.isChecked()) {
            d4 = "Included";
        } else {
            d4 = "Not Included";
        }

        if (c5.isChecked()) {
            d5 = "Included";
        }else {
            d5 = "Not Included";
        }

        if (c6.isChecked()) {
            d6 = "Included";
        } else {
            d6 = "Not Included";
        }

        if (c7.isChecked()) {
            d7 = "Included";
        } else {
            d7 = "Not Included";
        }

        if (c8.isChecked()) {
            d8 = "Included";
        } else {
            d8 = "Not Included";
        }

        if (c9.isChecked()) {
            d9 = "Included";
        }else {
            d9 = "Not Included";
        }

        if (c10.isChecked()) {
            d10 = "Included";
        } else {
            d10 = "Not Included";
        }



            //Old Working Method
            //New property data model
//            PropertyData propertyData = new PropertyData(
//                    txt_name.getText().toString(),
//                    txt_county.getText().toString(),
//                    txt_address.getText().toString(),
//                    txt_price.getText().toString(),
//                    imageUrl,
//                    d1,
//                    d2,
//                    d3,
//                    d4,
//                    d5,
//                    d6,
//                    d7,
//                    d8,
//                    d9,
//                    d10,
//                    spTotalBeds.getSelectedItem().toString(),
//                    spAvBeds.getSelectedItem().toString(),
//                    spBathrooms.getSelectedItem().toString(),
//                    spPeriod.getSelectedItem().toString()
//            );

        //Setting variables from selected spinner items
        final String name = txt_name.getText().toString().trim();
        final String county = txt_county.getText().toString().trim();
        final String address = txt_address.getText().toString().trim();
        final String price = txt_price.getText().toString().trim();
        final String beds = spTotalBeds.getSelectedItem().toString().trim();
        final String availableBeds = spAvBeds.getSelectedItem().toString().trim();
        final String bathrooms = spBathrooms.getSelectedItem().toString().trim();
        final String period = spPeriod.getSelectedItem().toString().trim();
        final String active = spActive.getSelectedItem().toString().trim();
        final String uid = mCurrentUser.getUid();

        PropertyData propertyData = new PropertyData(
                name,
                county,
                address,
                price,
                imageUrl,
                d1,
                d2,
                d3,
                d4,
                d5,
                d6,
                d7,
                d8,
                d9,
                d10,
                beds,
                availableBeds,
                bathrooms,
                period,
                active,
                uid

        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


        FirebaseDatabase.getInstance().getReference("Properties").child(myCurrentDateTime).setValue(propertyData).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(UploadProperty.this, "Property Uploaded", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }


            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadProperty.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }


//        final DatabaseReference newPost = databaseRef.push();
//        mDatabaseUsers.addValueEventListener(new ValueEventListener() {
//         @Override
//         public void onDataChange(DataSnapshot dataSnapshot) {
//             newPost.child("name").setValue(name);
//             newPost.child("county").setValue(county);
//             newPost.child("address").setValue(address);
//             newPost.child("price").setValue(price);
//             newPost.child("imageUrl").setValue(imageUrl);
//             newPost.child("wifi").setValue(d1);
//             newPost.child("bins").setValue(d2);
//             newPost.child("electricity").setValue(d3);
//             newPost.child("heating").setValue(d4);
//             newPost.child("washing").setValue(d5);
//             newPost.child("dryer").setValue(d6);
//             newPost.child("parking").setValue(d7);
//             newPost.child("dish").setValue(d8);
//             newPost.child("tv").setValue(d9);
//             newPost.child("kitchen").setValue(d10);
//             newPost.child("beds").setValue(beds);
//             newPost.child("availableBeds").setValue(availableBeds);
//             newPost.child("bathrooms").setValue(bathrooms);
//             newPost.child("period").setValue(period);
//             newPost.child("active").setValue(active);
//             newPost.child("uid").setValue(mCurrentUser.getUid())
//                     .addOnCompleteListener(new OnCompleteListener<Void>() {
//                         @Override
//                         public void onComplete(@NonNull Task<Void> task) {
//
//                             if (task.isSuccessful()) {
//                                 Toast.makeText(UploadProperty.this, "Property Uploaded", Toast.LENGTH_SHORT).show();
//                                 Intent intent = new Intent(UploadProperty.this, MainActivity.class);
//                                 startActivity(intent);
//                             }
//                         }
//                     });
//         }
//
//             @Override
//             public void onCancelled(DatabaseError databaseError) {
//
//             }
//
//         });
    }








            //Get current date and time
            //String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());



        //Working Method
//            String propertyName = txt_name.getText().toString();
            // Upload property data to database under property name
//            FirebaseDatabase.getInstance().getReference("Properties").child(propertyName).setValue(propertyData).addOnCompleteListener(new OnCompleteListener<Void>() {
//
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//
//                    if (task.isSuccessful()) {
//
//                        Toast.makeText(UploadProperty.this, "Property Uploaded", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                        finish();
//                    }
//
//
//                }
//
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(UploadProperty.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//                }
//
//
//           });








