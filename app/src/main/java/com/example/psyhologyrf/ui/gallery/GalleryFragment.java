package com.example.psyhologyrf.ui.gallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.psyhologyrf.R;
import com.example.psyhologyrf.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private WebView myweb;
    private ProgressBar progressBar;
    String url = "https://психо-анализ.рф/stati2";



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);



      //  myweb = (WebView) myweb.findViewById(R.id.myweb);
       // myweb.loadUrl(url);




        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        myweb = root.findViewById(R.id.myweb);
        progressBar = root.findViewById(R.id.progressBar);

        binding.myweb.loadUrl(url);
        binding.myweb.setWebViewClient(new WebViewClient());

     //   galleryViewModel.getText().observe(getViewLifecycleOwner(), WebView::getUrl(url));
      //  galleryViewModel.getText().observeForever(getViewLifecycleOwner(), WebView::getWebViewClient);
      //  galleryViewModel.getText().observeForever(WebView.ge, WebView::getWebViewClient);


        WebSettings webSettings = binding.myweb.getSettings();
        myweb.getSettings().setJavaScriptEnabled(true);
        //webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowContentAccess(true);

        myweb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Show progress bar
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Hide progress bar
                progressBar.setVisibility(View.GONE);
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