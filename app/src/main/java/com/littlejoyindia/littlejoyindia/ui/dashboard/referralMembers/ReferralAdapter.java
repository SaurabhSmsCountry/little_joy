package com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ui.dashboard.referralMembers.referralModel.ReferralMemberResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReferralAdapter extends RecyclerView.Adapter<ReferralAdapter.VH> {

    List<ReferralMemberResponse.Data> data;
    Context context;

    public ReferralAdapter(List<ReferralMemberResponse.Data> data) {
        this.data = data;
    };

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.referral_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (data != null) {
            holder.tvName.setTypeface(Typeface.createFromAsset(context.getAssets(), "/dosis/dosis_semi_bold.ttf"));
            holder.tvNumber.setTypeface(Typeface.createFromAsset(context.getAssets(), "/dosis/dosis_medium.ttf"));
            holder.tvDate.setTypeface(Typeface.createFromAsset(context.getAssets(), "/dosis/dosis_medium.ttf"));
            holder.tvName.setText(data.get(position).getFull_name());
            holder.tvNumber.setText(data.get(position).getPhone_no());
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd mm, yyyy, hh:mm aa");
            Date date = null;
            String formattedDate="";
            try {
                date = inputFormat.parse(data.get(position).getDate());
                formattedDate = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.tvDate.setText(formattedDate);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class VH extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvNumber;
        public VH(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
