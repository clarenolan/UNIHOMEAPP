package com.example.unihomeapp.Landlord;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unihomeapp.Models.UserData;
import com.example.unihomeapp.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class UserProfile extends AppCompatActivity {

    private TextView  txtEmail;
    private TextInputLayout fullName, email, phoneNo, password;
    private Button btnUpdate2;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Landlord");

        btnUpdate2 = findViewById(R.id.btnUpdate);
        txtEmail = findViewById(R.id.txtEmail);
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.etEmail);
        phoneNo = findViewById(R.id.etPhone);
        password = findViewById(R.id.etPassword);

        mDatabaseUsers.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    UserData user = snapshot.getValue(UserData.class);

                    String name = (String) snapshot.child("name").getValue();
                    String email2 = (String) snapshot.child("email").getValue();
                    String password2 = (String) snapshot.child("password").getValue();
                    String phoneNo2 = (String) snapshot.child("phoneNo").getValue();
                    String username = (String) snapshot.child("username").getValue();


                    fullName.getEditText().setText(name);
                    email.getEditText().setText(email2);
                    phoneNo.getEditText().setText(phoneNo2);
                    password.getEditText().setText(password2);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this, "Read Failed", Toast.LENGTH_SHORT).show();
            }

        });

        btnUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = fullName.getEditText().getText().toString().trim();
                final String emailaddress = email.getEditText().getText().toString().trim();
                final String phone = phoneNo.getEditText().getText().toString().trim();
                final String pass = password.getEditText().getText().toString().trim();
                final String userID = mAuth.getCurrentUser().getUid();


            }
        });

    }

}


//      }
//    private void showAllUserData() {
//        Intent intent = getIntent();
//        String user_username = intent.getStringExtra("username");
//        String user_name = intent.getStringExtra("name");
//        String user_email = intent.getStringExtra("email");
//        String user_phoneNo = intent.getStringExtra("phoneNo");
//        String user_password = intent.getStringExtra("password");}

