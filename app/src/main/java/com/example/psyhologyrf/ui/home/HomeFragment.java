package com.example.psyhologyrf.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class HomeFragment extends Fragment implements OnCategoryClickCallback {


    private FragmentHomeBinding binding;

    private TextView sayText;
    private EditText answerText;
    private Button buttonSend;
    private ConstraintLayout glavcolor;
    private RecyclerView recuclebuttom;
    private HomeViewModel mHomeViewModel;
    SimpleBot simpleBot = new SimpleBot();
    SayWhithTime sayWhithTime = new SayWhithTime();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mHomeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        glavcolor  = (ConstraintLayout) root.findViewById(R.id.glavcolor);
        buttonSend  = (Button) root.findViewById(R.id.buttonSend);
       // privet  = (Button) root.findViewById(R.id.privet);
      //  kakdela  = (Button) root.findViewById(R.id.kakdela);
      //  whoyou  = (Button) root.findViewById(R.id.whoyou);
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
        categoryList.add(new ModelButtom(5, "Как тебя зовут?"));
        categoryList.add(new ModelButtom(6, "стоимость"));
        categoryList.add(new ModelButtom(7, "до свидания"));

        setCategoryRecycle(categoryList);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                System.out.println(sayText.getText().toString());
                sayText.setText(simpleBot.sayInReturn(answerText.getText().toString(), true));
            }
        });



        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Подписываемся на изменения LiveData во вьюмодели. Когда текст в LiveData изменится, он будет вписан в EditText
        mHomeViewModel.getText().observe(getViewLifecycleOwner(), s -> {
            answerText.setText(s);
            sayText.setText(simpleBot.sayInReturn(answerText.getText().toString(), true));

        });
    }

    public void setCategoryRecycle(List<ModelButtom> categoryList) {
        //чтобы элементы были друг за другом
        androidx.recyclerview.widget.RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        // RecyclerView.HORIZONTAL - значит прокрутка по горизонтали
        recuclebuttom.setLayoutManager(layoutManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(
                getContext(),
                categoryList,
                this);
        recuclebuttom.setAdapter(categoryAdapter);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


// Обрабатываем callback с новым текстом. Отправляем его в LiveData во вьюмодели экрана
    @Override
    public void onCategoryClicked(String text) {
        mHomeViewModel.setText(text);
    }
}