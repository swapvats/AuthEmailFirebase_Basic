package com.example.swapnil.authemailfirebase_basic;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.SeekBar;

import com.github.florent37.longshadow.LongShadow;
import com.xw.repo.BubbleSeekBar;

public class UserInformationActivity extends AppCompatActivity {

    LongShadow longShadow;
    SeekBar seekBar;
    BubbleSeekBar bubbleSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        longShadow = findViewById(R.id.longShadow);
        seekBar = findViewById(R.id.seekBar);
        bubbleSeekBar = findViewById(R.id.bubbleBar);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(0);
        }
        seekBar.setMax(360);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                longShadow.setShadowAngle(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int i, float v, boolean b) {
                longShadow.setShadowAngle(i);
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int i, float v) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int i, float v, boolean b) {

            }
        });

        //TODO: CHECK OUT SPARSEARRAY :)
        bubbleSeekBar.setCustomSectionTextArray(new BubbleSeekBar.CustomSectionTextArray() {
            @NonNull
            @Override
            public SparseArray<String> onCustomize(int i, @NonNull SparseArray<String> sparseArray) {
                sparseArray.clear();
                sparseArray.put(1,"good");
                sparseArray.put(2,"nice");
                sparseArray.put(3,"cool");
                sparseArray.put(4,"fuck");

                return sparseArray;
            }
        });



    }


}
