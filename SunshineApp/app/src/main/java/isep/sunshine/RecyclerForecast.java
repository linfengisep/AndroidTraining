package isep.sunshine;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by linfengwang on 20/02/2018.
 */

public class RecyclerForecast extends AppCompatActivity implements ForecastAdapter{
    private static final int number=100;
    private ForecastAdapter mForecastAdapter;
    private RecyclerView mRecyclerForcast;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_forecast);

        mRecyclerForcast = (RecyclerView) findViewById(R.id.recycler_forecast);
    }
}


