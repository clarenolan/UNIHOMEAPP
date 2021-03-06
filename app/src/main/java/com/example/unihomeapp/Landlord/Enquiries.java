package com.example.unihomeapp.Landlord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.unihomeapp.Models.EnquiryModel;
import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Enquiries extends AppCompatActivity {

    TextView textView;
    private FirebaseAuth mAuth;
    RecyclerView mRecyclerView;
    List<EnquiryModel> myEnquiryList;
    private DatabaseReference databaseReference;
    EnquiryAdapter myAdapter2;
    private ValueEventListener eventListener;
    List<PropertyData> myPropertyList;
    String propertyKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiries);


        mAuth = FirebaseAuth.getInstance();

        myPropertyList = new ArrayList<>();

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerEnquiry);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Enquiries.this,1 );
        mRecyclerView.setLayoutManager(gridLayoutManager);

        myEnquiryList = new ArrayList<>();

        myAdapter2 = new EnquiryAdapter(Enquiries.this,myEnquiryList);
        mRecyclerView.setAdapter(myAdapter2);
        databaseReference = FirebaseDatabase.getInstance().getReference("Enquiries");


        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myPropertyList.clear();


                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    PropertyData propertyData = itemSnapshot.getValue(PropertyData.class);
                    propertyData.setKey(itemSnapshot.getKey());
                    propertyData.setName((String) itemSnapshot.child("name").getValue());
                    propertyData.setAddress((String) itemSnapshot.child("address").getValue());
                    propertyData.setPrice((String) itemSnapshot.child("price").getValue());
                    propertyData.setImageUrl((String) itemSnapshot.child("imageUrl").getValue());

                    String property_uid = (String) itemSnapshot.child("uid").getValue();

                    propertyKey = (itemSnapshot.getKey());

                    //(propertyData.getActive().equals("yes"))&&
                    if ((property_uid.equals(mAuth.getCurrentUser().getUid())) ) {
                        myPropertyList.add(propertyData);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        loadUI();


    }

    private void loadUI() {
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myEnquiryList.clear();

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    EnquiryModel enquiryData = itemSnapshot.getValue(EnquiryModel.class);
                    enquiryData.setKey(itemSnapshot.getKey());
                    enquiryData.setPropertyKey((String) itemSnapshot.child("propertyKey").getValue());
                    enquiryData.setTiimestamp((String) itemSnapshot.child("tiimestamp").getValue());
                    enquiryData.setSubject((String) itemSnapshot.child("subject").getValue());
                    enquiryData.setMessage((String) itemSnapshot.child("message").getValue());
                    enquiryData.setsName((String) itemSnapshot.child("sName").getValue());
                    enquiryData.setsEmail((String) itemSnapshot.child("sEmail").getValue());
                    enquiryData.setsPhone((String) itemSnapshot.child("sPhone").getValue());


                    String property_key = (String) itemSnapshot.child("propertyKey").getValue();

                    //(propertyData.getActive().equals("yes"))&&
                    if ((property_key.equals(propertyKey)) ) {
                        myEnquiryList.add(enquiryData);
                    }
                }

                myAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}