package com.example.unihomeapp.Student;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.R;
import com.example.unihomeapp.UserWelcome;
import com.example.unihomeapp.Utils.BottomNavigationViewHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

public class StudentNav extends AppCompatActivity {

    private static final String TAG = "StudentHome";
    private static final int ACTIVITY_NUM = 0;

    CheckBox chbWifi, chbParking, chbSem1, chbSem2, chbAcademic, chbSummer;
    TextView textView;
    private FirebaseAuth mAuth;
    List<PropertyData> myPropertyList;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    EditText txtSearch;
    Button btnApply, btnClear;
    String ch1, ch2, ch3, ch4, ch5, ch6;
    Spinner spRental, spCounties, spBeds, spRent;
    private PropertyAdapter adapter;
    private RecyclerView recyclerView;
    private Context mContext = StudentNav.this;
    NestedScrollView filters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_nav);


        mAuth = FirebaseAuth.getInstance();
        chbWifi = findViewById(R.id.cbWifi2);
        chbParking = findViewById(R.id.cbParking2);
        spRental = findViewById(R.id.spRental);
        spCounties = findViewById(R.id.spCounty);
        textView = findViewById(R.id.text);
        txtSearch = findViewById(R.id.txtSearch2);
        btnApply = findViewById(R.id.btnApply2);
        btnClear = findViewById(R.id.btnClear2);
        filters = findViewById(R.id.collapsing);

        recyclerView = findViewById(R.id.recyclerView2);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(StudentNav.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager2);

        myPropertyList = new ArrayList<>();

        adapter = new PropertyAdapter(StudentNav.this, myPropertyList);
        recyclerView.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Properties");

        loadUI();

        //Setting options 1-12, rental period spinner
        List<String> counties = new ArrayList<>();

        counties.add("Select your county...");
        counties.add("Antrim");
        counties.add("Armagh");
        counties.add("Carlow");
        counties.add("Cavan");
        counties.add("Cork");
        counties.add("Derry");
        counties.add("Donegal");
        counties.add("Down");
        counties.add("Dublin");
        counties.add("Fermanagh");
        counties.add("Galway");
        counties.add("Kerry");
        counties.add("Kildare");
        counties.add("Kilkenny");
        counties.add("Laois");
        counties.add("Leitrim");
        counties.add("Longford");
        counties.add("Louth");
        counties.add("Mayo");
        counties.add("Meath");
        counties.add("Monaghan");
        counties.add("Offaly");
        counties.add("Roscommon");
        counties.add("Sligo");
        counties.add("Tipperary");
        counties.add("Tyrone");
        counties.add("Mayo");
        counties.add("Waterford");
        counties.add("Westmeath");
        counties.add("Wexford");
        counties.add("Wicklow");


        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(StudentNav.this, android.R.layout.simple_spinner_item, counties);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCounties.setAdapter(dataAdapter);

        //Setting options 1-12, rental period spinner
        List<String> categories4 = new ArrayList<>();

        categories4.add("Select Rental Period");
        categories4.add("Academic Year");
        categories4.add("Semester 1");
        categories4.add("Semester 2");
        categories4.add("Summer Period");

        ArrayAdapter<String> dataAdapter4;
        dataAdapter4 = new ArrayAdapter(StudentNav.this, android.R.layout.simple_spinner_item, categories4);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRental.setAdapter(dataAdapter4);


        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPropertyList.clear();
                fetch();
            }
        });


        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        //Load method to reset UI
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUI();
                spRental.setSelection(0);
                spCounties.setSelection(0);
            }
        });

        setupBottomNavigationView();

    }


    //Reset UI
    private void loadUI() {
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Clear selected values
                myPropertyList.clear();
                if (chbWifi.isChecked()) {
                    chbWifi.setChecked(false);
                }
                if (chbParking.isChecked()) {
                    chbParking.setChecked(false);
                }
                spRental.setSelection(0);
                spCounties.setSelection(0);
//                spRent.setSelection(0);
//                spBeds.setSelection(0);

                //Load new propertyData model
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    PropertyData propertyData = itemSnapshot.getValue(PropertyData.class);
                    propertyData.setKey(itemSnapshot.getKey());
                    propertyData.setName((String) itemSnapshot.child("name").getValue());
                    propertyData.setAddress((String) itemSnapshot.child("address").getValue());
                    propertyData.setPrice((String) itemSnapshot.child("price").getValue());
                    propertyData.setImageUrl((String) itemSnapshot.child("imageUrl").getValue());
                    //Load only active properties
                    if (propertyData.getActive().equals("Yes")) {
                        myPropertyList.add(propertyData);
                    }
                }
                adapter.notifyDataSetChanged();
                // progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //  progressDialog.dismiss();
            }
        });
    }

    public void signOutStudent(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intToWelcome = new Intent(StudentNav.this, UserWelcome.class);
        startActivity(intToWelcome);
    }

    private void fetch() {
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    PropertyData propertyData = itemSnapshot.getValue(PropertyData.class);
                    propertyData.setKey(itemSnapshot.getKey());
                    propertyData.setName((String) itemSnapshot.child("name").getValue());
                    propertyData.setAddress((String) itemSnapshot.child("address").getValue());
                    propertyData.setPrice((String) itemSnapshot.child("price").getValue());
                    propertyData.setImageUrl((String) itemSnapshot.child("imageUrl").getValue());

                    String property_uid = (String) itemSnapshot.child("uid").getValue();
                    String string = "Included";
                    String sem1 = "Semester 1";
                    String sem2 = "Semester 2";
                    String sem3 = "Academic Year";
                    String sem4 = "Summer Period";

                    //County Variables
                    String a = "Antrim";
                    String b = "Armagh";
                    String c = "Carlow";
                    String d = "Cavan";
                    String e = "Cork";
                    String f = "Derry";
                    String g = ("Donegal");
                    String h = ("Down");
                    String i = ("Dublin");
                    String j = ("Fermanagh");
                    String k = ("Galway");
                    String l = ("Kerry");
                    String m = ("Kildare");
                    String n = ("Kilkenny");
                    String o = ("Laois");
                    String p = ("Leitrim");
                    String q = ("Longford");
                    String r = ("Louth");
                    String s = ("Mayo");
                    String t = ("Meath");
                    String u = ("Monaghan");
                    String v = ("Offaly");
                    String w = ("Roscommon");
                    String x = ("Sligo");
                    String y = "Tipperary";
                    String z = ("Tyrone");
                    String a1 = ("Mayo");
                    String a2 = ("Waterford");
                    String a3 = ("Westmeath");
                    String a4 = ("Wexford");
                    String a5 = ("Wicklow");

                    //Setting variables from amenity checkboxes
                    if (chbWifi.isChecked()) {
                        ch1 = "yes";
                    } else {
                        ch1 = "no";
                    }
                    if (chbParking.isChecked()) {
                        ch2 = "yes";
                    } else {
                        ch2 = "no";
                    }

                    //Wifi = yes, Parking = no, County = Cork, Period = Sem1
                    if ((ch1.equals("yes")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem1) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);

                        //Wifi = yes, Parking = no, County = Cork, Period = Sem2
                    } else if ((ch1.equals("yes")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem2)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem2) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);

                        //Wifi = yes, Parking = no, County = Cork, Period = Academic year
                    } else if ((ch1.equals("yes")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem3)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);

                        //Wifi = yes, Parking = no, County = Cork, Period = Summer Period
                    } else if ((ch1.equals("yes")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem4)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("yes")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem1) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("yes")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem2)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem2) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("yes")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem3)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("yes")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem4)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("no")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("period").getValue()).equals(sem1) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("no")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("period").getValue()).equals(sem1) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("no")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem2)) && (itemSnapshot.child("period").getValue()).equals(sem2) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("no")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem2)) && (itemSnapshot.child("period").getValue()).equals(sem2) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("no")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem3)) && (itemSnapshot.child("period").getValue()).equals(sem3) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("no")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem3)) && (itemSnapshot.child("period").getValue()).equals(sem3) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("no")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem4)) && (itemSnapshot.child("period").getValue()).equals(sem4) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("no")) && (ch2.equals("no")) && (spRental.getSelectedItem().toString().equals(sem4)) && (itemSnapshot.child("period").getValue()).equals(sem4) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("no")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("period").getValue()).equals(sem1) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("no")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("period").getValue()).equals(sem1) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("no")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem2)) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem2) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("no")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem2)) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem2) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("no")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem3)) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("no")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem3)) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("no")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem4)) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("no")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem4)) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("yes")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem1) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("yes")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem1) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("yes")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem2)) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem2) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("yes")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem2)) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem2) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("yes")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem3)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("yes")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem3)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);


                    } else if ((ch1.equals("yes")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem4)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4) && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)) {
                        myPropertyList.add(propertyData);
                    } else if ((ch1.equals("yes")) && (ch2.equals("yes")) && (spRental.getSelectedItem().toString().equals(sem4)) && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
                        myPropertyList.add(propertyData);

                    }


                }
                adapter.notifyDataSetChanged();
                //  progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //  progressDialog.dismiss();
            }
        });


    }

    //Search Bar
    private void filter(String text) {
        ArrayList<PropertyData> filterList = new ArrayList<>();

        for (PropertyData item : myPropertyList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(item);
            }
        }
        adapter.filteredList(filterList);

    }


    //BottomNavigationView setup

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    public void showFilters(View view) {
        if (filters.getVisibility() == View.VISIBLE) {
            filters.setVisibility(View.GONE);
        } else {
            filters.setVisibility(View.VISIBLE);
        }
    }
}