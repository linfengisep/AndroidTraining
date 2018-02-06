package isep.layouttraining;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



/**
 * Created by linfengwang on 01/02/2018.
 */

public class RoundCornerDrawableActivity extends Activity {
    ImageView myLogo;
    ImageButton imageButton;
    ToggleButton btnToggle;
    TextView actionText;
    AudioManager audioManager;

    Switch switchMedia;
    Switch switchWifi;
    Button btnResult;
    TextView show_area;

    Boolean is_enabled=true;
    Button enable_disable_switch;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_corner_view);

        //shape drawable;
        myLogo = findViewById(R.id.my_image_view);
        myLogo.setImageResource(R.drawable.mlogo);

        //for switch
        actionText = findViewById(R.id.btn_result);
        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        //transition drawable;
        /*
        imageButton = (ImageButton)findViewById(R.id.transition_btn);
        TransitionDrawable drawable = (TransitionDrawable)imageButton.getDrawable();
        drawable.startTransition(500);
        */
        //way 2, use java to get a transition effect directly;
        Drawable background[] = new Drawable[2];
        background[0] = getResources().getDrawable(R.drawable.off);
        background[1] = getResources().getDrawable(R.drawable.on);

        TransitionDrawable crossfader = new TransitionDrawable(background);
        //transition
        imageButton = findViewById(R.id.transition_btn);
        imageButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageButton.setImageDrawable(crossfader);
        crossfader.startTransition(2000);

        //toggle activity;
        btnToggle = findViewById(R.id.btn_toggle);
        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnToggle.isChecked()) {
                    Toast.makeText(RoundCornerDrawableActivity.this, "Toggle button is on", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RoundCornerDrawableActivity.this, "Toggle button is off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //get the switch
        switchMedia=findViewById(R.id.switch_media);
        btnResult=findViewById(R.id.btn_result);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String statusSwitchMedia,statusSwitchWifi;
                if(switchMedia.isChecked()){
                    statusSwitchMedia=switchMedia.getTextOn().toString();
                }else{
                    statusSwitchMedia=switchMedia.getTextOff().toString();
                }
                Toast.makeText(RoundCornerDrawableActivity.this,"Spot state: "
                        +statusSwitchMedia,Toast.LENGTH_SHORT).show();
            }
        });

        //drag;
        show_area=findViewById(R.id.show_area);
        switchMedia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(buttonView.isChecked()){
                    show_area.setText("Spot :ON by drag thumb");
                    //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,8,0);
                }else{
                    show_area.setText("Spot :OFF by drag thumb");
                    //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,0,0);
                }
            }
        });
    }

    //click
    public void onSwitchClicked(View view){
        switch (view.getId()){
            case R.id.switch_media:
                if(switchMedia.isChecked()){
                    show_area.setText("Spot :ON, by clicking");
                    //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,8,0);
                }else{
                    show_area.setText("Spot :Off, by clicking");
                    //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,0,0);
                }
                break;
        }
    }
}

























