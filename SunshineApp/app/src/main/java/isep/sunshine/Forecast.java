package isep.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Forecast extends AppCompatActivity implements ForecastAdapter.ListItemClickListener{
    //for an activity, when using RecyclerView, it needs Adapter,RecyclerView and LayoutManager;
    //and perhaps that a dataset(like boolist here) is necessary;

    private ForecastAdapter mForecastAdapter;
    private RecyclerView mRecyclerForcast;
    public RecyclerView.LayoutManager layoutManager;
    public List<String> bookList = new ArrayList<String>();
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //this layout must contain a field named:android.support.v7.widget.RecyclerView
        setContentView(R.layout.activity_forecast_recycler);

        prepareData();
        //get reference for RecyclerView;
        mRecyclerForcast = (RecyclerView) findViewById(R.id.recycler_forecast);
        mRecyclerForcast.setHasFixedSize(true);
        //using specific LayoutManager to fill the view for RecyclerView;
        layoutManager = new LinearLayoutManager(this);
        mRecyclerForcast.setLayoutManager(layoutManager);
        //at last create your Adapter(passing data to adapter constructor why not)
        mForecastAdapter = new ForecastAdapter(bookList,this);
        mRecyclerForcast.setAdapter(mForecastAdapter);
    }

    @Override
    public void onItemClickListener(int clickedItemIndex){
        if(mToast !=null){
            mToast.cancel();
        }

        String message = "Item # "+clickedItemIndex+" clicked.";
        mToast = Toast.makeText(this,message,Toast.LENGTH_SHORT);
        mToast.show();
    }

    public void prepareData(){
        String book1 = "mindset";
        String book2 = "hacker painter";
        String book3="influence";
        String book4 = "how to read a book";
        String book5 = "homo sapiens";
        String book6 = "Alchemist";
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);

    }
}


