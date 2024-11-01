package com.example.badmintonbookingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.TelephonesDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;

import java.util.List;

public class YardAdapter extends RecyclerView.Adapter<YardAdapter.YardViewHolder> {
    private List<YardResponseDTO> yards;

    public YardAdapter(List<YardResponseDTO> yards) {
        this.yards = yards;
    }

    public void setYards(List<YardResponseDTO> yards) {
        this.yards = yards;
    }

    @NonNull
    @Override
    public YardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_court_view, parent, false);
        return new YardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YardViewHolder holder, int position) {
        YardResponseDTO yard = yards.get(position);

        holder.yardName.setText(yard.getName());
        holder.yardAddress.setText(yard.getAddress());
        holder.yardStatus.setText(yard.getStatus() ? "Available" : "Unavailable");
        holder.yardOpenCloseTime.setText("Open: " + yard.getOpenTime() + " - Close: " + yard.getCloseTime());

        // Display telephones with id and yardId if needed
        StringBuilder telephones = new StringBuilder();
        if (yard.getTelephones() != null) {
            for (int i = 0; i < yard.getTelephones().size(); i++) {
                TelephonesDTO telephone = yard.getTelephones().get(i);
                telephones.append("ID: ").append(telephone.getId())
                        .append(", Yard ID: ").append(telephone.getYardId())
                        .append(", Number: ").append(telephone.getTelephone());
                if (i < yard.getTelephones().size() - 1) telephones.append("\n");
            }
        }
        holder.yardTelephones.setText("Contacts:\n" + telephones.toString());
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
        TextView yardTelephones;

        public YardViewHolder(View itemView) {
            super(itemView);
            yardName = itemView.findViewById(R.id.zone_label);
            yardAddress = itemView.findViewById(R.id.yard_address);
            yardStatus = itemView.findViewById(R.id.yard_status);
            yardOpenCloseTime = itemView.findViewById(R.id.yard_open_close_time);
            yardTelephones = itemView.findViewById(R.id.yard_telephones);
        }
    }
}
