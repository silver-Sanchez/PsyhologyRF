package com.example.psyhologyrf.ui.Regestration;

import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.psyhologyrf.EmployeeInfo;
import com.example.psyhologyrf.R;
import com.example.psyhologyrf.databinding.FragmentRegestrationBinding;
import com.example.psyhologyrf.ui.SayWhithTime;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

import java.util.Objects;

public class Regestration extends Fragment {

    private FragmentRegestrationBinding binding;

    private static int  SIGN_IN_CODE = 1;
    private FrameLayout registration_main;
    private Button sendBtnauth, login;
    private ImageView logOut;
    private TextView authText, secondauHello, youNamme;
    private EditText Passwordauth, Emailauth, touName;
    private ConstraintLayout registration_pole;
    private ConstraintLayout inauthlayer;
    private FirebaseAuth firebaseAuth;
    private DataSnapshot dataSnapshot;
    private String userId;
    String currentUserID;
    //userId = firebaseAuth.getUid();
    // creating a variable for our
    // Firebase Database.
    //FirebaseDatabase firebaseDatabase;
    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    public static final String APP_PREFERENCES = "mysettings";// это будет именем файла настроек
    public static final String APP_PREFERENCES_NAME = "Nickname"; // Далее нужно создать параметры, которые вы хотите сохранять в настройках
    public static final String APP_PREFERENCES_AGE = "Age"; // возраст кота

    // creating a variable for
    // our object class
    EmployeeInfo users;

    SharedPreferences mSettings; //сохранять любые значения

    private String USER_KEY = "employeeContactNumber";
    SayWhithTime sayWhithTime = new SayWhithTime();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegestrationModel regestrationModel =
                new ViewModelProvider(this).get(RegestrationModel.class);

        binding = FragmentRegestrationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mSettings = getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        registration_main  = (FrameLayout) root.findViewById(R.id.registration_main);
        authText  = (TextView) root.findViewById(R.id.authText);
        secondauHello  = (TextView) root.findViewById(R.id.secondauHello);
        youNamme  = (TextView) root.findViewById(R.id.youNamme);
        registration_pole  = (ConstraintLayout) root.findViewById(R.id.registration_pole);
        inauthlayer  = (ConstraintLayout) root.findViewById(R.id.inauthlayer);
        login  = (Button) root.findViewById(R.id.login);
        logOut  = (ImageView) root.findViewById(R.id.logOut);
        sendBtnauth  = (Button) root.findViewById(R.id.sendBtnauth);
        Passwordauth  = (EditText) root.findViewById(R.id.Passwordauth);
        touName  = (EditText) root.findViewById(R.id.touName);
        Emailauth  = (EditText) root.findViewById(R.id.Emailauth);

        sayWhithTime.SetcolorWithCurrentTime(inauthlayer);
        sayWhithTime.SetcolorWithCurrentTime(registration_pole);
        firebaseAuth = FirebaseAuth.getInstance();
       // dataSnapshot = DataSnapshot;
        //если пользователь ещё не авторизован

        //firebaseAppCheck
        FirebaseApp.initializeApp(getContext());
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());

        //firebaseDatabase = FirebaseDatabase.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference();
        // below line is used to get reference for our database.
       // String userIdS = firebaseAuth.getUid();

        // databaseReference = firebaseDatabase.getReference("users"); //.child(Objects.requireNonNull(firebaseAuth.getUid()));
       //  databaseReference.child(userIdS).push();
       // databaseReference = firebaseDatabase.getReference("users").child(Objects.requireNonNull(firebaseAuth.getUid())); //запись
       // databaseReference = firebaseDatabase.getReference("users").child(Objects.requireNonNull(firebaseAuth.getUid())); //запись
      //  databaseReference = firebaseDatabase.getReference("users").child(firebaseAuth.getInstance().getCurrentUser().getUid()); //запись данных по id user
        users = new EmployeeInfo();



        OnAuthCnow(firebaseAuth, registration_pole, secondauHello, "Приветствую!", authText, "Авторизуйтесь чтобы видеть содержимое"); // если уже зарегестрирован
        //ShowInfo(dataSnapshot);



    sendBtnauth.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String email = Emailauth.getText().toString();
            String name = touName.getText().toString();
            String pass = Passwordauth.getText().toString();



            //Чтение значений параметров
            //Получив нужный текст, сохраняем его через метод putString()
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_NAME, name); // ключь APP_PREFERENCES_NAME из поля name
            editor.apply();
            ///////////////////////////////////////////////////////////////////

            if (TextUtils.isEmpty(email)) {
                // if the text fields are empty
                authText.setText("введите логин имя и пароль");
                Toast.makeText(getContext(), "заполните email", Toast.LENGTH_SHORT).show();
            }
                else if (TextUtils.isEmpty(name)){
                    authText.setText("введите логин имя и пароль");
                    Toast.makeText(getContext(), "заполните  имя", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass)){
                    authText.setText("введите логин имя и пароль");
                    Toast.makeText(getContext(), "заполните пароль", Toast.LENGTH_SHORT).show();}
             else {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(Objects.requireNonNull(firebaseAuth.getUid())); // добавляем в базу по id авторизации

                        addDatatoFirebase(email, name); // добавляем данные регистрации

                        authText.setText("вы успешно авторизовались");
                        registration_pole.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "вы успешно авторизовались", Toast.LENGTH_SHORT).show();
                        // secondauHello.setText(firebaseAuth.getCurrentUser().toString());
                        // Toast.makeText(Regestration.this, "", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "возможно такой пользователь уже существует", Toast.LENGTH_SHORT).show();
                        authText.setText("возможно такой пользователь уже существует");
                    }
                });
            }
        }
    });
    //для логина
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String getEmail = Emailauth.getText().toString();
                    String getPassword = Passwordauth.getText().toString();  //разница только в signInWithEmailAndPassword

                    if (TextUtils.isEmpty(getEmail)) {
                        // if the text fields are empty
                        // then show the below message.
                        authText.setText("введите логин и пароль");
                        Toast.makeText(getContext(), "заполните email", Toast.LENGTH_SHORT).show();

                    }else if (TextUtils.isEmpty(getPassword)){
                        authText.setText("введите логин и пароль");
                        Toast.makeText(getContext(), "заполните пароль", Toast.LENGTH_SHORT).show();
                    }
                    else {

                    firebaseAuth.signInWithEmailAndPassword(getEmail, getPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            OnAuthCnow(firebaseAuth, registration_pole, secondauHello, "Приветствую", authText, "Авторизуйтесь чтобы видеть содержимое");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "не верный логин или пароль", Toast.LENGTH_SHORT).show();
                             authText.setText("не верный логин или пароль");
                        }
                    });
                }
                }

            });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                registration_pole.setVisibility(View.VISIBLE);
              //  databaseReference.removeEventListener(ValueEventListener());
            }
        });

        //загружаем с локального устройства
        if(mSettings.contains(APP_PREFERENCES_NAME)) {
            youNamme.setText(mSettings.getString(APP_PREFERENCES_NAME, ""));
        }else {youNamme.setText("нет данных");}


        /////// если раскоментировать код ниже, будет подгружать из базы firebase
      /*  ShowInfo(new MyCallback() {
            @Override
            public void onCallback(String value) {
                youNamme.setText(value);
            }
        });*/


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
                  //  String userName = snapshot.child(USER_KEY).getValue(String.class);
                  //  secondauHello.setText("data added" + userName);
                  //  ShowInfo(snapshot);
                    Toast.makeText(getContext(), "data added", Toast.LENGTH_SHORT).show();
                   // ShowInfo(dataSnapshot);


                /////// если раскоментировать код ниже, будет подгружать из базы firebase
               /* ShowInfo(new MyCallback() {
                    @Override
                    public void onCallback(String value) {
                        youNamme.setText(value);
                    }
                });*/
                ///////////////

                /////// подгружает с локального устройства
                if(mSettings.contains(APP_PREFERENCES_NAME)) {
                    youNamme.setText(mSettings.getString(APP_PREFERENCES_NAME, ""));
                }else {youNamme.setText("загружаю...");}
                ////////
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                secondauHello.setText("Fail to add data ");
                //Toast.makeText(getContext(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void OnAuthCnow(@NonNull FirebaseAuth firebaseAuth, ConstraintLayout constraintLayout, TextView textView, String text, TextView noautorizet, String textNoauth){
        if (firebaseAuth.getInstance().getCurrentUser() == null)
            noautorizet.setText(textNoauth);// фунцция startActivityForResult помогает авторизовать пользов
        else {
           // Snackbar.make(registration_main, "вы авторизовались", Snackbar.LENGTH_SHORT).show();
            constraintLayout.setVisibility(View.GONE); // поля регистрации пропадают если пользователь зарегелся

            textView.setText(text);
           // secondauHello.setText("прив" + " " + users.getEmployeeContactNumber());



        }
    }
    public interface MyCallback { //нужно для извлечения значения так как task не может быть возвращаемой
        void onCallback(String value);
        // возвращаем значение из ShowInfo
    }
    public void ShowInfo(MyCallback myCallback){
        if (firebaseAuth.getInstance().getCurrentUser() == null) {
            Log.e("firebase", "Error getting data NULL");
        }else {
            // databaseReference = FirebaseDatabase.getInstance().getReference("users").child(Objects.requireNonNull(firebaseAuth.getUid()))
            FirebaseDatabase.getInstance().getReference("users").child(Objects.requireNonNull(firebaseAuth.getUid())).child("employeeContactNumber").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        //youNamme.setText("привет незнакомец");
                        Log.e("firebase", "Error getting data", task.getException());
                        myCallback.onCallback("привет незнакомец");
                      String.valueOf(task.getException());
                    } else {
                       // youNamme.setText(String.valueOf(task.getResult().getValue()));
                       // users.setEmployeeContactNumber(String.valueOf(task.getResult().getValue()));
                       // youNamme.setText(users.getEmployeeContactNumber());
                       // String.valueOf(task.getResult().getValue());
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        myCallback.onCallback(String.valueOf(task.getResult().getValue()));

                    }
                }
            });
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}