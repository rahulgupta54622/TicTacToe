package com.gupta54622.rahul.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class HumanVsHuman extends AppCompatActivity {
    //  0 chance of o , 1 chance of x , 9 o's identifier ,10 x's identifier
    TextView staus;
    Button reStart;
    GridLayout gridLayout;
    int chance = 0;
    int count = 0;
    MediaPlayer xTapSound;
    MediaPlayer oTapSound;
    Animation fadeIn;
    Animation fadeOut;
    CardView cardView;
    int[][] wingPos = {{0,1,2} , {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2, 4 , 6}};

    AdView bottomAd;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_vs_human);


        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_game));

        AdRequest adRequestintr = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequestintr);

        staus = findViewById(R.id.tvGameStatus);
        gridLayout = findViewById(R.id.gridLayout);
        reStart = findViewById(R.id.buttonRestartGame);
        cardView = findViewById(R.id.cardView);


        bottomAd = findViewById(R.id.smart_banner_hvh_bottom);
//        bottomAd.setAdSize(AdSize.SMART_BANNER);
//        bottomAd.setAdUnitId(getString(R.string.smart_banner_main_top));

        AdRequest adRequest1 = new AdRequest.Builder().build();

        bottomAd.loadAd(adRequest1);
        //sounds
        xTapSound = MediaPlayer.create(this,R.raw.xtapsound);
        oTapSound = MediaPlayer.create(this,R.raw.otapsound);

        xTapSound.setVolume(1,1);
        oTapSound.setVolume(1,1);

        //animations
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);

        cardView.setAnimation(fadeIn);
    }

    public void onClickImageView(View view) {
        count++;
        view.startAnimation(fadeIn);
        int preChance = chance;
        if(chance == 0) {
            try {
                oTapSound.start();
            } catch (Exception e){
                System.out.print("MediaPlayerStartError!!");
            }

            view.setBackgroundResource(R.drawable.o);
            staus.setText("X's chance");
            chance = 1;
            view.setEnabled(false);
        }

        else {
            view.startAnimation(fadeIn);
            try {
                xTapSound.start();
            } catch (Exception e){
                System.out.print("MediaPlayerStartError!!");
            }
            view.setBackgroundResource(R.drawable.x);
            chance = 0;
            staus.setText("O's chance");
            view.setEnabled(false);
        }

        int tag = Integer.valueOf(view.getTag().toString());
        System.out.println(tag +" " + preChance);

        for (int i = 0 ; i < 8 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                if(wingPos[i][j] == tag && preChance == 0){
                    wingPos[i][j] = 9;
                }

                if(wingPos[i][j] == tag && preChance == 1){
                    wingPos[i][j] = 10;
                }
            }
        }
        //checking winner
        for (int i = 0 ; i < 8 ; i++){
            if(wingPos[i][0]  == 9 && wingPos[i][1]  == 9 && wingPos[i][2]  == 9 ) {
                count = 0;
                staus.setText("O won the match!!!!!");
                for(int k = 0 ; k < 9 ; k++)
                    gridLayout.getChildAt(k).setEnabled(false);
                ShowAdd();
                reStart.setVisibility(View.VISIBLE);
            }

            if(wingPos[i][0]  == 10 && wingPos[i][1]  == 10 && wingPos[i][2]  == 10 ) {
                count = 0;
                staus.setText("X won the match!!!!!");
                for(int k = 0 ; k < 9 ; k++)
                    gridLayout.getChildAt(k).setEnabled(false);
                ShowAdd();
                reStart.setVisibility(View.VISIBLE);
            }
        }

        if(count == 9)
        {   reStart.setVisibility(View.VISIBLE);
            staus.setText("It's a draw!!");
            ShowAdd();
        }
    }

    public void onClickRestart(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }


    private void ShowAdd(){
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }

}
