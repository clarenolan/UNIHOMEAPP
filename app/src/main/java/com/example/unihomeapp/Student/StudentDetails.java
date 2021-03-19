package com.example.unihomeapp.Student;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.unihomeapp.Common.CommentFragment;
import com.example.unihomeapp.Landlord.MainActivity;
import com.example.unihomeapp.Landlord.PropertyDetail;
import com.example.unihomeapp.Landlord.UpdateProperty;
import com.example.unihomeapp.Landlord.UploadProperty;
import com.example.unihomeapp.Landlord.UserProfile;
import com.example.unihomeapp.Models.PropertyData;
import com.example.unihomeapp.Models.UserData;
import com.example.unihomeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class StudentDetails extends Fragment {


    private TextView txtFullName, txtEmail;
    private TextInputLayout fullName, studEmail, phoneNo, studPassword, studentNumber;
    private Spinner spUniversity, spYear;
    private Button btnUpdate2;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private StorageReference mStorageRef;
    private CircleImageView studentImage;
    //Uri uri = null;
    //String imageUrl;

    public static final String TAG = "TAG";

    View view;
    private static StudentDetails instance;

    StorageReference storageReference;
    FirebaseFirestore fStore;
    FirebaseUser user;

    public StudentDetails() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scrolling, container, false);


        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Student");

        fStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();

        btnUpdate2 = view.findViewById(R.id.btnUpdateProfile);
        fullName = (TextInputLayout) view.findViewById(R.id.full_name_profile);
        studEmail =(TextInputLayout) view.findViewById(R.id.etEmailProfile);
        phoneNo = (TextInputLayout)view.findViewById(R.id.etPhoneProfile);
        studPassword = (TextInputLayout)view.findViewById(R.id.etPasswordProfile);
        studentImage = view.findViewById(R.id.img_student_profile);
        studentNumber = (TextInputLayout)view.findViewById(R.id.etStudentNumber);

        //Get user data from firebase
                mDatabaseUsers.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot movieSnapshot : snapshot.getChildren()) {
                    UserData movie = snapshot.getValue(UserData.class);

                    String name = (String) snapshot.child("name").getValue();
                    String email2 = (String) snapshot.child("email").getValue();
                    String password2 = (String) snapshot.child("password").getValue();
                    String phoneNo2 = (String) snapshot.child("phoneNo").getValue();
                    String username = (String) snapshot.child("username").getValue();
                   // String property_image = (String) snapshot.child("imageURL").getValue();

                    fullName.getEditText().setText(name);
                    studentNumber.getEditText().setText(username);
                    studEmail.getEditText().setText(email2);
                    phoneNo.getEditText().setText(phoneNo2);
                    studPassword.getEditText().setText(password2);
                   // Glide.with(getContext())
                    //        .load(property_image).into(studentImage);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Read Failed", Toast.LENGTH_SHORT).show();
            }

        });



                //


        String studentName = fullName.getEditText().getText().toString();
        String studentEmail = studEmail.getEditText().getText().toString();
        String studentPhone = phoneNo.getEditText().getText().toString();
        String studentPassword = studPassword.getEditText().getText().toString();
        String strStudentNumber = studentNumber.getEditText().getText().toString();

        StorageReference profileRef = storageReference.child("Student/" + mAuth.getCurrentUser().getUid() + "imageURL");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(studentImage);
            }
        });



        studentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });




        btnUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullName.getEditText().toString().isEmpty() || studEmail.getEditText().toString().isEmpty() || phoneNo.getEditText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "One or Many fields are empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String studentName = fullName.getEditText().getText().toString();
                String studentEmail = studEmail.getEditText().getText().toString();
                String studentPhone = phoneNo.getEditText().getText().toString();
                String studentPassword = studPassword.getEditText().getText().toString();
                String strStudentNumber = studentNumber.getEditText().getText().toString();


                mAuth = FirebaseAuth.getInstance();
                String user_id = mAuth.getCurrentUser().getUid();

//                UserData userData = new UserData( fullName.getEditText().toString().trim(),
//                studentNumber.getEditText().toString().trim(),
//                studEmail.getEditText().toString().trim(),
//                phoneNo.getEditText().toString().trim(),
//                        studPassword.getEditText().toString().trim());

                Map<String, Object> userData = new HashMap<>();
                userData.put("email", studEmail.getEditText().getText().toString().trim() );
                userData.put("name", studentName);
                userData.put("phoneNo", phoneNo.getEditText().getText().toString().trim());
                userData.put("password",studPassword.getEditText().getText().toString().trim());
                userData.put("username",studentNumber.getEditText().getText().toString().trim());

                mDatabaseUsers.child(user_id).updateChildren(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getContext(), "Data Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), StudentProfile.class));
//                                //finish()
//                                getActivity().getSupportFragmentManager().popBackStack();
                }
            }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),"Failed", Toast.LENGTH_SHORT).show();
                    }
                });

//                final String email = studEmail.getEditText().toString().trim();
//                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        DocumentReference docRef = fStore.collection("Student").document(user.getUid());
//                        Map<String, Object> edited = new HashMap<>();
//                        edited.put("email", email);
//                        edited.put("name", fullName.getEditText().toString().trim());
//                        edited.put("phoneNo", phoneNo.getEditText().toString().trim());
//                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(getContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getContext(), MainActivity.class));
//                                //finish()
//                                getActivity().getSupportFragmentManager().popBackStack();
//                            }
//                        });
//                        Toast.makeText(getContext(), "Email is changed.", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getContext(),"Failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

        studEmail.getEditText().setText(studentEmail);
        fullName.getEditText().setText(studentName);
        phoneNo.getEditText().setText(studentPhone);
        studPassword.getEditText().setText(studentPassword);
        studentNumber.getEditText().setText(strStudentNumber);
//
//        fullName.getEditText().setText(name);
//                    studentNumber.getEditText().setText(username);
//                    email.getEditText().setText(email2);
//                    phoneNo.getEditText().setText(phoneNo2);
//                    password.getEditText().setText(password2);

        // Log.d(TAG, "onCreate: " + fullName + " " + email + " " + phone);

        return view;
    }


//        studentImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent photoPicker = new Intent();
////                photoPicker.setType("image/*");
////                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
////                startActivityForResult(Intent.createChooser(photoPicker,"Select Profile Photo"), PICK_IMAGE_REQUEST);
//                Intent photoPicker = new Intent(Intent.ACTION_PICK);
//                photoPicker.setType("image/*");
//                startActivityForResult(photoPicker, 1);
//            }
//        });


//        mDatabaseUsers.child(user_id).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                for (DataSnapshot movieSnapshot : snapshot.getChildren()) {
//                    UserData movie = snapshot.getValue(UserData.class);
//
//                    String name = (String) snapshot.child("name").getValue();
//                    String email2 = (String) snapshot.child("email").getValue();
//                    String password2 = (String) snapshot.child("password").getValue();
//                    String phoneNo2 = (String) snapshot.child("phoneNo").getValue();
//                    String username = (String) snapshot.child("username").getValue();
//                    String property_image = (String) snapshot.child("imageURL").getValue();
//
//                    fullName.getEditText().setText(name);
//                    studentNumber.getEditText().setText(username);
//                    email.getEditText().setText(email2);
//                    phoneNo.getEditText().setText(phoneNo2);
//                    password.getEditText().setText(password2);
//                    Glide.with(getContext())
//                            .load(property_image).into(studentImage);
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), "Read Failed", Toast.LENGTH_SHORT).show();
//            }
//
//        });


    @Override
    public void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();

                //profileImage.setImageURI(imageUri);

                uploadImageToFirebase(imageUri);


            }
        }

    }

    private void uploadImageToFirebase(Uri imageUri) {
        // uplaod image to firebase storage
        final StorageReference fileRef = storageReference.child("Student/"+mAuth.getCurrentUser().getUid()+"imageURL");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(studentImage);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

