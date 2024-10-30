package com.example.badmintonbookingapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.badmintonbookingapp.databinding.AdminMainLayoutBinding;
import com.example.badmintonbookingapp.databinding.CourtOwnerMainLayoutBinding;
import com.example.badmintonbookingapp.databinding.StaffMainLayoutBinding;
import com.example.badmintonbookingapp.databinding.UserMainLayoutBinding;

public class MainActivity extends AppCompatActivity {
    private String userRole;
    private StaffMainLayoutBinding staffBinding;
    private UserMainLayoutBinding userBinding;
    private CourtOwnerMainLayoutBinding courtOwnerBinding;
    private AdminMainLayoutBinding adminBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge immersive layout
        EdgeToEdge.enable(this);

        // Retrieve user role before setting layout
        userRole = getUserRole();

        // Set the layout based on user role
        setLayoutBasedOnRole();

        // Set up navigation components if layout is user_main_layout or cour_owner_main_layout
        setupNavigation();
    }

    // Function to set layout based on user role
    private void setLayoutBasedOnRole() {
        if ("User".equals(userRole)) {
            userBinding = UserMainLayoutBinding.inflate(getLayoutInflater());
            setContentView(userBinding.getRoot());

            // Adjust padding to fit system bars for immersive experience
            ViewCompat.setOnApplyWindowInsetsListener(userBinding.getRoot(), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        } else if ("Staff".equals(userRole)) {
            staffBinding = StaffMainLayoutBinding.inflate(getLayoutInflater());
            setContentView(staffBinding.getRoot());

            ViewCompat.setOnApplyWindowInsetsListener(staffBinding.getRoot(), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        } else if ("CourtOwner".equals(userRole)) {
            courtOwnerBinding = CourtOwnerMainLayoutBinding.inflate(getLayoutInflater());
            setContentView(courtOwnerBinding.getRoot());

            // Adjust padding to fit system bars for immersive experience
            ViewCompat.setOnApplyWindowInsetsListener(courtOwnerBinding.getRoot(), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        } else if ("Admin".equals(userRole)) {
            adminBinding = AdminMainLayoutBinding.inflate(getLayoutInflater());
            setContentView(adminBinding.getRoot());  // Load user-specific layout (user_main_layout)

            // Adjust padding to fit system bars for immersive experience
            ViewCompat.setOnApplyWindowInsetsListener(adminBinding.getRoot(), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    // Function to set up navigation components
    private void setupNavigation() {
        if (userBinding != null && "User".equals(userRole)) {
            // Set up AppBarConfiguration with top-level destinations
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.user_navigation_home)
                    .build();

            NavController navController = Navigation.findNavController(this, R.id.user_nav_host_fragment_activity_main);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(userBinding.userNavView, navController);

        } else if (staffBinding != null && "Staff".equals(userRole)) {
            // Set up AppBarConfiguration with top-level destinations
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.staff_navigation_home)
                    .build();

            NavController navController = Navigation.findNavController(this, R.id.staff_nav_host_fragment_activity_main);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(staffBinding.staffNavView, navController);

        } else if (adminBinding != null && "Admin".equals(userRole)) {
            // Set up AppBarConfiguration with top-level destinations
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.admin_navigation_user_management)
                    .build();

            NavController navController = Navigation.findNavController(this, R.id.admin_nav_host_fragment_activity_main);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(adminBinding.adminNavView, navController);

        } else if (courtOwnerBinding != null && "CourtOwner".equals(userRole)) {
            // Set up AppBarConfiguration with top-level destinations
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.court_owner_navigation_home)
                    .build();

            NavController navController = Navigation.findNavController(this, R.id.court_owner_nav_host_fragment_activity_main);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(courtOwnerBinding.courtOwnerNavView, navController);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController;
        if ("User".equals(userRole) && userBinding != null) {
            navController = Navigation.findNavController(this, R.id.user_nav_host_fragment_activity_main);
        } else if ("Staff".equals(userRole) && staffBinding != null) {
            navController = Navigation.findNavController(this, R.id.staff_nav_host_fragment_activity_main);
        } else if ("CourtOwner".equals(userRole) && courtOwnerBinding != null) {
            navController = Navigation.findNavController(this, R.id.court_owner_nav_host_fragment_activity_main);
        } else if ("Admin".equals(userRole) && adminBinding != null) {
            navController = Navigation.findNavController(this, R.id.admin_nav_host_fragment_activity_main);
        } else {
            return super.onSupportNavigateUp();
        }
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    private String getUserRole() {
        return "Admin";
    }
}
