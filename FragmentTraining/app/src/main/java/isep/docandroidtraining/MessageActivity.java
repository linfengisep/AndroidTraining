package isep.docandroidtraining;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import isep.docandroidtraing.R;

/**
 * Created by linfengwang on 27/01/2018.
 */

public class MessageActivity extends Activity {
    TextView viewMessage;
    Button btnPassToFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent intent=getIntent();
        String receivingMessage=intent.getStringExtra(MainActivity.TAG);

        viewMessage=findViewById(R.id.viewMessage);
        viewMessage.setText(receivingMessage);

        btnPassToFragment =(Button)findViewById(R.id.btnPassToFragment);
        btnPassToFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
