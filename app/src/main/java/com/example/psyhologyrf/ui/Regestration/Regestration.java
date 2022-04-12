package com.example.psyhologyrf.ui.Regestration;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.psyhologyrf.R;
import com.example.psyhologyrf.databinding.FragmentRegestrationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

       String name = touName.getText().toString();


        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            authText.setText("вы не авторизовались");// фунцция startActivityForResult помогает авторизовать пользов
        else {
            Snackbar.make(registration_main, "вы авторизовались", Snackbar.LENGTH_SHORT).show();
            registration_pole.setVisibility(View.GONE); // поля регистрации пропадают если пользователь зарегелся
            secondauHello.setText(name);

        }

        sendBtnauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = Emailauth.getText().toString();
                String getPassword = Passwordauth.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        authText.setText("авторизовались");
                        registration_pole.setVisibility(View.GONE);
                        secondauHello.setText(name);
                       // secondauHello.setText(firebaseAuth.getCurrentUser().toString());
                       // Toast.makeText(Regestration.this, "", Toast.LENGTH_SHORT).show();
                    }
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
                        secondauHello.setText(firebaseAuth.getUid());
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



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}