package com.tquang.moneymind.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tquang.moneymind.R;
import com.tquang.moneymind.data.repository.MonthlyGoalDao;
import com.tquang.moneymind.data.repository.TransactionDao;
import com.tquang.moneymind.model.MonthlyGoal;
import com.tquang.moneymind.model.Transaction;
import com.tquang.moneymind.ui.adapter.TransactionAdapter;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private TextView tvTotalSpending, tvSpendingProgress;
    private ProgressBar progressBarSpending;
    private RecyclerView rvRecentTransactions;
    private TransactionAdapter transactionAdapter;
    private TransactionDao transactionDao;
    private MonthlyGoalDao goalDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initDaos();
        initViews(view);
        loadDashboardData();
        loadRecentTransactions();

        return view;
    }

    private void initDaos() {
        transactionDao = new TransactionDao(getContext());
        goalDao = new MonthlyGoalDao(getContext());
    }

    private void initViews(View view) {
        tvTotalSpending = view.findViewById(R.id.tvTotalSpending);
        tvSpendingProgress = view.findViewById(R.id.tvSpendingProgress);
        progressBarSpending = view.findViewById(R.id.progressBarSpending);
        rvRecentTransactions = view.findViewById(R.id.rvRecentTransactions);

        rvRecentTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecentTransactions.setNestedScrollingEnabled(false);
    }

    private void loadDashboardData() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        double totalExpense = transactionDao.getTotalExpenseByMonthYear(month, year);
        tvTotalSpending.setText(formatCurrency(totalExpense));

        List<MonthlyGoal> goals = goalDao.getMonthlyGoalsByTime(month, year);
        double goalAmount = 0;
        if (!goals.isEmpty()) {
            for(MonthlyGoal goal : goals) {
                goalAmount += goal.getGoalAmount();
            }
        }

        tvSpendingProgress.setText(String.format("%s / %s", formatCurrency(totalExpense), formatCurrency(goalAmount)));

        int percent = (goalAmount > 0) ? (int) Math.min(100, (totalExpense * 100 / goalAmount)) : 0;
        progressBarSpending.setProgress(percent);
    }

    private void loadRecentTransactions() {
        List<Transaction> recentTransactions = transactionDao.getAllTransactions();
        if (recentTransactions.size() > 5) {
            recentTransactions = recentTransactions.subList(0, 5);
        }
        transactionAdapter = new TransactionAdapter(recentTransactions);
        rvRecentTransactions.setAdapter(transactionAdapter);
    }

    private String formatCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(amount);
    }
} 