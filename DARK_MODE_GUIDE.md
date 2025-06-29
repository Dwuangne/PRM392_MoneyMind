# Hướng dẫn sử dụng chức năng Dark Mode

## Tổng quan
Ứng dụng MoneyMind đã được tích hợp chức năng Dark Mode và Light Mode với các tính năng sau:

### Tính năng chính:
1. **Chuyển đổi nhanh**: Nút FAB ở góc trên bên phải để chuyển đổi nhanh giữa Light/Dark mode
2. **Cài đặt chi tiết**: Menu cài đặt với 3 tùy chọn:
   - Chế độ sáng (Light Mode)
   - Chế độ tối (Dark Mode) 
   - Theo cài đặt hệ thống (System Mode)
3. **Lưu trữ cài đặt**: Tự động lưu và khôi phục cài đặt theme khi khởi động lại app
4. **Animation mượt mà**: Hiệu ứng chuyển đổi theme với animation

## Cách sử dụng:

### 1. Chuyển đổi nhanh:
- Nhấn vào nút FAB (Floating Action Button) ở góc trên bên phải màn hình
- Icon sẽ thay đổi:
  - 🌙 (mặt trăng) = chuyển sang Dark Mode
  - ☀️ (mặt trời) = chuyển sang Light Mode

### 2. Cài đặt chi tiết:
- Nhấn vào menu (3 chấm) ở góc trên bên phải
- Chọn "Cài đặt giao diện"
- Chọn một trong 3 tùy chọn:
  - **Chế độ sáng**: Giao diện sáng với nền trắng
  - **Chế độ tối**: Giao diện tối với nền đen
  - **Theo hệ thống**: Tự động theo cài đặt của thiết bị

## Cấu trúc code:

### Files chính:
- `ThemeManager.java`: Quản lý logic chuyển đổi theme
- `MainActivity.java`: Xử lý UI và tương tác người dùng
- `ThemeSettingsActivity.java`: Màn hình cài đặt theme
- `themes.xml` (values & values-night): Định nghĩa theme
- `colors.xml` (values & values-night): Định nghĩa màu sắc

### Icons:
- `ic_light_mode.xml`: Icon mặt trời cho Light Mode
- `ic_dark_mode.xml`: Icon mặt trăng cho Dark Mode

### Animation:
- `theme_switch_animation.xml`: Animation chuyển đổi theme

## Màu sắc được sử dụng:

### Light Mode:
- Background: #FFFFFF (trắng)
- Surface: #F5F5F5 (xám nhạt)
- Text: #222222 (đen đậm)
- Primary: #1976D2 (xanh dương)

### Dark Mode:
- Background: #1A1A1A (đen đậm)
- Surface: #1E1E1E (đen nhạt)
- Text: #E0E0E0 (trắng xám)
- Primary: #121212 (đen)

## Lưu ý:
- Cài đặt theme được lưu trong SharedPreferences
- App sẽ tự động áp dụng theme đã lưu khi khởi động
- Tất cả các màn hình đều hỗ trợ dark mode
- Animation chuyển đổi mượt mà và không gây lag

## Troubleshooting:
Nếu gặp vấn đề:
1. Kiểm tra file `colors.xml` có đầy đủ màu sắc cho cả 2 mode
2. Đảm bảo `themes.xml` được cấu hình đúng
3. Kiểm tra `ThemeManager.java` có được import đúng
4. Xóa cache app nếu cần thiết 