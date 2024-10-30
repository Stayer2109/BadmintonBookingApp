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

import com.example.badmintonbookingapp.databinding.CourOwnerMainLayoutBinding;
import com.example.badmintonbookingapp.databinding.AdminMainLayoutBinding;
import com.example.badmintonbookingapp.databinding.StaffMainLayoutBinding;
import com.example.badmintonbookingapp.databinding.UserMainLayoutBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private String userRole;
<<<<<<< HEAD
    private StaffMainLayoutBinding staffBinding;
    private UserMainLayoutBinding userBinding;
    private CourOwnerMainLayoutBinding courOwnerBinding;
=======
    private StaffMainLayoutBinding staffBinding;  // Use UserMainLayoutBinding for user_main_layout
    private UserMainLayoutBinding userBinding;  // Use StaffMainLayoutBinding for staff_main_layout
    private AdminMainLayoutBinding adminBinding;
>>>>>>> 270a5ff814ebce6e26fe1eb5513592f1f86f97f2

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
<<<<<<< HEAD
        } else if ("CourOwner".equals(userRole)) {
            courOwnerBinding = CourOwnerMainLayoutBinding.inflate(getLayoutInflater());
            setContentView(courOwnerBinding.getRoot());

            ViewCompat.setOnApplyWindowInsetsListener(courOwnerBinding.getRoot(), (v, insets) -> {
=======
        } else if ("Admin".equals(userRole)) {
            adminBinding = AdminMainLayoutBinding.inflate(getLayoutInflater());
            setContentView(adminBinding.getRoot());  // Load user-specific layout (user_main_layout)

            // Adjust padding to fit system bars for immersive experience
            ViewCompat.setOnApplyWindowInsetsListener(adminBinding.getRoot(), (v, insets) -> {
>>>>>>> 270a5ff814ebce6e26fe1eb5513592f1f86f97f2
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
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.user_navigation_home, R.id.user_navigation_booking, R.id.user_navigation_order)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.user_nav_host_fragment_activity_main);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(userBinding.userNavView, navController);
        } else if (staffBinding != null && "Staff".equals(userRole)) {
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.staff_navigation_manage_checkin, R.id.staff_navigation_user_payment, R.id.staff_navigation_manage_checkin)
                    .build();

            // Set up NavController with the NavHostFragment from user_main_layout
            NavController navController = Navigation.findNavController(this, R.id.staff_nav_host_fragment_activity_main);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

            // Link BottomNavigationView with NavController
            NavigationUI.setupWithNavController(staffBinding.staffNavView, navController);
        } else if (adminBinding != null && "Admin".equals(userRole)) {
            // Set up AppBarConfiguration with top-level destinations
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.admin_navigation_user_management, R.id.admin_navigation_my_account)
                    .build();

            // Set up NavController with the NavHostFragment from user_main_layout
            NavController navController = Navigation.findNavController(this, R.id.admin_nav_host_fragment_activity_main);

            // Link ActionBar with NavController
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

            // Link BottomNavigationView with NavController
            NavigationUI.setupWithNavController(adminBinding.adminNavView, navController);
        } else if (courOwnerBinding != null && "CourOwner".equals(userRole)) {
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.user_navigation_home, R.id.cour_owner_navigation_staff_management, R.id.cour_owner_navigation_yard_management)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.cour_owner_nav_host_fragment_activity_main);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(courOwnerBinding.courOwnerNavView, navController);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController;
        if ("User".equals(userRole) && userBinding != null) {
            navController = Navigation.findNavController(this, R.id.user_nav_host_fragment_activity_main);
        } else if ("Staff".equals(userRole) && staffBinding != null) {
            navController = Navigation.findNavController(this, R.id.staff_nav_host_fragment_activity_main);
<<<<<<< HEAD
        } else if ("CourOwner".equals(userRole) && courOwnerBinding != null) {
            navController = Navigation.findNavController(this, R.id.cour_owner_nav_host_fragment_activity_main);
=======
        } else if ("Admin".equals(userRole) && staffBinding != null) {
            // Handle Up navigation for staff layout
            navController = Navigation.findNavController(this, R.id.admin_nav_host_fragment_activity_main);
>>>>>>> 270a5ff814ebce6e26fe1eb5513592f1f86f97f2
        } else {
            return super.onSupportNavigateUp();
        }
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    private String getUserRole() {
<<<<<<< HEAD
        return "CourOwner";  // Temporary hardcoded value for demonstration; replace with actual retrieval logic
=======
        return "Admin";  // Temporary hardcoded value for demonstration
>>>>>>> 270a5ff814ebce6e26fe1eb5513592f1f86f97f2
    }
}
