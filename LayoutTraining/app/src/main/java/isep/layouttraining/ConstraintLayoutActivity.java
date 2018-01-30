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

public class ConstraintLayoutActivity extends Activity {
    Button btnCompoundView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraintlayout);
        btnCompoundView=findViewById(R.id.to_compound_view);
        btnCompoundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCompoundView=new Intent(ConstraintLayoutActivity.this,CompoundLayoutActivity.class);
                startActivity(intentCompoundView);
            }
        });
    }
}
