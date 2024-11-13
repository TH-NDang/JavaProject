# 🎏 Koi Pond Management System

## 📖 Giới Thiệu

Koi Pond Management System là một nền tảng toàn diện để quản lý dịch vụ thiết kế và thi công hồ cá Koi. Hệ thống bao gồm cả website giới thiệu sản phẩm và platform quản lý đơn hàng, khách hàng.

## ✨ Tính Năng Chính

### 🌐 Website Công Khai

- **Trang Chủ**: Giới thiệu tổng quan về dịch vụ
- **Dịch Vụ**: Chi tiết các gói dịch vụ và bảng giá
- **Dự Án**: Showcase các dự án tiêu biểu
- **Giới Thiệu**: Thông tin về công ty và đội ngũ
- **Liên Hệ**: Form liên hệ tư vấn

### 👥 Hệ Thống Quản Trị

- **Dashboard**: Thống kê và báo cáo tổng quan
- **Quản lý Đơn Hàng**: Xử lý và theo dõi đơn hàng
- **Quản lý Người Dùng**: Phân quyền và quản lý tài khoản
- **Quản lý Dịch Vụ**: Cập nhật thông tin dịch vụ
- **Báo Cáo**: Phân tích dữ liệu kinh doanh

## 🛠️ Công Nghệ Sử Dụng

### Frontend

- ⚛️ React
- 🎨 Tailwind CSS
- 📊 Recharts
- 🗓️ React Big Calendar
- 🔔 Sonner

### Backend

- ☕ Java Spring Boot
- 🏪 SQL Server
- 🔒 JWT Authentication
- ✨ Lombok
- 🗺️ MapStruct
- 📝 Flyway Migration

## 🚀 Hướng Dẫn Cài Đặt

### Yêu Cầu Hệ Thống

- Node.js 18+
- Java JDK 21
- SQL Server 2019 trở lên
- Maven 3.9.9

### Frontend Setup

```bash
# Di chuyển vào thư mục frontend
cd frontend

# Cài đặt dependencies
npm install

# Chạy ứng dụng
npm start
```

### Backend Setup

```bash
# Di chuyển vào thư mục backend
cd koi-pond-backend

# Build project
mvn clean install

# Chạy ứng dụng
mvn spring-boot:run
```

## 🧪 Testing

Dự án sử dụng Selenium để test UI và demo chức năng. Để chạy demo:

```java
// Chạy toàn bộ demo
DemoRunner runner = new DemoRunner();
runner.runFullDemo();

// Hoặc chạy từng phần
runner.runPublicPagesDemo();  // Demo trang công khai
runner.runContactDemo();      // Demo phần liên hệ
runner.runAdminDemo();        // Demo phần admin
```

## 📂 Cấu Trúc Project

```
koi-pond/
├── frontend/               # React frontend
│   ├── src/
│   │   ├── components/    # React components
│   │   ├── pages/        # Page components
│   │   ├── services/     # API services
│   │   └── types/        # TypeScript types
│   └── public/           # Static files
│
└── koi-pond-backend/      # Spring Boot backend
    └── src/
        ├── main/
        │   ├── java/     # Java source code
        │   └── resources/ # Application resources
        └── test/
            └── java/     # Test files
```

## 🔐 Tài Khoản Demo

| **Role** | **Email**       | **Password** |
| -------- | --------------- | ------------ |
| Admin    | admin@gmail.com | admin123     |

## 🌟 Tính Năng Nổi Bật

- **Responsive Design**: Tương thích với mọi thiết bị
- **Real-time Updates**: Cập nhật trạng thái đơn hàng real-time
- **Interactive Dashboard**: Biểu đồ và thống kê trực quan
- **Role-based Access**: Phân quyền chi tiết theo vai trò
- **Automated Testing**: Test tự động với Selenium

## 📄 License

Project được phát triển dưới license MIT.

## 👨‍💻 Nhóm Phát Triển

[![Contributors](https://contrib.rocks/image?repo=TH-NDang/JavaProject)](https://github.com/TH-NDang/JavaProject/graphs/contributors)
