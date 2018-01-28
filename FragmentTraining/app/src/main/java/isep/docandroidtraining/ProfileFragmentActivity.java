package isep.docandroidtraining;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import isep.docandroidtraing.R;

/**
 * Created by linfengwang on 27/01/2018.
 */

public class ProfileFragmentActivity extends ListFragment {
    OnProfileSelectedListener profileSelectedCallback;

    //the container activity must implement this interface, so the frag can deliver message;
    public interface OnProfileSelectedListener{
    //called by ProfileFragmentActivity when a list item is selected;
        public void onDossierSelected(int position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // We need to use a different list item layout for devices older than Honeycomb
    int layout = android.R.layout.activity_list_item;

    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(),layout, Eleve.Profile);
    setListAdapter(arrayAdapter);
    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.dossierSection) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            profileSelectedCallback = (OnProfileSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        profileSelectedCallback.onDossierSelected(position);

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }

}
