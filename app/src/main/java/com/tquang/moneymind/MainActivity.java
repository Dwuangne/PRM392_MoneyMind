package com.tquang.moneymind;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tquang.moneymind.ui.activity.AddEditTransactionActivity;
import com.tquang.moneymind.ui.fragment.HomeFragment;
import com.tquang.moneymind.ui.fragment.TransactionListFragment;
import com.tquang.moneymind.ui.fragment.WalletListFragment;
import com.tquang.moneymind.ui.fragment.GoalListFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);

        setupBottomNavigation();

        // Kiểm tra Intent từ GoalListActivity
        String targetFragment = getIntent().getStringExtra("fragment");
        
        // Load the default fragment
        if (savedInstanceState == null) {
            if (targetFragment != null) {
                handleFragmentNavigation(targetFragment);
            } else {
                loadFragment(new HomeFragment());
                bottomNavigationView.setSelectedItemId(R.id.nav_home);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reset lại selected item dựa trên fragment hiện tại
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (currentFragment instanceof HomeFragment) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        } else if (currentFragment instanceof TransactionListFragment) {
            bottomNavigationView.setSelectedItemId(R.id.nav_transactions);
        } else if (currentFragment instanceof WalletListFragment) {
            bottomNavigationView.setSelectedItemId(R.id.nav_wallets);
        } else if (currentFragment instanceof GoalListFragment) {
            bottomNavigationView.setSelectedItemId(R.id.nav_goals);
        }
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.nav_transactions) {
               selectedFragment = new TransactionListFragment();
            } else if (itemId == R.id.nav_wallets) {
                selectedFragment = new WalletListFragment();
            } else if (itemId == R.id.nav_goals) {
                selectedFragment = new GoalListFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }
            return false;
        });

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditTransactionActivity.class);
            startActivity(intent);
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }

    private void handleFragmentNavigation(String fragmentName) {
        Fragment selectedFragment = null;
        int selectedItemId = R.id.nav_home;
        
        switch (fragmentName) {
            case "transactions":
                selectedFragment = new TransactionListFragment();
                selectedItemId = R.id.nav_transactions;
                break;
            case "wallets":
                selectedFragment = new WalletListFragment();
                selectedItemId = R.id.nav_wallets;
                break;
            case "goals":
                selectedFragment = new GoalListFragment();
                selectedItemId = R.id.nav_goals;
                break;
            case "home":
            default:
                selectedFragment = new HomeFragment();
                selectedItemId = R.id.nav_home;
                break;
        }
        
        if (selectedFragment != null) {
            loadFragment(selectedFragment);
            bottomNavigationView.setSelectedItemId(selectedItemId);
        }
    }
}