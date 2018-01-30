package isep.layouttraining;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by linfengwang on 30/01/2018.
 */
//if we want to have some logic inside the view,just to extend one of
// the built-in layouts and inflate custom layout during initialization

public class CompoundLayoutView extends LinearLayout {
    private TextView value;
    private Button arrowLeft;
    private Button arrowRight;

    private CharSequence[] mSpinnerValues = null;
    private int mSelectedIndex = -1;

    public CompoundLayoutView(Context context){
        super(context);
        init(context);
    }
    public CompoundLayoutView(Context context, AttributeSet attrs){
        super(context,attrs);
        //to get the value of our custom values attribute, call obtainStyledAttributes method of AttributeSet object first,
        //it returns a typeArray object;
        TypedArray typedArray;
        typedArray=context.obtainStyledAttributes(attrs,R.styleable.CompoundLayoutView);
        mSpinnerValues=typedArray.getTextArray(R.styleable.CompoundLayoutView_values);
        typedArray.recycle();//it is shared resource and must be recycled after use.

        init(context);
    }
    public CompoundLayoutView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        TypedArray typedArray;
        typedArray=context.obtainStyledAttributes(attrs,R.styleable.CompoundLayoutView);
        mSpinnerValues=typedArray.getTextArray(R.styleable.CompoundLayoutView_values);
        typedArray.recycle();

        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.compoundlayout_view,this);
    }
    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        arrowLeft=(Button)findViewById(R.id.arrow_left);
        arrowLeft.setBackgroundResource(R.drawable.arrow_left);

        arrowLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSelectedIndex>0){
                    int newSelectedIndex=mSelectedIndex-1;
                    setSelectedIndex(newSelectedIndex);
                }
            }
        });

        arrowRight=(Button)findViewById(R.id.arrow_right);
        arrowRight.setBackgroundResource(R.drawable.arrow_right);

        arrowRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSelectedIndex<mSpinnerValues.length-1||mSpinnerValues !=null){
                    int newSelectedIndex=mSelectedIndex+1;
                    setSelectedIndex(newSelectedIndex);
                }
            }
        });
        //select the first value be default;
        setSelectedIndex(0);
    }

    public void setValues(CharSequence[] values) {
        mSpinnerValues = values;

        // Select the first item of the string array by default since 
        // the list of value has changed.
        setSelectedIndex(0);
    }

    public void setSelectedIndex(int index) {
        if(mSpinnerValues==null ||mSpinnerValues.length==0)
            return;
        if(index<0||index>mSpinnerValues.length)
            return;

        mSelectedIndex=index;
        TextView currentView;
        currentView=findViewById(R.id.value);
        currentView.setText(mSpinnerValues[index]);
        //if the first value is shown, hide the previous button;
        if(mSelectedIndex==0){
            arrowLeft.setVisibility(INVISIBLE);
        }else{
            arrowLeft.setVisibility(VISIBLE);
        }

        //if the last value is shown, hide the next button;
        if(mSelectedIndex==mSpinnerValues.length-1){
            arrowRight.setVisibility(INVISIBLE);
        }else{
            arrowRight.setVisibility(VISIBLE);
        }
    }

    //return the selected value of the spinner;
    public CharSequence getSelectedValue(){
        if(mSpinnerValues==null||mSpinnerValues.length==0)
            return "";
        if(mSelectedIndex<0||mSelectedIndex>=mSpinnerValues.length)
            return "";

        return mSpinnerValues[mSelectedIndex];
    }

    public int getSelectedIndex(){
        return mSelectedIndex;
    }
}
