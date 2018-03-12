package isep.toy;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;

import java.io.IOException;
import java.net.URL;

/**
 * Created by linfengwang on 17/02/2018.
 */

public class T0201 extends AppCompatActivity {
    EditText mSearchBoxEditText;
    TextView mUrlDisplayTextView;
    TextView mSearchResultsTextView;
    TextView errorMessage;
    ProgressBar progressBar;
    SwitchButton btnIOS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_0201);
        mSearchBoxEditText = (EditText)findViewById(R.id.et_search_box);
        mUrlDisplayTextView = (TextView)findViewById(R.id.display_url);
        mSearchResultsTextView = (TextView)findViewById(R.id.tv_github_search_results_json);
        errorMessage = (TextView)findViewById(R.id.error_message_display);
        progressBar = (ProgressBar)findViewById(R.id.pb_loading_indicator);
        btnIOS = (SwitchButton)findViewById(R.id.switch_btn);

        btnIOS.setThumbDrawableRes(R.drawable.switch_thumb);
        btnIOS.setFadeBack(true);
    }
    private void makeGithubSearchQuery(){
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL gethubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(gethubSearchUrl.toString());
        new GithubQueryTask().execute(gethubSearchUrl);
    }

    public void showJsonDataView(){
        errorMessage.setVisibility(View.INVISIBLE);
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    public void showErrorMessage(){
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    public class GithubQueryTask extends AsyncTask<URL,Void,String>{
        @Override
        public void onPreExecute(){
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public String doInBackground(URL... urls){
            String githubSearchResult = null;
            URL searchUrl=urls[0];
            try{
                githubSearchResult=NetworkUtils.getResponseFromHttpUrl(searchUrl);
            }catch (IOException e){
                e.printStackTrace();
            }
            return githubSearchResult;
        }

        @Override
        public void onPostExecute(String githubSearchResult){
            progressBar.setVisibility(View.INVISIBLE);
            if(githubSearchResult !=null && githubSearchResult !="") {
                mSearchResultsTextView.setText(githubSearchResult);
                showJsonDataView();
                mSearchResultsTextView.setText(githubSearchResult);
            }else{
                showErrorMessage();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int menuItemThatWasSelected = item.getItemId();
        if(menuItemThatWasSelected == R.id.action_search){
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
