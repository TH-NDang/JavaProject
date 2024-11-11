package org.group.koipondbackend.selenium.config;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfig {
    public static ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();

        // Kiểm tra xem có đang chạy trong môi trường CI không
        if (System.getenv("CI") != null) {
            // Chạy ở chế độ headless (không có GUI)
            options.addArguments("--headless");

            // Vô hiệu hóa sandbox để chạy với quyền root trong container
            options.addArguments("--no-sandbox");

            // Tránh vấn đề với shared memory trong Docker
            options.addArguments("--disable-dev-shm-usage");

            // Tắt GPU để tránh vấn đề với container
            options.addArguments("--disable-gpu");

            // Đặt kích thước cửa sổ cố định
            options.addArguments("--window-size=1920,1080");

            // Tùy chọn bổ sung nếu cần
            // options.addArguments("--disable-notifications"); // Tắt thông báo
            // options.addArguments("--ignore-certificate-errors"); // Bỏ qua lỗi chứng chỉ
            // options.addArguments("--disable-extensions"); // Tắt extensions
            // options.setExperimentalOption("excludeSwitches", new
            // String[]{"enable-automation"}); // Ẩn cảnh báo automation
        } else {
            // Tùy chọn cho môi trường development
            options.addArguments("--start-maximized");
            // Thêm các tùy chọn khác nếu cần
        }

        return options;
    }
}
