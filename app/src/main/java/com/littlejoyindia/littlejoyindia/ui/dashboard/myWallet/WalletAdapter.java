package com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.littlejoyindia.littlejoyindia.R;
import com.littlejoyindia.littlejoyindia.ui.dashboard.myWallet.myWalletModel.MyWalletHistoryResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.VH> {

    List<MyWalletHistoryResponse.Data> data;
    private Context context;

    public WalletAdapter(List<MyWalletHistoryResponse.Data> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.tvTitle.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_semi_bold.ttf"));
        holder.tvAmount.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_regular.ttf"));
        holder.tvDateTime.setTypeface(Typeface.createFromAsset(context.getResources().getAssets(), "dosis/dosis_bold.ttf"));

        holder.tvTitle.setText(data.get(position).getDescription());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yy - hh:mm:ss");
        Date date;
        String formattedDate="";
        try {
            date = inputFormat.parse(data.get(position).getDate());
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvDateTime.setText(formattedDate);
        holder.tvAmount.setText("\u20B9"+data.get(position).getAmount());
        if (data.get(position).getAction().contains("sub")) {
            holder.image.setImageResource(R.drawable.drawable_wallet_red_circle);
            holder.tvAmount.setTextColor(context.getColor(R.color.red_color_theme));
        } else if (data.get(position).getAction().contains("add")) {
            holder.image.setImageResource(R.drawable.drawable_wallet_green_circle);
            holder.tvAmount.setTextColor(context.getColor(R.color.green_color_theme));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDateTime, tvAmount;
        ImageView image;
        public VH(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvAmount = itemView.findViewById(R.id.tvAmount);
        }
    }
}
