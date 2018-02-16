package isep.layouttraining;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class FireMissileDialog extends Dialog implements android.view.View.OnClickListener{
    public Activity activity;
    public Button btnYes,btnNo;

    public FireMissileDialog(Activity fireEvent){
        super(fireEvent);
        this.activity=fireEvent;
    }
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fire_missile);
        btnNo = findViewById(R.id.btn_cancel);
        btnYes = findViewById(R.id.btn_fire);

        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_fire:
                activity.finish();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

}

/*
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
public class FireMissileDialog extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_missile);
        open();
    }

    public void open(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("are you sure?");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(FireMissileDialog.this,"you confirmed yes",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
*/



