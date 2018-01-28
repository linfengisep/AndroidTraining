package isep.layouttraining;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by linfengwang on 28/01/2018.
 */

public class RelativeLayoutActivity extends Activity{
    Button btnToConstraintLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relativelayout);
        btnToConstraintLayout=(Button)findViewById(R.id.btnConstraintLayout);

        btnToConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentConstraintLayout=new Intent(getApplicationContext(),ConstraintLayoutActivity.class);
                startActivity(intentConstraintLayout);
            }
        });
    }
}
