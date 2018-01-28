package isep.layouttraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRelative=(Button)findViewById(R.id.btnFromLinearLayout);

        btnRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRelativelayout=new Intent(getApplicationContext(), RelativeLayoutActivity.class);
                startActivity(intentRelativelayout);
            }
        });
    }
}
