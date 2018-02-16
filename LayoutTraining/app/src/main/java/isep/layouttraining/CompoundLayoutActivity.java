package isep.layouttraining;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by linfengwang on 30/01/2018.
 */

public class CompoundLayoutActivity extends Activity {
    Button btnToRoundConnerView;
    ImageButton missileBtn;
    ImageButton jinsanpangBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compoundlayout);

        //initialize the side spinner from code;
        CompoundLayoutView companySpinner;
        companySpinner=(CompoundLayoutView)findViewById(R.id.sideSpinner_company);

        CharSequence company[]={"Apple","Facebook","Heylo","IBM","Tweeter","Oracle","Alibaba","Tasla","Whatup"};

        companySpinner.setValues(company);
        companySpinner.setSelectedIndex(1);

        btnToRoundConnerView=findViewById(R.id.round_corner);
        btnToRoundConnerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent roundCornerView= new Intent(CompoundLayoutActivity.this,RoundCornerDrawableActivity.class);
                startActivity(roundCornerView);
            }
        });

        missileBtn = findViewById(R.id.missile_button);
        missileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fire a dialog instance;
                FireMissileDialog fireMissile = new FireMissileDialog(CompoundLayoutActivity.this);
                fireMissile.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                fireMissile.show();
            }
        });

        jinsanpangBtn=findViewById(R.id.jin);
        jinsanpangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JinZhengEnMissile affaireEvent = new JinZhengEnMissile();
                affaireEvent.show(getFragmentManager(),"show");
            }
        });
    }
}

