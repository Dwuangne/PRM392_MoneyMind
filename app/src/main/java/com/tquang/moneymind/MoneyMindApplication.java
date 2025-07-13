package com.tquang.moneymind;

import android.app.Application;
import com.tquang.moneymind.utils.ThemeManager;

public class MoneyMindApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        // Áp dụng theme khi ứng dụng khởi động
        ThemeManager themeManager = new ThemeManager(this);
        themeManager.applyTheme();
    }
} 