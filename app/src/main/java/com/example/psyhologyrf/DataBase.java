package com.example.psyhologyrf;

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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.psyhologyrf.databinding.FragmentRegestrationBinding;
import com.example.psyhologyrf.ui.Regestration.Regestration;
import com.example.psyhologyrf.ui.Regestration.RegestrationModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataBase extends AppCompatActivity {
/*
    private FragmentRegestrationBinding binding;
    // creating variables for
    // EditText and buttons.
    private EditText employeeNameEdt, employeePhoneEdt, employeeAddressEdt;
    private ImageView sendDatabtn;
    private TextView secondauHello;
    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    EmployeeInfo employeeInfo;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegestrationModel regestrationModel =
                new ViewModelProvider(this).get(RegestrationModel.class);

        binding = FragmentRegestrationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

// initializing our edittext and button
        employeeNameEdt  = (EditText) root.findViewById(R.id.idEdtEmployeeName);
        employeePhoneEdt  = (EditText) root.findViewById(R.id.idEdtEmployeePhoneNumber);
        employeeAddressEdt  = (EditText) root.findViewById(R.id.idEdtEmployeeAddress);
        secondauHello  = (TextView) root.findViewById(R.id.secondauHello);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("EmployeeInfo");

        // initializing our object
        // class variable.
        employeeInfo = new EmployeeInfo();


        sendDatabtn  = (ImageView) root.findViewById(R.id.idBtnSendData);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                String name = employeeNameEdt.getText().toString();
                String phone = employeePhoneEdt.getText().toString();
                String address = employeeAddressEdt.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(address)) {
                    // if the text fields are empty
                    // then show the below message.
                    secondauHello.setText("Please add some data.");
                  //  Toast.makeText(Regestration.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    secondauHello.setText("add.");
                    addDatatoFirebase(name, phone, address);
                }
            }
        });


        return root;
    }


   //add data firebase
    private void addDatatoFirebase (String name, String email) {
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
                secondauHello.setText("data added");
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
    }*/
}
