package com.technovate18.sanskar.touristy.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technovate18.sanskar.touristy.R;
import com.technovate18.sanskar.touristy.models.FeedPostModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by hadoop on 15/3/18.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {


    private List<FeedPostModel> dataList;
    private int positionoooo;

    public int getPositionoooo() {
        return positionoooo;
    }

    public void setPositionoooo(int positionoooo) {
        this.positionoooo = positionoooo;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        public TextView title, author, description, day_date,time;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.row_1_tv);
            author = (TextView) view.findViewById(R.id.row_2_tv);
            description = (TextView) view.findViewById(R.id.row_3_tv);
            day_date = (TextView)view.findViewById(R.id.day_date_tv);
            time = (TextView)view.findViewById(R.id.time_tv);

            view.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            Log.e("onCreateContextMenu","from viewholder class, yha sab thk hai");
            contextMenu.add(Menu.NONE,Menu.NONE,Menu.NONE,"Share notice");
            contextMenu.add(Menu.NONE,Menu.NONE,Menu.NONE,"Like");

        }

    }

    public FeedAdapter() {
    }

    public FeedAdapter(List<FeedPostModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        FeedPostModel datu = dataList.get(position);

        String[] arr =getDateandtime(Long.parseLong(datu.getTimestamp())).split("-");
        String entryDate= arr[0];
        String entryTime= arr[1];

        holder.author.setText("Issued by : "+datu.getAuthor());
        holder.title.setText(datu.getTitle());
        holder.description.setText(datu.getDescription());
        holder.day_date.setText(entryDate);
        holder.time.setText(entryTime);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setPositionoooo(holder.getAdapterPosition());
                Log.e("FROM LOOOOONG CLICK", "YAHA SAB THK HAI");
                return false;
            }
        });
        // set everything here- done
        // like : holder.time = ..., holder.day_daye = ..., holder.title = ...

    }
    public static String getDateandtime(long timestamp) {
        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getTimeZone("India/Kolkata");
            calendar.setTimeInMillis(timestamp * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdfDate = new SimpleDateFormat("EEE, MMM d, ''yy");
            SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm a");

            java.util.Date currenTimeZone = (Date) calendar.getTime();
            return sdfDate.format(currenTimeZone)+"-"+sdfTime.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void refreshList(List<FeedPostModel> dataList){

        this.dataList = dataList;
        notifyItemInserted(dataList.size() - 1);

    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }


}
