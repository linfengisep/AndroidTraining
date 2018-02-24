package isep.sunshine;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {
    public List<String> bookOfLuyanLinfeng = new ArrayList<String>();
    public ListItemClickListener listItemClickListener;

    //item click listener;
    public interface ListItemClickListener{
        void onItemClickListener(int clickedItemIndex);
    }

    //a constructor;
    public ForecastAdapter(List<String> list,ListItemClickListener listener){
        this.bookOfLuyanLinfeng = list;
        this.listItemClickListener = listener;
    }
    /*
    *  @param parent The ViewGroup that these ViewHolders are contained within.
    *  @return A new ForecastViewHolder that holds the View for each list item
    */
    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //get parent context;
        Context context = parent.getContext();
        //create a LayoutInflater based on the parent context;
        LayoutInflater inflater = LayoutInflater.from(context);
        //inflating to a view from XML
        View itemView = inflater.inflate(R.layout.activity_forecast_recycler_item,parent,false);
        //put this view to the ViewHolder and return it;
        return new ForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        String s = bookOfLuyanLinfeng.get(position);
        holder.getViewItem().setText(s);
    }

    @Override
    public int getItemCount() {
        return bookOfLuyanLinfeng.size();
    }

    /**
     * ViewHolder class
     * **/

    public class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView itemView;

        public ForecastViewHolder(View view) {
            super(view);
            //fill the view holder with xml content or custom view;
            itemView = view.findViewById(R.id.view_holder_content);
            itemView.setOnClickListener(this);
        }
        public TextView getViewItem(){
            return itemView;
        }

        @Override
        public void onClick(View v) {
            listItemClickListener.onItemClickListener(getAdapterPosition());
        }
    }
}
