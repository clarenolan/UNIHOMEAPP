package com.example.unihomeapp.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toolbar;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.unihomeapp.Models.PropertyData;
//import com.example.unihomeapp.R;
//import com.example.unihomeapp.Student.PropertyAdapter;
//import com.example.unihomeapp.UserWelcome;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
public class HomeFragment extends Fragment {
//
//
//    private static final String TAG = "HomeFragment";
//
//    CheckBox chbWifi, chbParking, chbSem1, chbSem2, chbAcademic, chbSummer;
//    TextView textView;
//    private FirebaseAuth mAuth;
//    List<PropertyData> myPropertyList;
//    private DatabaseReference databaseReference;
//    private ValueEventListener eventListener;
//    ProgressDialog progressDialog;
//    EditText txtSearch;
//    Button btnApply, btnClear;
//    String ch1, ch2, ch3, ch4, ch5, ch6;
//    Spinner spRental, spCounties, spBeds, spRent;
//
//
//    private PropertyAdapter adapter;
//    private RecyclerView recyclerView;
//    private CheckBox cbWifi;
//    //private Spinner spCounty;
//    Toolbar toolbar;
//
//    View view;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        //       setUpRecyclerView();
//        mAuth = FirebaseAuth.getInstance();
//        chbWifi = view.findViewById(R.id.cbWifi2);
//        chbParking = view.findViewById(R.id.cbParking2);
//        spRental = view.findViewById(R.id.spRental);
//        spCounties = view.findViewById(R.id.spCounty);
//        textView = view.findViewById(R.id.text);
//        txtSearch = view.findViewById(R.id.txtSearch2);
//        btnApply = view.findViewById(R.id.btnApply2);
//        btnClear = view.findViewById(R.id.btnClear2);
//
//
//        btnApply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myPropertyList.clear();
//                fetch();
//            }
//        });
//
//
//
//
//        recyclerView = view.findViewById(R.id.recyclerView2);
//        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(),1 );
//        recyclerView.setLayoutManager(gridLayoutManager2);
//
//        myPropertyList = new ArrayList<>();
//
//        adapter = new PropertyAdapter(getContext(),myPropertyList);
//        recyclerView.setAdapter(adapter);
//        databaseReference = FirebaseDatabase.getInstance().getReference("Properties");
//
//        //Setting options 1-12, rental period spinner
//        List<String> counties = new ArrayList<>();
//
//        counties.add("");
//        counties.add("Antrim");
//        counties.add("Armagh");
//        counties.add("Carlow");
//        counties.add("Cavan");
//        counties.add("Cork");
//        counties.add("Derry");
//        counties.add("Donegal");
//        counties.add("Down");
//        counties.add("Dublin");
//        counties.add("Fermanagh");
//        counties.add("Galway");
//        counties.add("Kerry");
//        counties.add("Kildare");
//        counties.add("Kilkenny");
//        counties.add("Laois");
//        counties.add("Leitrim");
//        counties.add("Longford");
//        counties.add("Louth");
//        counties.add("Mayo");
//        counties.add("Meath");
//        counties.add("Monaghan");
//        counties.add("Offaly");
//        counties.add("Roscommon");
//        counties.add("Sligo");
//        counties.add("Tipperary");
//        counties.add("Tyrone");
//        counties.add("Mayo");
//        counties.add("Waterford");
//        counties.add("Westmeath");
//        counties.add("Wexford");
//        counties.add("Wicklow");
//
//
//        ArrayAdapter<String> dataAdapter;
//        dataAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, counties);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spCounties.setAdapter(dataAdapter);
//
//
//        //Setting options 1-12, rental period spinner
//        List<String> categories4 = new ArrayList<>();
//
//        categories4.add("0");
//        categories4.add("Academic Year");
//        categories4.add("Semester 1");
//        categories4.add("Semester 2");
//        categories4.add("Summer Period");
//
//        ArrayAdapter<String> dataAdapter4;
//        dataAdapter4 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, categories4);
//        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spRental.setAdapter(dataAdapter4);
//
//        loadUI();
//
//
//
//        txtSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });
//
//        //Load method to reset UI
//        btnClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadUI();
//                spRental.setSelection(0);
//                spCounties.setSelection(0);
//            }
//        });
//        return view;
//    }
//    //Reset UI
//    private void loadUI() {
//        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //Clear selected values
//                myPropertyList.clear();
//                if (chbWifi.isChecked()) {
//                    chbWifi.setChecked(false);
//                }
//                if (chbParking.isChecked()) {
//                    chbParking.setChecked(false);
//                }
//                spRental.setSelection(0);
//                spCounties.setSelection(0);
////                spRent.setSelection(0);
////                spBeds.setSelection(0);
//
//                //Load new propertyData model
//                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
//                    PropertyData propertyData = itemSnapshot.getValue(PropertyData.class);
//                    propertyData.setKey(itemSnapshot.getKey());
//                    propertyData.setName((String) itemSnapshot.child("name").getValue());
//                    propertyData.setAddress((String) itemSnapshot.child("address").getValue());
//                    propertyData.setPrice((String) itemSnapshot.child("price").getValue());
//                    propertyData.setImageUrl((String) itemSnapshot.child("imageUrl").getValue());
//                    //Load only active properties
//                    if (propertyData.getActive().equals("yes")) {
//                        myPropertyList.add(propertyData);
//                    }
//                }
//                adapter.notifyDataSetChanged();
//               // progressDialog.dismiss();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//              //  progressDialog.dismiss();
//            }
//        });
//    }
//
//    public void signOutStudent(View view) {
//        FirebaseAuth.getInstance().signOut();
//        Intent intToWelcome = new Intent(getContext(), UserWelcome.class);
//        startActivity(intToWelcome);
//    }
//
//    private void fetch() {
//        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
//                    PropertyData propertyData = itemSnapshot.getValue(PropertyData.class);
//                    propertyData.setKey(itemSnapshot.getKey());
//                    propertyData.setName((String) itemSnapshot.child("name").getValue());
//                    propertyData.setAddress((String) itemSnapshot.child("address").getValue());
//                    propertyData.setPrice((String) itemSnapshot.child("price").getValue());
//                    propertyData.setImageUrl((String) itemSnapshot.child("imageUrl").getValue());
//
//                    String property_uid = (String) itemSnapshot.child("uid").getValue();
//                    String string = "Included";
//                    String sem1 = "Semester 1";
//                    String sem2 = "Semester 2";
//                    String sem3 = "Academic Year";
//                    String sem4 = "Summer Period";
//
//                    //County Variables
//                    String a = "Antrim";
//                    String b = "Armagh";
//                    String c = "Carlow";
//                    String d = "Cavan";
//                    String e = "Cork";
//                    String f = "Derry";
//                    String g = ("Donegal");
//                    String h = ("Down");
//                    String i = ("Dublin");
//                    String j = ("Fermanagh");
//                    String k = ("Galway");
//                    String l = ("Kerry");
//                    String m = ("Kildare");
//                    String n = ("Kilkenny");
//                    String o = ("Laois");
//                    String p = ("Leitrim");
//                    String q = ("Longford");
//                    String r = ("Louth");
//                    String s = ("Mayo");
//                    String t = ("Meath");
//                    String u = ("Monaghan");
//                    String v = ("Offaly");
//                    String w = ("Roscommon");
//                    String x = ("Sligo");
//                    String y = "Tipperary";
//                    String z = ("Tyrone");
//                    String a1 = ("Mayo");
//                    String a2 = ("Waterford");
//                    String a3 = ("Westmeath");
//                    String a4 = ("Wexford");
//                    String a5 = ("Wicklow");
//
//                    //Setting variables from amenity checkboxes
//                    if (chbWifi.isChecked()) {
//                        ch1 = "yes";
//                    }else {
//                        ch1 = "no";
//                    }
//                    if (chbParking.isChecked()) {
//                        ch2 = "yes";
//                    }else {
//                        ch2 = "no";
//                    }
//
//                    //Wifi = yes, Parking = no, County = Cork, Period = Sem1
//                    if ((ch1.equals("yes"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem1))
//                            && (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem1)
//                            && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//
//                    //Wifi = yes, Parking = no, County = Cork, Period = Sem2
//                    }else if((ch1.equals("yes"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem2)
//                    )&& (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem2)
//                            && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//
//                    //Wifi = yes, Parking = no, County = Cork, Period = Academic year
//                    }else if((ch1.equals("yes"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem3))&&
//                            (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3)
//                            && (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//
//                    //Wifi = yes, Parking = no, County = Cork, Period = Summer Period
//                    } else if((ch1.equals("yes"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem4)) &&
//                            (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4)
//                            &&(spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//
//
//
//                    }else if ((ch1.equals("yes"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem1))&& (itemSnapshot.child("wifi").getValue()).equals(string)
//                            && (itemSnapshot.child("period").getValue()).equals(sem1) && (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//                    }else if((ch1.equals("yes"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem2))&& (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem2)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//
//                    }else if((ch1.equals("yes"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem3))&& (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//                    } else if((ch1.equals("yes"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem4))&& (itemSnapshot.child("wifi").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4)&&(spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//
//
//                    }else if ((ch1.equals("no"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("period").getValue()).equals(sem1)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    }else if ((ch1.equals("no"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("period").getValue()).equals(sem1)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//                    }else if((ch1.equals("no"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem2))&& (itemSnapshot.child("period").getValue()).equals(sem2)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    }else if((ch1.equals("no"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem2))&& (itemSnapshot.child("period").getValue()).equals(sem2)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//                    }else if((ch1.equals("no"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem3))&&  (itemSnapshot.child("period").getValue()).equals(sem3)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    }else if((ch1.equals("no"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem3))&& (itemSnapshot.child("period").getValue()).equals(sem3)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//
//                    } else if((ch1.equals("no"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem4))&& (itemSnapshot.child("period").getValue()).equals(sem4)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    } else if((ch1.equals("no"))&& (ch2.equals("no"))&& (spRental.getSelectedItem().toString().equals(sem4))&& (itemSnapshot.child("period").getValue()).equals(sem4)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
//                        myPropertyList.add(propertyData);
//
//
//
//
//
//
//
//                    }else if ((ch1.equals("no"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("period").getValue()).equals(sem1)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    }else if ((ch1.equals("no"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem1)) && (itemSnapshot.child("period").getValue()).equals(sem1)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//                    }else if((ch1.equals("no"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem2))&& (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem2)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    }else if((ch1.equals("no"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem2))&& (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem2)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//                    }else if((ch1.equals("no"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem3))&& (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    }else if((ch1.equals("no"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem3))&& (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//                    } else if((ch1.equals("no"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem4))&& (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    }else if((ch1.equals("no"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem4))&& (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)) {
//                        myPropertyList.add(propertyData);
//
//
//
//
//
//
//
//
//
//                    }else if ((ch1.equals("yes"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem1))&& (itemSnapshot.child("parking").getValue()).equals(string)&& (itemSnapshot.child("wifi").getValue()).equals(string)  && (itemSnapshot.child("period").getValue()).equals(sem1)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    }else if ((ch1.equals("yes"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem1))&& (itemSnapshot.child("parking").getValue()).equals(string)&& (itemSnapshot.child("wifi").getValue()).equals(string)  && (itemSnapshot.child("period").getValue()).equals(sem1)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//
//                    }else if((ch1.equals("yes"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem2))&& (itemSnapshot.child("parking").getValue()).equals(string)&& (itemSnapshot.child("wifi").getValue()).equals(string)  && (itemSnapshot.child("period").getValue()).equals(sem2)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    }else if((ch1.equals("yes"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem2))&& (itemSnapshot.child("parking").getValue()).equals(string)&& (itemSnapshot.child("wifi").getValue()).equals(string)  && (itemSnapshot.child("period").getValue()).equals(sem2)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//
//                    }else if((ch1.equals("yes"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem3))&& (itemSnapshot.child("wifi").getValue()).equals(string)&& (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    }else if((ch1.equals("yes"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem3))&& (itemSnapshot.child("wifi").getValue()).equals(string)&& (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem3)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//
//
//
//
//
//                    } else if((ch1.equals("yes"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem4))&& (itemSnapshot.child("wifi").getValue()).equals(string)&& (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4)&& (spCounties.getSelectedItem().toString().equals(e)) && itemSnapshot.child("county").getValue().equals(e)){
//                        myPropertyList.add(propertyData);
//                    } else if((ch1.equals("yes"))&& (ch2.equals("yes"))&& (spRental.getSelectedItem().toString().equals(sem4))&& (itemSnapshot.child("wifi").getValue()).equals(string)&& (itemSnapshot.child("parking").getValue()).equals(string) && (itemSnapshot.child("period").getValue()).equals(sem4)&& (spCounties.getSelectedItem().toString().equals(y)) && itemSnapshot.child("county").getValue().equals(y)){
//                        myPropertyList.add(propertyData);
//
//                    }
//
//
//                }
//                adapter.notifyDataSetChanged();
//              //  progressDialog.dismiss();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//              //  progressDialog.dismiss();
//            }
//        });
//
//
//    }
//
//    private void filter(String text) {
//        ArrayList<PropertyData> filterList = new ArrayList<>();
//
//        for(PropertyData item: myPropertyList){
//            if(item.getName().toLowerCase().contains(text.toLowerCase())){
//                filterList.add(item);
//            }
//        }
//        adapter.filteredList(filterList);
//    }

}






//spCounty = view.findViewById(R.id.spCounty);

        //Setting county options
//        List<String> counties = new ArrayList<>();
//
//        counties.add("Cork");
//        counties.add("Tipperary");
//        counties.add("Clare");
//        counties.add("Limerick");
//        counties.add("Waterford");
//        counties.add("Kerry");
//        counties.add("Kilkenny");
//        counties.add("8");
//        counties.add("9");
//        counties.add("10");
//        counties.add("11");
//        counties.add("12");
//
//        ArrayAdapter<String> dataAdapter1;
//        dataAdapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, counties);
//        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spCounty.setAdapter(dataAdapter1);




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//
//        MenuItem menuItem = menu.findItem(R.id.search_view);
//
//        SearchView searchView = (SearchView) menuItem.getActionView();
//
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                PropertyAdapter.getFilter().filter(newText);
//                return true;
//            }
//        });
//
//
//
//        return  true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if(id == R.id.search_view){
//            return  true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    public void setUpRecyclerView(){
//
//          Query query = FirebaseDatabase.getInstance().getReference("Properties");

//        String county = spCounty.getSelectedItem().toString().trim();
//
//        Query query = null;
//
//        if (cbWifi.isChecked()){
//             query = FirebaseDatabase.getInstance().getReference("Properties").orderByChild("wifi").equalTo("Included");
//        }else{
//             query = FirebaseDatabase.getInstance()
//                    .getReference().child("Properties");
//
//        }
//
//        if(county == "Cork"){
//            query = FirebaseDatabase.getInstance().getReference("Properties").orderByChild("county")
//                    .equalTo("Cork");
//        } else{
//            query = FirebaseDatabase.getInstance()
//                    .getReference().child("Properties");
//        }
//
//
//        FirebaseRecyclerOptions<PropertyData> options = new FirebaseRecyclerOptions.Builder<PropertyData>()
//                .setQuery(query, new SnapshotParser<PropertyData>() {
//                    @NonNull
//                    @Override
//                    public PropertyData parseSnapshot(@NonNull DataSnapshot snapshot) {
//                        return new PropertyData(snapshot.child("name").getValue().toString(),
//                                snapshot.child("county").getValue().toString(),
//                                snapshot.child("address").getValue().toString(),
//                                snapshot.child("price").getValue().toString(),
//                                snapshot.child("imageUrl").getValue().toString(),
//                                snapshot.child("wifi").getValue().toString(),
//                                snapshot.child("bins").getValue().toString(),
//                                snapshot.child("electricity").getValue().toString(),
//                                snapshot.child("heating").getValue().toString(),
//                                snapshot.child("washing").getValue().toString(),
//                                snapshot.child("dryer").getValue().toString(),
//                                snapshot.child("parking").getValue().toString(),
//                                snapshot.child("dish").getValue().toString(),
//                                snapshot.child("tv").getValue().toString(),
//                                snapshot.child("kitchen").getValue().toString(),
//                                snapshot.child("beds").getValue().toString(),
//                                snapshot.child("availableBeds").getValue().toString(),
//                                snapshot.child("bathrooms").getValue().toString(),
//                                snapshot.child("period").getValue().toString(),
//                                snapshot.child("active").getValue().toString(),
//                                snapshot.child("uid").getValue().toString());
//                    }
//                })
//                .build();
//
//
//        adapter = new PropertyAdapter(getContext(),options);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);
//
//
//
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        if (adapter != null) {
//            adapter.startListening();
//        }
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (adapter != null) {
//            adapter.stopListening();
//        }
//
//    }

//
//}