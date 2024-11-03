package com.example.badmintonbookingapp.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.response.BookingOrdersResponseDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<BookingOrdersResponseDTO> bookingList;


    public void submitList(List<BookingOrdersResponseDTO> bookingList) {
        this.bookingList = bookingList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        BookingOrdersResponseDTO booking = bookingList.get(position);
        holder.bookingIdText.setText("Booking ID: " + booking.getId());

        if (booking.getStatus() != null) {
            holder.bookingStatusText.setText("Status: " + (booking.getStatus() ? "Confirmed" : "Pending"));
        }

        if (booking.getBookingAt() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            holder.bookingTimeText.setText("Booking Time: " + booking.getBookingAt().format(formatter));
        }
        if(booking.getYard() != null) {
            holder.bookingYardText.setText("Yard ID: " + booking.getYard().getId());
        }

        if(booking.getSlot() != null) {
            holder.bookingSlotText.setText("Slot ID: " + booking.getSlot().getId());
        }
    }



    @Override
    public int getItemCount() {
        return bookingList != null ? bookingList.size() : 0;
    }


    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView bookingIdText;
        TextView bookingStatusText;
        TextView bookingTimeText;
        TextView bookingYardText;
        TextView bookingSlotText;


        BookingViewHolder(View itemView) {
            super(itemView);
            bookingIdText = itemView.findViewById(R.id.bookingIdText);
            bookingStatusText = itemView.findViewById(R.id.bookingStatusText);
            bookingTimeText = itemView.findViewById(R.id.bookingTimeText);
            bookingYardText = itemView.findViewById(R.id.bookingYardText);
            bookingSlotText = itemView.findViewById(R.id.bookingSlotText);


        }
    }
}