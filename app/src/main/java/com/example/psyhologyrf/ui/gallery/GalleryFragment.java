package com.example.psyhologyrf.ui.gallery;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
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
    private static final TimeInterpolator GAUGE_ANIMATION_INTERPOLATOR = new DecelerateInterpolator(2);
    private static final int MAX_LEVEL = 100;
    private static final long GAUGE_ANIMATION_DURATION = 2000;
    private ProgressBar progressBar;
    String url = "https://психо-анализ.рф/статьи-2";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);




        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        myweb = root.findViewById(R.id.myweb);
        progressBar = root.findViewById(R.id.progressBar);
      //  progressBar.setProgress(50);
     //   Drawable draw=getResources().getDrawable(R.drawable.custom_progressbar);

        binding.myweb.loadUrl(url);
        binding.myweb.setWebViewClient(new WebViewClient());


        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", 0, MAX_LEVEL);
        animator.setInterpolator(GAUGE_ANIMATION_INTERPOLATOR);
        animator.setDuration(GAUGE_ANIMATION_DURATION);
        animator.start();
     //   galleryViewModel.getText().observe(getViewLifecycleOwner(), WebView::getUrl(url));
      //  galleryViewModel.getText().observeForever(getViewLifecycleOwner(), WebView::getWebViewClient);
      //  galleryViewModel.getText().observeForever(WebView.ge, WebView::getWebViewClient);


        WebSettings webSettings = binding.myweb.getSettings();
        myweb.getSettings().setJavaScriptEnabled(true);
        //webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowContentAccess(true);
     //   progressBar.setProgressDrawable(draw);


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