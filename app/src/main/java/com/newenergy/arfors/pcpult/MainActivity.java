package com.newenergy.arfors.pcpult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    ImageButton ibtnMute;
    ImageButton ibtnPlayPause;
    ImageButton ibtnPower;
    SeekBar skbProgress;
    SeekBar skbVolume;

    SeekBar skbMouseX;
    SeekBar skbMouseY;

    View vwTouchPad;
    private UDPClient udpClient;
    private GestureDetector gd;
    Device pc1 = new Device(50, 100, 0, 50, 100, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skbVolume = findViewById(R.id.skbVolume);
        skbProgress = findViewById(R.id.skbProgress);
        ibtnPower = findViewById(R.id.ibtnPower);
        ibtnPlayPause = findViewById(R.id.ibtnPlayPause);
        ibtnMute = findViewById(R.id.ibtnMute);
        skbMouseY = findViewById(R.id.skbMouseY);
        skbMouseX = findViewById(R.id.skbMouseX);
        vwTouchPad = findViewById(R.id.vwTouchPad);
        gd = new GestureDetector(this, this);
        gd.setOnDoubleTapListener(this);
        udpClient = new UDPClient("192.168.88.117", 12345);
        skbVolume.setMax(pc1.getMaxVolume());
        skbMouseY.setMax(100);
        skbMouseX.setMax(100);
//        skbVolume.setMin(pc1.getMinVolume());
        skbVolume.setProgress(pc1.getVolume());
        ibtnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast t = Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT);
                // t.setGravity(Gravity.CENTER, 0, 30);
                // t.show();

                if (pc1.isFlMute()) {
                    ibtnMute.setImageResource(R.drawable.baseline_volume_off_24);
                    skbVolume.setEnabled(false);
                    pc1.setFlMute(false);
                    udpClient.sendDataAsync("mute:1");
                }
                else {
                    ibtnMute.setImageResource(R.drawable.baseline_volume_up_24);
                    pc1.setFlMute(true);
                    skbVolume.setEnabled(false);
                    udpClient.sendDataAsync("mute:0");
                }
            }
        });
        skbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("Volume", String.valueOf(progress));
                udpClient.sendDataAsync("volume:" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("Volume", String.valueOf("Test"));
            }
        });
        ibtnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pc1.isFlPause()) {
                    ibtnPlayPause.setImageResource(R.drawable.baseline_play_arrow_24);
                    pc1.setFlPause(false);
                }
                else {
                    ibtnPlayPause.setImageResource(R.drawable.baseline_pause_24);
                    pc1.setFlPause(true);
                }
            }
        });

        skbMouseX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                udpClient.sendDataAsync("mouse:"+i+","+skbMouseY.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        skbMouseY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                udpClient.sendDataAsync("mouse:"+skbMouseX.getProgress() + "," + (100-i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        vwTouchPad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            int width = vwTouchPad.getWidth();
            int hight = vwTouchPad.getHeight();

            int x = (int) (event.getX() / width * 100);
            int y = (int) (event.getY() / hight * 100);

              if (x < 0) {
                  x = 0;
              } else if (x > 100){
                 x = 100;
                }
              if (y < 0) {
                  y = 0;
              } else if (y > 100) {
                  y = 100;
                }
                udpClient.sendDataAsync("mouse:" + x + ","+y);
                Log.d("X,Y", "X:"+x + " " +"Y:"+y);

                gd.onTouchEvent(event);
              return true;
            }
        });
    }

    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
        Log.d("Gesture", "duobletap");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}