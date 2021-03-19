package com.example.unihomeapp.Landlord;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateProperty extends AppCompatActivity {

//    ImageView propertyImage;
//    Uri uri;
//    EditText txt_name, txt_description, txt_address, txt_price;
//    String imageUrl;
//    String key, oldImageUrl;
//    DatabaseReference databaseReference;
//    StorageReference storageReference;
//    String propertyname, propertyDescription, propertyAddress, propertyPrice;
//    String d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;
//    CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
//    Spinner spTotalBeds, spAvBeds, spBathrooms, spPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_property);

//        propertyImage = (ImageView) findViewById(R.id.ivPropImage);
//        txt_name = (EditText) findViewById(R.id.txt_PropName);
//        txt_description = (EditText) findViewById(R.id.txt_Description);
//        txt_address = (EditText) findViewById(R.id.txt_Address);
//        txt_price = (EditText) findViewById(R.id.text_price);
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//
//            Glide.with(UpdateProperty.this)
//                    .load(bundle.getString("oldimageUrl"))
//                    .into(propertyImage);
//            txt_name.setText(bundle.getString("propertyNameKey"));
//            txt_description.setText(bundle.getString("propertyDescriptionKey"));
//            txt_address.setText(bundle.getString("propertyAddressKey"));
//            txt_price.setText(bundle.getString("propertyPriceKey"));
//            key = bundle.getString("key");
//            oldImageUrl = bundle.getString("oldimageUrl");
//        }
//
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("Property").child(key);
//

    }
//
//    public void btnSelectImage(View view) {
//        Intent photoPicker = new Intent(Intent.ACTION_PICK);
//        photoPicker.setType("image/*");
//        startActivityForResult(photoPicker, 1);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//
//            uri = data.getData();
//            propertyImage.setImageURI(uri);
//
//        } else Toast.makeText(this, "You haven't picked image", Toast.LENGTH_SHORT).show();
//
//    }
//
//
//    public void btnUpdateProperty(View view) {
//
//        propertyname = txt_name.getText().toString().trim();
//        propertyDescription = txt_description.getText().toString().trim();
//        propertyAddress = txt_address.getText().toString().trim();
//        propertyPrice = txt_price.getText().toString();

//
//        if (c1.isChecked()) {
//            d1 = "Wifi";
//
//        } else {
//
//        }
//        if (c2.isChecked()) {
//
//            d2 = "Bins";
//
//
//        } else {
//
//        }
//        if (c3.isChecked()) {
//            d3 = "Electricity";
//
//
//        } else {
//
//        }
//        if (c4.isChecked()) {
//
//            d4 = "Heating";
//
//
//        } else {
//
//        }
//        if (c5.isChecked()) {
//
//            d5 = "Washing Machine";
//
//
//        } else {
//
//        }
//        if (c6.isChecked()) {
//            d6 = "Dryer";
//
//
//        } else {
//
//        }
//
//            if (c7.isChecked()) {
//                d7 = "Parking";
//
//
//            } else {
//
//            }
//            if (c8.isChecked()) {
//
//                d8 = "Dish Washer";
//
//            } else {
//
//            }
//            if (c9.isChecked()) {
//
//                d9 = "TV Room";
//
//            } else {
//
//            }
//            if (c10.isChecked()) {
//
//                d10 = "Communal Kitchen";
//
//            } else {
//
//            }


//            final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setMessage("Property Uploading....");
//            progressDialog.show();
//            storageReference = FirebaseStorage.getInstance()
//                    .getReference().child("PropertyImage").child(uri.getLastPathSegment());
//            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
//                    while (!uriTask.isComplete()) ;
//                    Uri urlImage = uriTask.getResult();
//                    imageUrl = urlImage.toString();
//                    uploadProperty();
//                    progressDialog.dismiss();
//                }
//
//
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    progressDialog.dismiss();
//                }
//            });
//
//
//        }
//
//
//        public void uploadProperty(){
//
//            PropertyData propertyData = new PropertyData(
//                    propertyname,
//                    propertyDescription,
//                    propertyAddress,
//                    propertyPrice,
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
//                    spPeriod.getSelectedItem().toString(),
//                    username);
//
//
//            databaseReference.setValue(propertyData).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    StorageReference storageReferenceNew = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageUrl);
//                    storageReferenceNew.delete();
//                    Toast.makeText(UpdateProperty.this, "Data Updated", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//        }

    }
