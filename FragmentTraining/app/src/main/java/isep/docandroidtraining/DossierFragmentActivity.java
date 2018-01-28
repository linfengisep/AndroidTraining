package isep.docandroidtraining;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import isep.docandroidtraing.R;

/**
 * Created by linfengwang on 27/01/2018.
 */

public class DossierFragmentActivity extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition=-1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        //inflate the layout for this fragment;
        return inflater.inflate(R.layout.activity_dossier,container,false);
    }

    @Override
    public void onStart(){
        super.onStart();
        Bundle args=getArguments();
        if(args != null){
            updateDossierView(args.getInt(ARG_POSITION));
        }else{
            updateDossierView(mCurrentPosition);
        }
    }

    public void updateDossierView(int position){
        TextView dossier=(TextView)getActivity().findViewById(R.id.dossier);
        dossier.setText(Eleve.Dossier[position]);
        mCurrentPosition=position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}

