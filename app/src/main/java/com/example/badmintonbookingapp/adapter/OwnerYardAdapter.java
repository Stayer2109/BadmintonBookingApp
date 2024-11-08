package com.example.badmintonbookingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.TelephonesDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.ui.court_owner.court_owner_court.YardOwnerDetail;

import java.util.List;

public class OwnerYardAdapter extends RecyclerView.Adapter<OwnerYardAdapter.YardViewHolder> {
    private List<YardResponseDTO> yards;
    private Context context;

    public OwnerYardAdapter(Context context, List<YardResponseDTO> yards) {
        this.context = context;
        this.yards = yards;
    }

    public void setYards(List<YardResponseDTO> yards) {
        this.yards = yards;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OwnerYardAdapter.YardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_court_view, parent, false);
        return new OwnerYardAdapter.YardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerYardAdapter.YardViewHolder holder, int position) {
        YardResponseDTO yard = yards.get(position);

        holder.yardName.setText(yard.getName());
        holder.yardAddress.setText("Address: " + yard.getAddress());
        holder.yardStatus.setText("Status: " + (yard.getIsActive() ? "Available" : "Unavailable"));
        holder.yardOpenCloseTime.setText(String.format("Open: %s - Close: %s", yard.getOpenTime(), yard.getCloseTime()));
        holder.yardDescription.setText("Description: " + yard.getDescription());

        // Display telephones
//        StringBuilder telephones = new StringBuilder();
//        if (yard.getTelephones() != null && !yard.getTelephones().isEmpty()) {
//            for (int i = 0; i < yard.getTelephones().size(); i++) {
//                TelephonesDTO telephone = yard.getTelephones().get(i);
//                telephones.append("\n\t\t Number: ").append(telephone.getTelephone());
//                if (i < yard.getTelephones().size() - 1) telephones.append("\n");
//            }
//            holder.yardTelephones.setText("Contacts: " + telephones.toString());
//        } else {
//            holder.yardTelephones.setText("Contacts: None");
//        }

        // Set an OnClickListener to start YardDetailActivity with the yard ID
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, YardOwnerDetail.class);
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

        public YardViewHolder(View itemView) {
            super(itemView);
            yardName = itemView.findViewById(R.id.zone_label);
            yardAddress = itemView.findViewById(R.id.yard_address);
            yardStatus = itemView.findViewById(R.id.yard_status);
            yardOpenCloseTime = itemView.findViewById(R.id.yard_open_close_time);
//            yardTelephones = itemView.findViewById(R.id.yard_telephones);
            yardDescription = itemView.findViewById(R.id.yard_desc);
        }
    }
}
