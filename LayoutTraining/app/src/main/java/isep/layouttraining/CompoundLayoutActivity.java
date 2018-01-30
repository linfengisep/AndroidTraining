package isep.layouttraining;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by linfengwang on 30/01/2018.
 */

public class CompoundLayoutActivity extends Activity {
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
    }
}

