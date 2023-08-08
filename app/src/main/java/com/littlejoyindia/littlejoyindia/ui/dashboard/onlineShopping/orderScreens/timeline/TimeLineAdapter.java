package com.littlejoyindia.littlejoyindia.ui.dashboard.onlineShopping.orderScreens.timeline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.github.vipulasri.timelineview.TimelineView;
import com.littlejoyindia.littlejoyindia.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {

    List<String> orderStatus;
    String currentStatus;
    String dateTime;
    int type = 0;

    public TimeLineAdapter(List<String> orderStatus, String currentStatus,
                           String dateTime,int type) {
        this.orderStatus = orderStatus;
        this.currentStatus = currentStatus;
        this.dateTime = dateTime;
        this.type = type;
    }

    View _view;

    @NonNull
    @Override
    public TimeLineAdapter.TimeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (type == 0){
            _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery_timeline,
                    parent, false);
        }else{
            _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline_horizontal,
                    parent, false);
        }
        return new TimeLineAdapter.TimeLineViewHolder(_view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineAdapter.TimeLineViewHolder holder, int position) {
        holder.text_timeline_title.setText(orderStatus.get(position));

        String formattedDate = "";
        if (dateTime != null){
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
            Date date = null;
            try {
                date = inputFormat.parse(dateTime);
                formattedDate = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        if (orderStatus.get(position).equalsIgnoreCase(currentStatus)) {
            setMarker(holder, R.drawable.radio_button, R.color.quantum_purple);
            holder.text_timeline_date.setVisibility(View.VISIBLE);
            holder.text_timeline_date.setText(formattedDate);
        } else {
            setMarker(holder, R.drawable.radio_unbutton, R.color.quantum_purple);
        }
    }

    @Override
    public int getItemCount() {
        if (orderStatus != null && orderStatus.size() > 0) {
            return orderStatus.size();
        } else {
            return 0;
        }
    }

    public void updateList(List<String> orderStatus, String status, String updationDate) {
        this.orderStatus = orderStatus;
        currentStatus = status;
//        dateTime = updationDate;
        notifyDataSetChanged();
    }

    private void setMarker(TimeLineViewHolder holder, int drawableResId, int colorFilter) {
        holder.timelineView.setMarker(_view.getResources().getDrawable(drawableResId));
    }

    public static class TimeLineViewHolder extends RecyclerView.ViewHolder {
        TextView text_timeline_title;
        TextView text_timeline_date;
        TimelineView timelineView;

        public TimeLineViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            text_timeline_title = itemView.findViewById(R.id.text_timeline_title);
            text_timeline_date = itemView.findViewById(R.id.text_timeline_date);
            timelineView = itemView.findViewById(R.id.timeline);

            timelineView.initLine(viewType);
        }
    }
}
