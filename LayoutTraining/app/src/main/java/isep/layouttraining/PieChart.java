package isep.layouttraining;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by linfengwang on 06/02/2018.
 */

public class PieChart extends View {
    private Boolean mShowText=false;
    private int mColor;
    private int mLabolPosition;
    private String mName;

    public PieChart(Context context, AttributeSet attrs){
        super(context,attrs);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.PieChart,0,0);
        try{
            mShowText = ta.getBoolean(R.styleable.PieChart_showText,false);
            mColor = ta.getColor(R.styleable.PieChart_pieColor,0);
            mLabolPosition = ta.getInteger(R.styleable.PieChart_labelPosition,0);
            mName = ta.getString(R.styleable.PieChart_name);
        } finally {
            ta.recycle();
        }
    }

    public Boolean getmShowText() {
        return mShowText;
    }

    public void setmShowText(Boolean mShowText) {
        this.mShowText = mShowText;
        reDraw();
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
        reDraw();
    }

    public int getmLabolPosition() {
        return mLabolPosition;
    }

    public void setmLabolPosition(int mLabolPosition) {
        this.mLabolPosition = mLabolPosition;
        reDraw();
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
        reDraw();
    }
    private void reDraw() {
        invalidate();
        requestLayout();
    }


}
