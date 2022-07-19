package com.safe.tutorbuddy;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class MRGS_KAMAR extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar);
        WebView mrgsKamarPortal = findViewById(R.id.webviewID);
        mrgsKamarPortal.loadUrl("https://kamarportal.mrgs.school.nz/index.php/home");
    }
}
