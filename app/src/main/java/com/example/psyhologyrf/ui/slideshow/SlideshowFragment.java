package com.example.psyhologyrf.ui.slideshow;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.psyhologyrf.EmployeeInfo;
import com.example.psyhologyrf.MainActivity;
import com.example.psyhologyrf.R;
import com.example.psyhologyrf.databinding.FragmentRegestrationBinding;
import com.example.psyhologyrf.databinding.FragmentSlideshowBinding;
import com.example.psyhologyrf.ui.Regestration.Regestration;
import com.example.psyhologyrf.ui.SayWhithTime;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    // creating variables for
    // EditText and buttons.

    private TextView textViewrrr, textNoRegister;
    private FirebaseAuth firebaseAuth;
    private ConstraintLayout slideShow, inLoginconstraint;
    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    SayWhithTime sayWhithTime = new SayWhithTime();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


/*
        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
*/
// initializing our edittext and button

        textViewrrr  = (TextView) root.findViewById(R.id.textViewrrr);
        textNoRegister  = (TextView) root.findViewById(R.id.textNoRegister);
        slideShow  = (ConstraintLayout) root.findViewById(R.id.slideShow);
        inLoginconstraint  = (ConstraintLayout) root.findViewById(R.id.inLoginconstraint);

        sayWhithTime.SetcolorWithCurrentTime(slideShow);
        sayWhithTime.SetcolorWithCurrentTime(inLoginconstraint);
        // below line is used to get the
        // instance of our FIrebase database.

        //firebaseAppCheck


        firebaseDatabase = FirebaseDatabase.getInstance();


        // below line is used to get reference for our database.


        // initializing our object
        // class variable.
       // employeeInfo = new EmployeeInfo();


        Regestration regestration = new Regestration();
        regestration.OnAuthCnow(firebaseAuth, slideShow, textViewrrr, "вы авторизовались", textNoRegister, "Авторизуйтесь чтобы видеть содержимое");


        // adding on click listener for our button.


        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}