package isep.docandroidtraining;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import isep.docandroidtraing.R;

import static isep.docandroidtraing.R.id.fragment_container;

/**
 * Created by linfengwang on 27/01/2018.
 */

public class MainFragment extends FragmentActivity implements ProfileFragmentActivity.OnProfileSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv);

        //check the activity is using the layout version with fragment_container layout;
        if(findViewById(fragment_container)!=null) {
            //if we are being restored from a previous state, do not need do anything
            if (savedInstanceState != null) {
                return;
            }

            //or, create a new fragment to be placed in the activity layout
            ProfileFragmentActivity firstFragment = new ProfileFragmentActivity();
            //in case, this activity was started with special instruction from an intent, pass its extras as argument
            firstFragment.setArguments(getIntent().getExtras());

            //add the fragment to the fragment-container layout;
            getSupportFragmentManager().beginTransaction()
                    .add(fragment_container, firstFragment).commit();
        }
    }

    //
    public void onDossierSelected(int position){

        //capture the dossier fragment from activity layout;
        DossierFragmentActivity dossierFragment=(DossierFragmentActivity)
                getSupportFragmentManager().findFragmentById(R.id.dossierSection);
        if(dossierFragment!=null){
            // Call a method in the ArticleFragment to update its content
            dossierFragment.updateDossierView(position);
        }else{

            DossierFragmentActivity secondFragment=new DossierFragmentActivity();

            Bundle args = new Bundle();
            args.putInt(DossierFragmentActivity.ARG_POSITION,position);

            secondFragment.setArguments(args);

            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            //replace whatever is in the fragment_container with this one;
            transaction.replace(fragment_container,secondFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
