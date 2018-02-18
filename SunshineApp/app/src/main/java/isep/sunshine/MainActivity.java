package isep.sunshine;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import java.net.URL;

import isep.sunshine.data.SunshinePreferences;
import isep.sunshine.utilities.NetworkUtils;
import isep.sunshine.utilities.OpenWeatherJsonUtils;

public class MainActivity extends AppCompatActivity {
    public TextView forecast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        forecast = (TextView)findViewById(R.id.tv_weather_data);
        loadWeatherData();
    }

    private void loadWeatherData() {
        String location = SunshinePreferences.getPreferredWeatherLocation(this);
        new FetchWeatherTask().execute(location);
    }

     public class FetchWeatherTask extends AsyncTask<String,Void,String[]>{
        @Override
        public String[] doInBackground(String... params){
            if(params.length==0){
                return null;
            }
            String location = params[0];
            URL weatherRequestUrl = NetworkUtils.buildUrl(location);
            try {
                String jsonWeatherReponse = NetworkUtils.getResponseFromHttpUrl(
                        weatherRequestUrl);
                String[] simpleJsonWeatherData = OpenWeatherJsonUtils.getSimpleWeatherStringsFromJson(
                        MainActivity.this, jsonWeatherReponse);
                return simpleJsonWeatherData;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void onPostExecute(String[] weatherData){
            if (weatherData != null) {
                for (String weatherString : weatherData) {
                    forecast.append((weatherString) + "\n\n\n");
                }
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.forecast,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            forecast.setText("");
            loadWeatherData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
