package com.example.psyhologyrf.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psyhologyrf.CategoryAdapter;
import com.example.psyhologyrf.ModelButtom;
import com.example.psyhologyrf.R;
import com.example.psyhologyrf.SimpleBot;
import com.example.psyhologyrf.databinding.FragmentHomeBinding;
import com.example.psyhologyrf.ui.SayWhithTime;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {



    private FragmentHomeBinding binding;

    private TextView sayText;
    private EditText answerText;
    private Button buttonSend, privet, kakdela, whoyou;
    private ConstraintLayout glavcolor;
    private RecyclerView recuclebuttom;
    SimpleBot simpleBot = new SimpleBot();
    SayWhithTime sayWhithTime = new SayWhithTime();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        glavcolor  = (ConstraintLayout) root.findViewById(R.id.glavcolor);
        buttonSend  = (Button) root.findViewById(R.id.buttonSend);
        privet  = (Button) root.findViewById(R.id.privet);
        kakdela  = (Button) root.findViewById(R.id.kakdela);
        whoyou  = (Button) root.findViewById(R.id.whoyou);
        answerText  = (EditText) root.findViewById(R.id.answerText);
        sayText  = (TextView) root.findViewById(R.id.text_home_ya);
        recuclebuttom  = (RecyclerView) root.findViewById(R.id.recuclebuttom);




        sayText.setText(sayWhithTime.CurrentTime());
        sayWhithTime.SetcolorWithCurrentTime(glavcolor);


        recuclebuttom.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<ModelButtom> categoryList = new ArrayList<>();
        categoryList.add(new ModelButtom(1, "привет"));
        categoryList.add(new ModelButtom(2, "что ты умеешь?"));
        categoryList.add(new ModelButtom(3, "кто ты?"));
        categoryList.add(new ModelButtom(4, "сколько время?"));
        categoryList.add(new ModelButtom(5, "пей сок"));
        categoryList.add(new ModelButtom(6, "пей сок"));
        categoryList.add(new ModelButtom(7, "пей сок"));

        setCategoryRecycle(categoryList);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                System.out.println(sayText.getText().toString());
                sayText.setText(simpleBot.sayInReturn(answerText.getText().toString(), true));
            }
        });




        privet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerText.setText(R.string.helloo);
                sayText.setText(simpleBot.sayInReturn(answerText.getText().toString(), true));
            }
        });
        kakdela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerText.setText(R.string.whatSkill);
                sayText.setText(simpleBot.sayInReturn(answerText.getText().toString(), true));
            }
        });

/*
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String name = preferences.getString("Name", Mytitle);
        if(!name.equalsIgnoreCase(Mytitle))  //не вышло
        {
            name = name + Mytitle;
            answerText.setText(name);
        }
*/

        return root;

    }


    public void setCategoryRecycle(List<ModelButtom> categoryList) {
        //чтобы элементы были друг за другом
        androidx.recyclerview.widget.RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        // RecyclerView.HORIZONTAL - значит прокрутка по горизонтали
        recuclebuttom.setLayoutManager(layoutManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        recuclebuttom.setAdapter(categoryAdapter);

    }


    public void showCategoryByActivity(String myTtitle) {



        // как сделать тут   answerText.setText(myTtitle); ???

        /*
        SharedPreferences mSettings;
        mSettings = getContext().getSharedPreferences("APP_PREFERENCES", getContext().MODE_PRIVATE);
        SharedPreferences.Editor  editor = mSettings.edit();
        editor.putString("APP_PREFERENCES_NAME", myTtitle);   //не вышло
        editor.apply();*/


        // EditText Ttitle =(EditText)  HomeFragment.getView().findViewById(R.id.answerText); //не вышло
        //   Ttitle.setText(myTtitle); //не вышло

        //   myTitletwo  = myTtitle;
        System.out.println(myTtitle + "  это title в showCategoryByActivity");
        //  answerText  = (EditText) getLayoutInflater().getContext(); //не вышло
        // answerText = (EditText)  getView().getRootView().findViewById(R.id.answerText); //не вышло

        // answerText  = (EditText) binding.getRoot().findViewById(R.id.answerText); //не вышло

        //  answerText = (EditText) getView().getRootView().findViewById(R.id.answerText); //не вышло
        // answerText.setText(myTtitle);



    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}