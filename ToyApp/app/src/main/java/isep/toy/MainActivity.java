package isep.toy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public TextView mToysListTextView;
    public String s;
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mLifecycleDisplay;
    private static final ArrayList<String> mLifecycleCallbacks = new ArrayList<>();
    /*
     * This constant String will be used to store the content of the TextView used to display the
     * list of callbacks. The reason we are storing the contents of the TextView is so that you can
     * see the entire set of callbacks as they are called.
     */
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";

    /* Constant values for the names of each respective lifecycle callback */
    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToysListTextView = (TextView) findViewById(R.id.my_toy);

        mLifecycleDisplay = (TextView) findViewById(R.id.lifecycle_part);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                String allPreviousLifecycleCallbacks = savedInstanceState
                        .getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
                mLifecycleDisplay.setText(allPreviousLifecycleCallbacks);
            }
        }
        for (int i = mLifecycleCallbacks.size() - 1; i >= 0; i--) {
            mLifecycleDisplay.append(mLifecycleCallbacks.get(i) + "\n");
        }

        mLifecycleCallbacks.clear();

        logAndAppend(ON_CREATE);

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

    @Override
    protected void onStart() {
        super.onStart();

        logAndAppend(ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();

        logAndAppend(ON_RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        logAndAppend(ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // COMPLETED (2) Add the ON_STOP String to the front of mLifecycleCallbacks
        /*
         * Since any updates to the UI we make after onSaveInstanceState (onStop, onDestroy, etc),
         * we use an ArrayList to track if these lifecycle events had occurred. If any of them have
         * occurred, we append their respective name to the TextView.
         */
        mLifecycleCallbacks.add(0, ON_STOP);

        logAndAppend(ON_STOP);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        logAndAppend(ON_RESTART);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLifecycleCallbacks.add(0, ON_DESTROY);

        logAndAppend(ON_DESTROY);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);
        String lifecycleDisplayTextViewContents = mLifecycleDisplay.getText().toString();
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, lifecycleDisplayTextViewContents);
    }


    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);

        mLifecycleDisplay.append(lifecycleEvent + "\n");
    }

    public void resetLifecycleDisplay(View view) {
        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
    }


}
