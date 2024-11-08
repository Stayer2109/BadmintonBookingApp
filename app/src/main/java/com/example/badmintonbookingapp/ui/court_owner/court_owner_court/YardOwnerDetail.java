package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.dto.TelephonesDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.ui.not_fragment.court_detail.YardDetailViewModel;
import com.example.badmintonbookingapp.ui.not_fragment.court_detail.YardDetailViewModelFactory;
import com.example.badmintonbookingapp.utils.TokenManager;

public class YardOwnerDetail extends AppCompatActivity {

    private YardDetailViewModel yardDetailViewModel;
    private int yardId;

    // UI Components
    private TextView yardName;
    private TextView address;
    private TextView openingHours;
    private TextView status;
    private LinearLayout contactContainer; // Container for multiple phone numbers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yard_owner_detail);

        Button btnAddSlot = findViewById(R.id.btn_add_slot);
        btnAddSlot.setOnClickListener(v -> {
            // Create an Intent to navigate to the Add Slot Activity
            Intent intent = new Intent(YardOwnerDetail.this, AddSlotActivity.class);
            startActivity(intent);
        });

        // Enable the "up" button on the Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize UI components
        yardName = findViewById(R.id.yard_name);
        address = findViewById(R.id.yard_address);
        openingHours = findViewById(R.id.yard_openingHours);
        status = findViewById(R.id.yard_status);
        contactContainer = findViewById(R.id.contactContainer);

        // Get the yard ID passed from the intent
        yardId = getIntent().getIntExtra("yard_id", 0);

        TokenManager tokenManager = TokenManager.getInstance(getApplicationContext());
        AuthRepository authRepository = new AuthRepository(tokenManager);

        // Set up ViewModel
        YardDetailViewModelFactory factory = new YardDetailViewModelFactory(tokenManager, authRepository);
        yardDetailViewModel = new ViewModelProvider(this, factory).get(YardDetailViewModel.class);

        // Fetch yard details if yardId is valid
        if (yardId != 0) {
            yardDetailViewModel.fetchYardDetail(yardId); // Trigger the API call
            yardDetailViewModel.getYardDetail().observe(this, yard -> {
                if (yard != null) {
                    displayYardDetails(yard);
                } else {
                    Toast.makeText(this, "Failed to load yard details.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Invalid yard ID.", Toast.LENGTH_SHORT).show();
        }

        // Initialize the Book Court button and set its click listener
        Button bookCourtButton = findViewById(R.id.updateButton);
        bookCourtButton.setOnClickListener(v -> {
            // Handle the booking action here, such as opening a new booking activity or showing a dialog
            Toast.makeText(this, "Update Button clicked", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Finish the activity and go back to the previous screen
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayYardDetails(YardResponseDTO yard) {
        yardName.setText(yard.getName());
        address.setText(yard.getAddress());
        openingHours.setText(String.format("%s - %s", yard.getOpenTime(), yard.getCloseTime()));
        status.setText(yard.getIsActive() ? "Available" : "Unavailable");

        // Set color based on status
        if (yard.getIsActive()) {
            status.setTextColor(ContextCompat.getColor(this, R.color.green));
        } else {
            status.setTextColor(ContextCompat.getColor(this, R.color.red));
        }

        // Populate multiple contact numbers dynamically
//        contactContainer.removeAllViews(); // Clear any previous views
//        for (TelephonesDTO telephone : yard.getTelephones()) {
//            // Inflate the contact_item.xml for each phone number
//            View contactView = getLayoutInflater().inflate(R.layout.contacts_item, contactContainer, false);
//
//            // Set the phone number text
//            TextView phoneTextView = contactView.findViewById(R.id.phoneNumber);
//            phoneTextView.setText(telephone.getTelephone());
//
//            // Optional: make the phone number clickable to dial
//            phoneTextView.setOnClickListener(v -> {
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telephone.getTelephone()));
//                startActivity(intent);
//            });
//
//            // Add the inflated contact view to the container
//            contactContainer.addView(contactView);
//        }
    }
}
