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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.psyhologyrf.EmployeeInfo;
import com.example.psyhologyrf.MainActivity;
import com.example.psyhologyrf.R;
import com.example.psyhologyrf.databinding.FragmentRegestrationBinding;
import com.example.psyhologyrf.databinding.FragmentSlideshowBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    // creating variables for
    // EditText and buttons.
    private EditText employeeNameEdt, employeePhoneEdt, employeeAddressEdt;
    private Button sendDatabtn;
    private TextView textViewrrr;
    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    EmployeeInfo users;


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
        employeeNameEdt  = (EditText) root.findViewById(R.id.idEdtEmployeeName);
        employeePhoneEdt  = (EditText) root.findViewById(R.id.idEdtEmployeePhoneNumber);
        employeeAddressEdt  = (EditText) root.findViewById(R.id.idEdtEmployeeAddress);
        textViewrrr  = (TextView) root.findViewById(R.id.textViewrrr);
        sendDatabtn  = (Button) root.findViewById(R.id.idBtnSendData);
        // below line is used to get the
        // instance of our FIrebase database.

        //firebaseAppCheck
        FirebaseApp.initializeApp(getContext());
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());

        firebaseDatabase = FirebaseDatabase.getInstance();


        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("users").push();

        // initializing our object
        // class variable.
        users = new EmployeeInfo();

        // initializing our object
        // class variable.
       // employeeInfo = new EmployeeInfo();




        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String name = employeeNameEdt.getText().toString();
                String phone = employeePhoneEdt.getText().toString();
                String address = employeeAddressEdt.getText().toString();


                System.out.println(FirebaseDatabase.getInstance().getReference());
                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(address)) {
                    // if the text fields are empty
                    // then show the below message.
                    textViewrrr.setText("пусто");

                } else {
                    // else call the method to add
                    // data to our database.
                    //databaseReference.setValue("Hello, World!");
                    addDatatoFirebase(name, phone, address);

                }
            }
        });


        return root;
    }


    private void addDatatoFirebase(String name, String phone, String address) {
        // below 3 lines of code is used to set
        // data in our object class.
        users.setEmployeeName(name);
        users.setEmployeeContactNumber(phone);
        users.setEmployeeAddress(address);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(users);
                textViewrrr.setText("data added");
                // after adding this data we are showing toast message.

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                textViewrrr.setText("Fail to add data ");

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}