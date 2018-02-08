package isep.layouttraining;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class PieChart extends View {
    public int labolPosition;
    public String mName;
    public TextView textView;
    public PieChart(Context context){
        super(context);
    }

    public PieChart(Context context,AttributeSet attrs){
        super(context,attrs);
        init(attrs);
    }

    public PieChart(Context context, AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs){
        //inflate(getContext(),R.layout.pie_chart_view,this);/////???
        textView = findViewById(R.id.pie_chart);

        TypedArray ta = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.PieChart,0,0);
        try{
            labolPosition = ta.getInteger(R.styleable.PieChart_labelPosition,0);
            mName = ta.getString(R.styleable.PieChart_name);

            textView.setText(mName);
            if(labolPosition==1){
                textView.setPadding(50,0,0,0);
            }else{
                textView.setPadding(0,0,50,0);
            }
            reDraw();
        } finally {
            ta.recycle();
        }
    }
    private void reDraw() {
        invalidate();
        requestLayout();
    }
}
