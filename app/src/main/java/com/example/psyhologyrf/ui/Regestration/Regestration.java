package com.example.psyhologyrf.ui.Regestration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.psyhologyrf.R;
import com.example.psyhologyrf.databinding.FragmentRegestrationBinding;
import com.example.psyhologyrf.databinding.FragmentSlideshowBinding;
import com.example.psyhologyrf.ui.slideshow.SlideshowViewModel;

public class Regestration extends Fragment {

    private FragmentRegestrationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegestrationModel regestrationModel =
                new ViewModelProvider(this).get(RegestrationModel.class);

        binding = FragmentRegestrationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
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