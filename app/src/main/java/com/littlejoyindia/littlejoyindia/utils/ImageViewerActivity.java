package com.littlejoyindia.littlejoyindia.utils;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.littlejoyindia.littlejoyindia.R;

public class ImageViewerActivity extends AppCompatActivity {

    String imageUrl;
    ImageButton imageBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_viewer_activity);

        PhotoView photoView = (PhotoView) findViewById(R.id.iv_photo);
        imageBack = (ImageButton) findViewById(R.id.imageBack);

        imageUrl = getIntent().getStringExtra("imageUrl");

        Glide.with(this).load(imageUrl).into(photoView);

        imageBack.setOnClickListener(view -> {
            onBackPressed();
            ;
        });

    }
}
