package com.example.badmintonbookingapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.TelephonesDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.ui.not_fragment.court_detail.YardDetailActivity;

import java.util.List;

public class YardAdapter extends RecyclerView.Adapter<YardAdapter.YardViewHolder> {
    private List<YardResponseDTO> yards;
    private final Context context;

    public YardAdapter(Context context, List<YardResponseDTO> yards) {
        this.context = context;
        this.yards = yards;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setYards(List<YardResponseDTO> yards) {
        this.yards = yards;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public YardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_court_view, parent, false);
        return new YardViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull YardViewHolder holder, int position) {
        YardResponseDTO yard = yards.get(position);

        holder.yardName.setText(yard.getName());
        holder.yardAddress.setText(String.format("Address: %s", yard.getAddress()));
        holder.yardStatus.setText(String.format("Status: %s", yard.getIsActive() ? "Available" : "Unavailable"));
        holder.yardOpenCloseTime.setText(String.format("Open: %s - Close: %s", yard.getOpenTime(), yard.getCloseTime()));
        holder.yardDescription.setText(String.format("Description: %s", yard.getDescription()));

        if (yard.getYardImages() != null && !yard.getYardImages().isEmpty()) {
            String imageUrl = yard.getYardImages().get(0).getImg();
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .into(holder.yardImage);
        } else {
            holder.yardImage.setImageResource(R.drawable.yard_image);
        }

        // Display telephones
//        StringBuilder telephones = new StringBuilder();
//        if (yard.gt() != null && !yard.getTelephones().isEmpty()) {
//            telephones.append("Contacts:");
//            for (TelephonesDTO telephone : yard.getTelephones()) {
//                telephones.append("\n\tNumber: ").append(telephone.getTelephone());
//            }
//            holder.yardTelephones.setText(telephones.toString());
//        } else holder.yardTelephones.setText("Contacts: None");

        // Set an OnClickListener to start YardDetailActivity with the yard ID
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, YardDetailActivity.class);
            intent.putExtra("yard_id", yard.getYardId()); // Passing the yard ID
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return yards != null ? yards.size() : 0;
    }

    public static class YardViewHolder extends RecyclerView.ViewHolder {
        TextView yardName;
        TextView yardAddress;
        TextView yardStatus;
        TextView yardOpenCloseTime;
//        TextView yardTelephones;
        TextView yardDescription;
        ImageView yardImage;

        public YardViewHolder(View itemView) {
            super(itemView);
            yardName = itemView.findViewById(R.id.zone_label);
            yardAddress = itemView.findViewById(R.id.yard_address);
            yardStatus = itemView.findViewById(R.id.yard_status);
            yardOpenCloseTime = itemView.findViewById(R.id.yard_open_close_time);
//            yardTelephones = itemView.findViewById(R.id.yard_telephones);
            yardDescription = itemView.findViewById(R.id.yard_desc);
            yardImage = itemView.findViewById(R.id.yard_image);
        }
    }
}
