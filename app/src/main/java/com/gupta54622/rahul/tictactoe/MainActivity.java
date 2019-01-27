package com.gupta54622.rahul.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    Button btnwithhuman;
    Button btnwithmobile;
    Button btnwithmobileeasy;
    AdView topAd;
    AdView bottomAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the AdMob app
        MobileAds.initialize(this, getString(R.string.admob_app_id));

        btnwithhuman = findViewById(R.id.btnpalywithhuman);
        btnwithmobile = findViewById(R.id.btnplaywithmobile);
        btnwithmobileeasy = findViewById(R.id.btnplaywithmobileeasy);

        btnwithmobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGame();
            }
        });

        btnwithhuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHumanVsHuman();
            }
        });

        btnwithmobileeasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGameEasy();
            }
        });

        topAd = findViewById(R.id.smart_banner_main_top);
//        topAd.setAdSize(AdSize.SMART_BANNER);
//        topAd.setAdUnitId(getString(R.string.smart_banner_main_top));

        AdRequest adRequest = new AdRequest.Builder().build();

        topAd.loadAd(adRequest);

        bottomAd = findViewById(R.id.smart_banner_main_bottom);
//        bottomAd.setAdSize(AdSize.SMART_BANNER);
//        bottomAd.setAdUnitId(getString(R.string.smart_banner_main_top));

        AdRequest adRequest1 = new AdRequest.Builder().build();

        bottomAd.loadAd(adRequest1);
    }

    private void gotoGameEasy() {
        Intent intent = new Intent(this,GameEasy.class);
        startActivity(intent);

    }

    private void gotoHumanVsHuman(){

        Intent intent = new Intent(this,HumanVsHuman.class);
        startActivity(intent);
    }

    private void gotoGame(){

        Intent intent = new Intent(this,Game.class);
        startActivity(intent);
    }


    @Override
    public void onPause() {
        if (topAd != null) {
            topAd.pause();
        }

        if (bottomAd != null) {
            bottomAd.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (topAd != null) {
            topAd.resume();
        }

        if (bottomAd != null) {
            bottomAd.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (topAd != null) {
            topAd.destroy();
        }

        if (bottomAd != null) {
            bottomAd.destroy();
        }
        super.onDestroy();
    }

}
