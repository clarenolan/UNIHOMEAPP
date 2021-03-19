package com.example.unihomeapp.Landlord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;

import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoadProperties extends AppCompatActivity {

    List<PropertyData> myPropertyList;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_properties);

//        Query query = databaseReference.orderByChild("genre").equalTo("comedy");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                ArrayList<Movie> movies = new ArrayList<>();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    String lead = dataSnapshot1.child("lead").getValue(String.class);
//                    String genre = dataSnapshot1.child("genre").getValue(String.class);
//
//                    movie = new Movie(lead, genre);
//
//                    movies.add(movie);
//
//                }
//
//                filterResults(movies, "Jack Nicholson");
//
//            }
//
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//    });

}

//    public void filterResults(final List<Movie> list, final String genre) {
//        List<Movie> movies = new ArrayList<>();
//        movies = list.stream().filter(o -> o.getLead().equals(genre)).collect(Collectors.toList());
//        System.out.println(movies);
//
//        employees.forEach(movie -> System.out.println(movie.getFirstName()));
//    }

}