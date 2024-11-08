package com.example.badmintonbookingapp.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.response.SlotResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.SlotViewHolder> {
    private List<SlotResponseDTO> slots;
    private final OnSlotClickListener listener;
    private final List<Integer> selectedSlotIds;

    public interface OnSlotClickListener {
        void onSlotClick(SlotResponseDTO slot);
    }

    public SlotAdapter(OnSlotClickListener listener) {
        this.listener = listener;
        this.selectedSlotIds = new ArrayList<>();
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

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull SlotViewHolder holder, int position) {
        SlotResponseDTO slot = slots.get(position);

        // Set slot details
        holder.slotTime.setText(String.format("%s - %s", slot.getStartTime(), slot.getEndTime()));
        holder.slotPrice.setText(String.format("%.2f VND", slot.getPrice()));

        // Change background color based on selection
        if (selectedSlotIds.contains(slot.getId())) {
            holder.itemView.setBackgroundColor(Color.parseColor("#A5D6A7")); // Light green color
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE); // Default background color
        }

        // Handle click event
        holder.itemView.setOnClickListener(v -> {
            if (selectedSlotIds.contains(slot.getId())) {
                // Deselect the slot
                selectedSlotIds.remove(Integer.valueOf(slot.getId()));
                holder.itemView.setBackgroundColor(Color.WHITE); // Reset background color
            } else {
                // Select the slot
                selectedSlotIds.add(slot.getId());
                holder.itemView.setBackgroundColor(Color.parseColor("#A5D6A7")); // Change to green color
            }
            listener.onSlotClick(slot);
        });
    }

    @Override
    public int getItemCount() {
        return slots != null ? slots.size() : 0;
    }

    static class SlotViewHolder extends RecyclerView.ViewHolder {
        TextView slotTime;
        TextView slotPrice;

        SlotViewHolder(View itemView) {
            super(itemView);
            slotTime = itemView.findViewById(R.id.slotTime);
            slotPrice = itemView.findViewById(R.id.slotPrice);
        }
    }
}
