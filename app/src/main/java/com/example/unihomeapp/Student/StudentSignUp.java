package com.example.unihomeapp.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.unihomeapp.Models.UserData;
import com.example.unihomeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StudentSignUp extends AppCompatActivity {

    private Button btnRegister;
    private FirebaseAuth mAuth;
    private TextInputLayout etName, etEmail, etNumber, etPhone, etPassword;
    private ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        mAuth = FirebaseAuth.getInstance();

        initializeUI();



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });


    }


    private void registerUser() {
        String name = etName.getEditText().getText().toString();
        String number = etNumber.getEditText().getText().toString();
        String email = etEmail.getEditText().getText().toString();
        String phone = etPhone.getEditText().getText().toString();
        String password = etPassword.getEditText().getText().toString();


        if (number.isEmpty()) {
            etNumber.setError("Please fill in all details");
            etNumber.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            etPhone.setError("Please fill in all details");
            etPhone.requestFocus();
            return;
        }


        if (name.isEmpty()) {
            etName.setError("Please fill in all details");
            etName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("Please enter email address");
            etEmail.requestFocus();
            return;
        }

         if (password.isEmpty()) {
             etPassword.setError("Please enter your password");
             etPassword.requestFocus();
             return;
         }


         if (password.length() < 6) {
             Toast.makeText(StudentSignUp.this, "Password too short", Toast.LENGTH_SHORT).show();
         }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(StudentSignUp.this, StudentLogin.class);
                            startActivity(intent);

                            createUser();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



    }

    private void createUser() {
        UserData user = new UserData(etName.getEditText().getText().toString(),
                etNumber.getEditText().getText().toString(),
                etEmail.getEditText().getText().toString(),
                etPhone.getEditText().getText().toString(),
                etPassword.getEditText().getText().toString());

        //String number = etNumber.getEditText().getText().toString();
        String user_id = mAuth.getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference("Student").child(user_id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {


            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(StudentSignUp.this, "UserUploaded", Toast.LENGTH_SHORT).show();

                    finish();
                }


            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StudentSignUp.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void initializeUI() {

        etName = findViewById(R.id.txtName);
        etEmail = findViewById(R.id.txtEmail);
        etNumber = findViewById(R.id.txtStudentNo);
        etPhone = findViewById(R.id.txtPhone);
        etPassword = findViewById(R.id.txtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressBar);
    }

    public void startStuLogin(View view) {
        Intent intent = new Intent(StudentSignUp.this, StudentLogin.class);
        startActivity(intent);

    }
}