package com.example.badmintonbookingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.response.BookingOrdersResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private List<BookingOrdersResponseDTO> bookings = new ArrayList<>();

    // Phương thức để cập nhật danh sách booking
    public void setBookings(List<BookingOrdersResponseDTO> newBookings) {
        this.bookings.clear();
        if (newBookings != null) {
            this.bookings.addAll(newBookings);
        }
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
        BookingOrdersResponseDTO booking = bookings.get(position);

        // Gán dữ liệu vào các TextView
        holder.bookingIdTextView.setText("Booking ID: " + booking.getBookingOrderId());
        holder.bookingDateTextView.setText("Date: " + booking.getBookingDate());
        holder.bookingStatusTextView.setText("Status: " + booking.getActive());
    }

    @Override
    public int getItemCount() {
        return bookings != null ? bookings.size() : 0;
    }

    static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView bookingIdTextView;
        TextView bookingDateTextView;
        TextView bookingStatusTextView;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingIdTextView = itemView.findViewById(R.id.bookingIdText);
            bookingDateTextView = itemView.findViewById(R.id.bookingTimeText);
            bookingStatusTextView = itemView.findViewById(R.id.bookingStatusText);
        }
    }
}
