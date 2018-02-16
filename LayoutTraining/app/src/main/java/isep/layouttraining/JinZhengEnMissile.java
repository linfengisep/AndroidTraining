package isep.layouttraining;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Toast;

/**
 * Created by linfengwang on 10/02/2018.
 */

public class JinZhengEnMissile extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alerdialog = new AlertDialog.Builder(getActivity());
        //get the inflater;
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //inflate and set the layout for the dialog;
        alerdialog.setView(inflater.inflate(R.layout.activity_jinzhengen,null))
                .setPositiveButton("coorperate", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),"you have no choice",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("flight",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int id){
                        JinZhengEnMissile.this.getDialog().cancel();
                    }
                });
        return alerdialog.create();
    }
}





























