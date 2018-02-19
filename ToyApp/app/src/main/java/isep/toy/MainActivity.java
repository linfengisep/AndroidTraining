package isep.toy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public TextView mToysListTextView;
    public String s;
    public String TAG = this.getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToysListTextView = (TextView) findViewById(R.id.my_toy);
        //mToysListTextView.setMovementMethod(new ScrollingMovementMethod());
        String toyList []=ToyBox.getToyNames();
        s=convertToStringlist(toyList);
        mToysListTextView.setText(s);
    }

    public String convertToStringlist(String[] list){
        StringBuilder toyContainer = new StringBuilder();
        for(String toy:list){
            toyContainer.append(toy+"\n");
        }
        return toyContainer.toString();
    }

    public void goToT0201(View view){
        Intent intent = new Intent(getApplication(),T0201.class);
        startActivity(intent);
    }
}
