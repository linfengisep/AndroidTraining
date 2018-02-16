package isep.toy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public TextView mToysListTextView;
    public String s;
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
}
