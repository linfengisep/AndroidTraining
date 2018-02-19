package isep.sunshine;

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
    // TODO (6) Add a TextView variable for the error message display
    public TextView errorMessage;
    // TODO (16) Add a ProgressBar variable to show and hide the progress bar
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        forecast = (TextView)findViewById(R.id.tv_weather_data);
        // TODO (7) Find the TextView for the error message using findViewById
        errorMessage =(TextView) findViewById(R.id.error_message);
        // TODO (17) Find the ProgressBar using findViewById
        progressBar = (ProgressBar)findViewById(R.id.progress);
        loadWeatherData();// all begin at the onCreate cycle;
    }
    // a method to call the task to execute;
    private void loadWeatherData(){
        // TODO (20) Call showWeatherDataView before executing the AsyncTask
        showWeatherDataView();
        String location = SunshinePreferences.getPreferredWeatherLocation(this);
        new FetchWeatherTask().execute(location);
    }

    // TODO (8) Create a method called showWeatherDataView that will hide the error message and show the weather data
    public void showWeatherDataView(){
        errorMessage.setVisibility(View.INVISIBLE);
        forecast.setVisibility(View.VISIBLE);
    }

    // TODO (9) Create a method called showErrorMessage that will hide the weather data and show the error message
    public void showErrorMessage(){
        errorMessage.setVisibility(View.VISIBLE);
        forecast.setVisibility(View.INVISIBLE);
    }

    //a AsyncTask class to get the weather info from internet in the background;

     public class FetchWeatherTask extends AsyncTask<String,Void,String[]>{
         // TODO (18) Within your AsyncTask, override the method onPreExecute and show the loading indicator
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
            // TODO (19) As soon as the data is finished loading, hide the loading indicator
            progressBar.setVisibility(View.INVISIBLE);
            if (weatherData != null) {
                // TODO (11) If the weather data was not null, make sure the data view is visible
                showWeatherDataView();

                for (String weatherString : weatherData) {
                    forecast.append((weatherString) + "\n\n\n");
                }
            }else{
                showErrorMessage();
            }
            // TODO (10) If the weather data was null, show the error message

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

}
