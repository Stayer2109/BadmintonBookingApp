package com.example.badmintonbookingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.response.SlotResponseDTO;


import java.time.format.DateTimeFormatter;
import java.util.List;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.SlotViewHolder> {


    private List<SlotResponseDTO> slots;
    private final OnSlotClickListener listener;


    public interface OnSlotClickListener {
        void onSlotClick(SlotResponseDTO slot);
    }

    public SlotAdapter(OnSlotClickListener listener) {
        this.listener = listener;
    }


    public void setSlots(List<SlotResponseDTO> slots) {
        this.slots = slots;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slot, parent, false);
        return new SlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlotViewHolder holder, int position) {
        SlotResponseDTO slot = slots.get(position);


        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String startTime = slot.getStartTime().format(timeFormatter);
        String endTime = slot.getEndTime().format(timeFormatter);
        holder.slotTime.setText(String.format("%s - %s", startTime, endTime));
        holder.slotPrice.setText(String.format("%.2f", slot.getPrice()));
        holder.slotStatus.setText(slot.getStatus());



        holder.itemView.setOnClickListener(v -> listener.onSlotClick(slot));

    }

    @Override
    public int getItemCount() {
        return slots != null ? slots.size() : 0;
    }

    static class SlotViewHolder extends RecyclerView.ViewHolder {
        TextView slotTime;
        TextView slotPrice;
        TextView slotStatus;


        SlotViewHolder(View itemView) {
            super(itemView);
            slotTime = itemView.findViewById(R.id.slotTime);
            slotPrice = itemView.findViewById(R.id.slotPrice);
            slotStatus = itemView.findViewById(R.id.slotStatus);


        }
    }
}
