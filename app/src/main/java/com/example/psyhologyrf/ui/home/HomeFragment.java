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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.psyhologyrf.R;
import com.example.psyhologyrf.SimpleBot;
import com.example.psyhologyrf.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;

    private TextView sayText;
    private EditText answerText;
    private Button buttonSend;
    SimpleBot simpleBot = new SimpleBot();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        buttonSend  = (Button) root.findViewById(R.id.buttonSend);
        answerText  = (EditText) root.findViewById(R.id.answerText);
        sayText  = (TextView) root.findViewById(R.id.text_home_ya);

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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}