package com.example.badmintonbookingapp.ui.court_owner.court_owner_court;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.badmintonbookingapp.R;

public class AddSlotActivity extends AppCompatActivity {

    private Button btnAddSlot, btnAddmoreSlot, btnDeleteSlot;
    private LinearLayout slotContainer;
    private int slotCount = 0;
    private static final int MAX_SLOTS = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_slot);

        btnAddmoreSlot = findViewById(R.id.btn_add_more_slot);
        slotContainer = findViewById(R.id.main1);  // This is the container for all slot layouts
        btnDeleteSlot = findViewById(R.id.btn_delete_slot);
        btnAddSlot = findViewById(R.id.btn_add_slot);

        btnAddmoreSlot.setOnClickListener(v -> {
            if (slotCount < MAX_SLOTS) {
                // Add a new slot (start time, end time, price)
                addSlot();
                slotCount++;


            } else {
                Toast.makeText(AddSlotActivity.this, "Maximum 12 slots allowed", Toast.LENGTH_SHORT).show();
            }
        });



        btnAddSlot.setOnClickListener(v -> {
        Toast.makeText(AddSlotActivity.this, "Add Complete", Toast.LENGTH_SHORT).show();

        // Navigate to another activity (replace AnotherActivity.class with your desired class)
        Intent intent = new Intent(AddSlotActivity.this, YardOwnerDetail.class);
        startActivity(intent);

        });

        // Handle Remove Slot
        btnDeleteSlot.setOnClickListener(v -> {
            if (slotCount > 0) {
                // Remove the last slot
                slotContainer.removeViewAt(slotContainer.getChildCount() - 1);
                slotCount--;
            }
        });
    }

    private void addSlot() {
        // Inflate and add a new slot view to the container
        View slotView = getLayoutInflater().inflate(R.layout.slot_layout, null);
        slotContainer.addView(slotView);
    }
}