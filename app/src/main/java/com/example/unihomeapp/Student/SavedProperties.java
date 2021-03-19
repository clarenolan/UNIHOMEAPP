package com.example.unihomeapp.Student;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SavedProperties extends Fragment {

    private List<String> mySaved;

    FirebaseUser firebaseUser;

    private RecyclerView recyclerView_saved;
    private SavedAdapter savedAdapter;
    private List<PropertyData> savedPropertyList;
    private DatabaseReference databaseReference;

    public SavedProperties(){

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saved_properties, container, false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView_saved = view.findViewById(R.id.recyclerViewSaved);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 1);
        recyclerView_saved.setLayoutManager(gridLayoutManager2);
        savedPropertyList = new ArrayList<>();
        savedAdapter = new SavedAdapter(getContext(), savedPropertyList);
        recyclerView_saved.setAdapter(savedAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Properties");

        mySaved();

        return view;
    }


    private void mySaved() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Saved").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mySaved = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mySaved.add(snapshot.getKey());
                }
                System.out.println(mySaved);
                readSaved();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void readSaved() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                savedPropertyList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PropertyData post = snapshot.getValue(PropertyData.class);
                    String postKey = snapshot.getKey();
                    post.setKey(snapshot.getKey());

                    for (String id : mySaved) {
                        if (postKey.equals(id)) {
                            savedPropertyList.add(post);
                        }
                    }
                }
                savedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
