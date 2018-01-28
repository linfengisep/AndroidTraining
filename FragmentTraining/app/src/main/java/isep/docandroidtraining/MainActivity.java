package isep.docandroidtraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import isep.docandroidtraing.R;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btnSendMessage;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.textMessage);
        btnSendMessage=(Button)findViewById(R.id.btnSendMessage);

        final String message=editText.getText().toString();
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"sending message in onClick");
                String message=editText.getText().toString();
                Intent messageActivity = new Intent(MainActivity.this,MessageActivity.class);
                messageActivity.putExtra(TAG,message);
                Log.d(TAG,message);
                Toast.makeText(MainActivity.this,"Sending message...",Toast.LENGTH_SHORT).show();
                startActivity(messageActivity);
            }
        });
    }
}
