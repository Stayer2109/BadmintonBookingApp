package com.example.badmintonbookingapp.ui.not_fragment.court_detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.badmintonbookingapp.R;
import com.example.badmintonbookingapp.adapter.ImageCarouselAdapter;
import com.example.badmintonbookingapp.dto.TelephonesDTO;
import com.example.badmintonbookingapp.dto.YardImagesDTO;
import com.example.badmintonbookingapp.dto.response.YardResponseDTO;
import com.example.badmintonbookingapp.repository.AuthRepository;
import com.example.badmintonbookingapp.ui.user.booking.BookingActivity;
import com.example.badmintonbookingapp.utils.TokenManager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class YardDetailActivity extends AppCompatActivity {
    private YardDetailViewModel yardDetailViewModel;
    private int yardId;
    private ViewPager2 imageCarousel;
    private TabLayout carouselIndicator;

    // UI Components
    private TextView yardName;
    private TextView address;
    private TextView openingHours;
    private TextView status;
    private TextView description;
    private LinearLayout contactContainer;
    private Button bookCourtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yard_detail);

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Initialize UI components
        yardName = findViewById(R.id.yardName);
        address = findViewById(R.id.address);
        openingHours = findViewById(R.id.openingHours);
        status = findViewById(R.id.status);
        contactContainer = findViewById(R.id.contactContainer);
        imageCarousel = findViewById(R.id.imageCarousel);
        carouselIndicator = findViewById(R.id.carouselIndicator);
        bookCourtButton = findViewById(R.id.bookCourtButton);
        description = findViewById(R.id.description);

        // Get the yard ID passed from the intent
        yardId = getIntent().getIntExtra("yard_id", 0);

        TokenManager tokenManager = TokenManager.getInstance(getApplicationContext());
        AuthRepository authRepository = new AuthRepository(tokenManager);

        // Set up ViewModel
        YardDetailViewModelFactory factory = new YardDetailViewModelFactory(tokenManager, authRepository);
        yardDetailViewModel = new ViewModelProvider(this, factory).get(YardDetailViewModel.class);

        // Fetch yard details if yardId is valid
        if (yardId != 0) {
            yardDetailViewModel.fetchYardDetail(yardId);
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

        // Button OnClick listener
        bookCourtButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookingActivity.class);
            intent.putExtra("yard_id", yardId);
            startActivity(intent);
        });
    }

    private void displayYardDetails(YardResponseDTO yard) {
        yardName.setText(yard.getName());
        address.setText(yard.getAddress());
        openingHours.setText(String.format("%s - %s", yard.getOpenTime(), yard.getCloseTime()));
        status.setText(yard.getIsActive() ? "Available" : "Unavailable");
        description.setText(yard.getDescription());

        // Set color based on status
        status.setTextColor(ContextCompat.getColor(this, yard.getIsActive() ? R.color.green : R.color.red));

        // Populate multiple contact numbers dynamically
//        contactContainer.removeAllViews();
//        for (TelephonesDTO telephone : yard.getTelephones()) {
//            View contactView = getLayoutInflater().inflate(R.layout.contacts_item, contactContainer, false);
//            TextView phoneTextView = contactView.findViewById(R.id.phoneNumber);
//            phoneTextView.setText(telephone.getTelephone());
//            phoneTextView.setOnClickListener(v -> {
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telephone.getTelephone()));
//                startActivity(intent);
//            });
//            contactContainer.addView(contactView);
//        }

//         Set up the image carousel
        List<YardImagesDTO> imageList = yard.getYardImages();
        if (imageList != null && !imageList.isEmpty()) {
            List<String> imageUrls = new ArrayList<>();
            for (YardImagesDTO image : imageList) {
                imageUrls.add(image.getImg());
            }

            ImageCarouselAdapter adapter = new ImageCarouselAdapter(this, imageUrls);
            imageCarousel.setAdapter(adapter);

            // Set up TabLayout indicator for carousel
            new TabLayoutMediator(carouselIndicator, imageCarousel, (tab, position) -> {
                View dot = new View(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(24, 24); // Size for dots
                params.setMargins(1, 0, 1, 0); // Spacing between dots
                dot.setLayoutParams(params);
                dot.setBackgroundResource(R.drawable.tab_dot_selector);
                tab.setCustomView(dot);
            }).attach();

            // Adding a composite transformer for zoom-in effect on current item
            CompositePageTransformer transformer = new CompositePageTransformer();
            transformer.addTransformer(new MarginPageTransformer(16)); // Set margin between pages
            transformer.addTransformer((page, position) -> {
                float scale = 0.8f + (1 - Math.abs(position)) * 0.26f; // Zoom effect
                page.setScaleY(scale);
            });
            imageCarousel.setPageTransformer(transformer);
        } else {
            // Hide the carousel if there are no images
            imageCarousel.setVisibility(View.GONE);
            carouselIndicator.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Finish the activity when the back button is pressed
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
