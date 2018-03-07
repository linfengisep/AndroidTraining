package isep.layouttraining;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class CompoundLayoutActivity extends AppCompatActivity {
    Button btnToRoundConnerView;
    ImageButton missileBtn;
    ImageButton jinsanpangBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compoundlayout);
        Toolbar myToolBar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //initialize the side spinner from code;
        CompoundLayoutView companySpinner;
        companySpinner=findViewById(R.id.sideSpinner_company);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.action_one:
                Toast.makeText(this, "one selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_two:
                Toast.makeText(this, "two selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}

