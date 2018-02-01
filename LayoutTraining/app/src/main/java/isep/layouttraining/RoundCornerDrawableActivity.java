package isep.layouttraining;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by linfengwang on 01/02/2018.
 */

public class RoundCornerDrawableActivity extends Activity {
    ImageView myLogo;
    ImageButton imageButton;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_corner_view);

        //shape drawable;
        myLogo = (ImageView)findViewById(R.id.my_image_view);
        myLogo.setImageResource(R.drawable.mlogo);

        //transition drawable;
        /*
        imageButton = (ImageButton)findViewById(R.id.transition_btn);
        TransitionDrawable drawable = (TransitionDrawable)imageButton.getDrawable();
        drawable.startTransition(500);
        */
        //way 2, use java to get a transition effect directly;
        Drawable background[]=new Drawable[2];
        background[0] =getResources().getDrawable(R.drawable.on);
        background[1] =getResources().getDrawable(R.drawable.off);

        TransitionDrawable crossfader= new TransitionDrawable(background);

        ImageButton imagebtn=(ImageButton)findViewById(R.id.transition_btn);
        imagebtn.setImageDrawable(crossfader);
        crossfader.startTransition(2000);
    }
}

























