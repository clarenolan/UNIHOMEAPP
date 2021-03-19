package com.example.unihomeapp.Landlord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.unihomeapp.Models.EnquiryModel;
import com.example.unihomeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EnquiryDetail extends AppCompatActivity {

    private EditText etMessage;
    private TextView etEmail, etSubject, sName, sEmail, sNumber;
    private DatabaseReference mDatabase;
    String property_key = null;
    private DatabaseReference mUser;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_detail);

        etEmail = findViewById(R.id.etEmail2);
        etSubject = findViewById(R.id.etSubject2);
        etMessage = findViewById(R.id.etMessage2);
        sName = findViewById(R.id.tvSName2);
        sEmail = findViewById(R.id.tvSEmail2);
        sNumber = findViewById(R.id.tvSNumber2);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Enquiries");
        property_key = getIntent().getExtras().getString("PropertyID");
        mAuth = FirebaseAuth.getInstance();

        mDatabase.child(property_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //EnquiryModel enquiryData = dataSnapshot.getValue(EnquiryModel.class);
                    //enquiryData.setTiimestamp((String) dataSnapshot.child("tiimestamp").getValue());
                    String subject = (String) dataSnapshot.child("subject").getValue();
                    String Message = (String) dataSnapshot.child("message").getValue();
                    String name = (String) dataSnapshot.child("sName").getValue();
                    String email = (String) dataSnapshot.child("sEmail").getValue();
                    String phone = (String) dataSnapshot.child("sPhone").getValue();

                    etSubject.setText(subject);
                    etMessage.setText(Message);
                    sName.setText(name);
                    sEmail.setText(email);
                    sNumber.setText(phone);



            }


            @Override
            public void onCancelled(@NonNull DatabaseError error){
                }
            });

}


}