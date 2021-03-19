package com.example.unihomeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.unihomeapp.Landlord.LandlordLogin;
import com.example.unihomeapp.Landlord.MainActivity;
import com.example.unihomeapp.Landlord.PropertyDetail;
import com.example.unihomeapp.Landlord.UploadProperty;
import com.example.unihomeapp.Landlord.UserProfile;
import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.Models.UserData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;
import java.util.Map;

public class EditorActivity extends AppCompatActivity {

    private RecyclerView recyclerView, recyclerInactive;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

//        recyclerView = findViewById(R.id.list);
//        //recyclerInactive = findViewById(R.id.inactiveList);
//        mAuth = FirebaseAuth.getInstance();
//
//        //Active properties recycler view
//        linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setHasFixedSize(true);
//
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Properties");
//        mDatabase.child("id/active").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                for (DataSnapshot movieSnapshot : snapshot.getChildren()) {
//                    PropertyData movie = snapshot.getValue(PropertyData.class);
//                    if (movie.getActive().equals("yes")) {
//
//                        fetch();
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(EditorActivity.this, "Read Failed", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//
//
//
//
//
//        //Inactive properties recyclerview
////        linearLayoutManager = new LinearLayoutManager(this);
////        recyclerInactive.setLayoutManager(linearLayoutManager);
////        recyclerInactive.setHasFixedSize(true);
////        fetchInactive();
//
////            mDatabase = FirebaseDatabase.getInstance().getReference().child("Property");
////            mAuth = FirebaseAuth.getInstance();
////            mAuthListener = new FirebaseAuth.AuthStateListener() {
////                @Override
////                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
////                    if (mAuth.getCurrentUser() == null) {
////                        Intent loginIntent = new Intent(EditorActivity.this, LandlordLogin.class);
////                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                        startActivity(loginIntent);
////                    }
////                }
////            };
//
//    }
//
////    private void fetchInactive() {
////
////    }
//
//
//
//
//
//
//
//
//    private void fetch() {
//        Query query = FirebaseDatabase.getInstance()
//                .getReference()
//                .child("Properties").orderByChild("uid").equalTo(mAuth.getCurrentUser().getUid());
//
//
//        FirebaseRecyclerOptions<PropertyData> options =
//                new FirebaseRecyclerOptions.Builder<PropertyData>()
//                        .setQuery(query, new SnapshotParser<PropertyData>() {
//                            @NonNull
//                            @Override
//                            public PropertyData parseSnapshot(@NonNull DataSnapshot snapshot) {
//                                return new PropertyData(snapshot.child("name").getValue().toString(),
//                                        snapshot.child("county").getValue().toString(),
//                                        snapshot.child("address").getValue().toString(),
//                                        snapshot.child("price").getValue().toString(),
//                                        snapshot.child("imageUrl").getValue().toString(),
//                                        snapshot.child("uid").getValue().toString(),
//                                        snapshot.child("wifi").getValue().toString(),
//                                        snapshot.child("bins").getValue().toString(),
//                                        snapshot.child("electricity").getValue().toString(),
//                                        snapshot.child("heating").getValue().toString(),
//                                        snapshot.child("washing").getValue().toString(),
//                                        snapshot.child("dryer").getValue().toString(),
//                                        snapshot.child("parking").getValue().toString(),
//                                        snapshot.child("dish").getValue().toString(),
//                                        snapshot.child("tv").getValue().toString(),
//                                        snapshot.child("kitchen").getValue().toString(),
//                                        snapshot.child("beds").getValue().toString(),
//                                        snapshot.child("availableBeds").getValue().toString(),
//                                        snapshot.child("bathrooms").getValue().toString(),
//                                        snapshot.child("period").getValue().toString(),
//                                        snapshot.child("active").getValue().toString());
//                            }
//                        })
//                        .build();
//
//
//        adapter = new FirebaseRecyclerAdapter<PropertyData, ViewHolder>(options) {
//            @Override
//            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.recycler_row_item, parent, false);
//
//                return new ViewHolder(view);
//            }
//
//
//            @Override
//            protected void onBindViewHolder(ViewHolder holder, final int position, PropertyData model) {
//                //Assigning values to textviews on the card view
//                holder.setTxtTitle(model.getPropertyName());
//                holder.setTxtAddress(model.getPropertyAddress());
//                holder.setTxtPrice(model.getRentPrice());
//                holder.setImage(model.getPropertyImage(), getApplicationContext());
//
//                final String property_key = getRef(position).getKey().toString();
//
//                holder.cardView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //Start property detail activity, passing property id
//                        Intent singleActivity = new Intent(EditorActivity.this, PropertyDetail.class);
//                        singleActivity.putExtra("PropertyID", property_key);
//                        startActivity(singleActivity);
//                    }
//                });
//            }
//
//        };
//        recyclerView.setAdapter(adapter);
//    }
//
//
//
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public CardView cardView;
//        public TextView mName, mAddress, mPrice;
//        public ImageView imageView;
//
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            cardView = itemView.findViewById(R.id.myCardView);
//            imageView = itemView.findViewById(R.id.ivImage);
//            mName = itemView.findViewById(R.id.tvName);
//            mAddress = itemView.findViewById(R.id.tvDescription);
//            mPrice = itemView.findViewById(R.id.tvPrice);
//
//        }
//
//        public void setTxtTitle(String string) {
//            mName.setText(string);
//        }
//
//
//        public void setTxtAddress(String string) {
//            mAddress.setText(string);
//        }
//
//
//        public void setImage(String propertyImage, Context context) {
//            Glide.with(context)
//                    .load(propertyImage).into(imageView);
//        }
//
//
//
//        public void setTxtPrice(String rentPrice) {
//            mPrice.setText("€" + rentPrice);
//        }
//    }
//    //Start Upload Property Activity
//    public void btn_UploadActivity(View view) {
//        startActivity(new Intent(this, UploadProperty.class));
//    }
//
//
    }

}