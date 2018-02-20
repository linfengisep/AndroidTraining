package isep.sunshine;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.net.URL;

import isep.sunshine.data.SunshinePreferences;
import isep.sunshine.utilities.NetworkUtils;
import isep.sunshine.utilities.OpenWeatherJsonUtils;

public class MainActivity extends AppCompatActivity {
    public TextView forecast;
    public TextView errorMessage;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        forecast = (TextView)findViewById(R.id.tv_weather_data);
        errorMessage =(TextView) findViewById(R.id.error_message);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        loadWeatherData();// all begin at the onCreate cycle;
    }
    // a method to call the task to execute;
    private void loadWeatherData(){
        showWeatherDataView();
        String location = SunshinePreferences.getPreferredWeatherLocation(this);
        new FetchWeatherTask().execute(location);
    }

    public void showWeatherDataView(){
        errorMessage.setVisibility(View.INVISIBLE);
        forecast.setVisibility(View.VISIBLE);
    }

    public void showErrorMessage(){
        errorMessage.setVisibility(View.VISIBLE);
        forecast.setVisibility(View.INVISIBLE);
    }

    //a AsyncTask class to get the weather info from internet in the background;

     public class FetchWeatherTask extends AsyncTask<String,Void,String[]>{
         @Override
         public void onPreExecute(){
             super.onPreExecute();
             progressBar.setVisibility(View.VISIBLE);
         }

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
            progressBar.setVisibility(View.INVISIBLE);
            if (weatherData != null) {
                showWeatherDataView();

                for (String weatherString : weatherData) {
                    forecast.append((weatherString) + "\n\n\n");
                }
            }else{
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.forecast,menu);
        return true;//return true to display your menu;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemChosen = item.getItemId();
        if(itemChosen == R.id.action_refresh){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToRecyclerView(View view){
        Intent intent = new Intent(MainActivity.this, RecyclerForecast.class);
        startActivity(intent);
    }

}
