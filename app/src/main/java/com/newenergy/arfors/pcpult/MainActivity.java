package com.newenergy.arfors.pcpult;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton ibtnMute;
    Device pc1 = new Device(50, 100, 0, 50, 100, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar skbVolume = findViewById(R.id.skbVolume);
        skbVolume.setMax(pc1.getMaxVolume());
        skbVolume.setMin(pc1.getMinVolume());
        skbVolume.setProgress(pc1.getVolume());

        ibtnMute = findViewById(R.id.ibtnMute);
        ibtnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 30);
                t.show();

                if (pc1.isFlMute()) {
                    ibtnMute.setImageResource(R.drawable.baseline_volume_off_24);
                    pc1.setFlMute(false);
                }
                else {
                    ibtnMute.setImageResource(R.drawable.baseline_volume_up_24);
                    pc1.setFlMute(true);
                }
            }
        });
        skbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}