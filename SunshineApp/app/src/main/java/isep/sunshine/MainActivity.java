package isep.sunshine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

// TODO (1) Create a field to store the weather display TextView
// TODO (2) Use findViewById to get a reference to the weather display TextView
// TODO (3) Create an array of Strings that contain fake weather data
// TODO (4) Append each String from the fake weather data array to the TextView
public class MainActivity extends AppCompatActivity {
    public TextView forecast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        forecast = (TextView)findViewById(R.id.tv_weather_data);
        String [] weatherData = new String[]{"Raining","strom","snow","smog"};
        for(String weather:weatherData){
            forecast.append(weather+"\n\n");
        }
    }
}
