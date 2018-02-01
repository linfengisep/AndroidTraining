package isep.layouttraining;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

/**
 * Created by linfengwang on 01/02/2018.
 */

public class RoundCornerDrawableActivity extends Activity {
    ImageView myLogo;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_corner_view);

        myLogo = (ImageView)findViewById(R.id.my_image_view);
        myLogo.setImageResource(R.drawable.mlogo);
    }
}
