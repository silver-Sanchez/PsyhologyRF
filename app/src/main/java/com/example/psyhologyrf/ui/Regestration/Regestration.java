package com.example.psyhologyrf.ui.Regestration;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.psyhologyrf.EmployeeInfo;
import com.example.psyhologyrf.R;
import com.example.psyhologyrf.databinding.FragmentRegestrationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Regestration extends Fragment {

    private FragmentRegestrationBinding binding;

    private static int  SIGN_IN_CODE = 1;
    private FrameLayout registration_main;
    private Button sendBtnauth;
    private Button login;
    private ImageView logOut;
    private TextView authText;
    private TextView secondauHello;
    private EditText Passwordauth;
    private EditText Emailauth;
    private EditText touName;
    private ConstraintLayout registration_pole;
    private FirebaseAuth firebaseAuth;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    EmployeeInfo users;

    private String USER_KEY = "employeeContactNumber";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegestrationModel regestrationModel =
                new ViewModelProvider(this).get(RegestrationModel.class);

        binding = FragmentRegestrationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        registration_main  = (FrameLayout) root.findViewById(R.id.registration_main);
        authText  = (TextView) root.findViewById(R.id.authText);
        secondauHello  = (TextView) root.findViewById(R.id.secondauHello);
        registration_pole  = (ConstraintLayout) root.findViewById(R.id.registration_pole);
        login  = (Button) root.findViewById(R.id.login);
        logOut  = (ImageView) root.findViewById(R.id.logOut);
        sendBtnauth  = (Button) root.findViewById(R.id.sendBtnauth);
        Passwordauth  = (EditText) root.findViewById(R.id.Passwordauth);
        touName  = (EditText) root.findViewById(R.id.touName);
        Emailauth  = (EditText) root.findViewById(R.id.Emailauth);


        firebaseAuth = FirebaseAuth.getInstance();
        //если пользователь ещё не авторизован

        //firebaseAppCheck
        FirebaseApp.initializeApp(getContext());
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());

        firebaseDatabase = FirebaseDatabase.getInstance();


        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("users").push();//запись

       // mReference = FirebaseDatabase.getInstance().getReference().child("Quotes"); //чтение
        // initializing our object
        // class variable.
        users = new EmployeeInfo();



        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            authText.setText("вы не авторизовались");// фунцция startActivityForResult помогает авторизовать пользов
        else {
            Snackbar.make(registration_main, "вы авторизовались", Snackbar.LENGTH_SHORT).show();
            registration_pole.setVisibility(View.GONE); // поля регистрации пропадают если пользователь зарегелся





        }

        sendBtnauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = Emailauth.getText().toString();
                String getPassword = Passwordauth.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        String email = Emailauth.getText().toString();
                        String name = touName.getText().toString();
                        String pass = Passwordauth.getText().toString();

                        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(name) && TextUtils.isEmpty(pass)) {
                            // if the text fields are empty
                            // then show the below message.
                            authText.setText("пусто");
                            Toast.makeText(getContext(), "пусто", Toast.LENGTH_SHORT).show();

                        } else {
                            // else call the method to add
                            // data to our database.
                            //databaseReference.setValue("Hello, World!");
                            addDatatoFirebase(email, name);




                        authText.setText("авторизовались");
                        registration_pole.setVisibility(View.GONE);
                        secondauHello.setText("userid");
                       // secondauHello.setText(firebaseAuth.getCurrentUser().toString());
                       // Toast.makeText(Regestration.this, "", Toast.LENGTH_SHORT).show();
                    }}
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        authText.setText("ошибка");
                    }
                });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = Emailauth.getText().toString();
                String getPassword = Passwordauth.getText().toString();  //разница только в signInWithEmailAndPassword
                firebaseAuth.signInWithEmailAndPassword(getEmail, getPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        registration_pole.setVisibility(View.GONE);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        authText.setText("не верный логин или пароль");
                    }
                });
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                registration_pole.setVisibility(View.VISIBLE);
            }
        });
/*
        final TextView textView = binding.textSlideshow;
        regestrationModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    private void addDatatoFirebase(String name, String email) {
        // below 3 lines of code is used to set
        // data in our object class.
        users.setEmployeeName(name);
        users.setEmployeeContactNumber(email);
        // employeeInfo.setEmployeeAddress(address);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(users);

                // after adding this data we are showing toast message.
                secondauHello.setText("data added" + databaseReference.get());
                 Toast.makeText(getContext(), "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                secondauHello.setText("Fail to add data ");
                Toast.makeText(getContext(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}