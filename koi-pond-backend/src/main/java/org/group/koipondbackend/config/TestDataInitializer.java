package org.group.koipondbackend.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.koipondbackend.entity.*;
import org.group.koipondbackend.entity.enums.OrderStatus;
import org.group.koipondbackend.entity.enums.Role;
import org.group.koipondbackend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("!prod") // Don't run in production
public class TestDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ServicesRepository servicesRepository;
    private final OrderRepository orderRepository;
    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;
    private final Random random = new Random();

    private static final int DESIRED_CUSTOMERS = 20;
    private static final int DESIRED_STAFF = 5;
    private static final int DESIRED_ORDERS = 50;

    @Override
    @Transactional
    public void run(String... args) {
        try {
            log.info("Starting data initialization...");

            // Create or get existing services
            List<Services> services = ensureServices();

            // Create or get admin
            ensureAdmin();

            // Create additional staff if needed
            List<Staff> staffMembers = ensureStaffMembers();

            // Create additional customers if needed
            List<User> customers = ensureCustomers();

            // Create additional orders if needed
            ensureOrders(customers, services, staffMembers);

            log.info("Data initialization completed successfully!");
        } catch (Exception e) {
            log.error("Error during data initialization", e);
            throw e;
        }
    }

    private List<Services> ensureServices() {
        log.info("Checking and creating services...");
        List<Services> existingServices = servicesRepository.findAll();

        // If we already have all services, return them
        if (existingServices.size() >= 3) {
            return existingServices;
        }

        // Create map of existing services by name
        Map<String, Services> serviceMap = new HashMap<>();
        for (Services service : existingServices) {
            serviceMap.put(service.getName(), service);
        }

        List<Services> servicesToCreate = new ArrayList<>();

        // Check and create basic service
        if (!serviceMap.containsKey("Thiết kế hồ cá Koi cơ bản")) {
            servicesToCreate.add(Services.builder()
                    .name("Thiết kế hồ cá Koi cơ bản")
                    .description("Thiết kế và thi công hồ cá Koi với đầy đủ tính năng thiết yếu")
                    .price(15000000.0)
                    .features(Arrays.asList(
                            "Thiết kế 2D",
                            "Thi công cơ bản",
                            "Bảo hành 1 năm",
                            "Tư vấn kỹ thuật"))
                    .isPopular(false)
                    .build());
        }

        // Check and create premium service
        if (!serviceMap.containsKey("Thiết kế hồ cá Koi cao cấp")) {
            servicesToCreate.add(Services.builder()
                    .name("Thiết kế hồ cá Koi cao cấp")
                    .description("Giải pháp hoàn hảo cho những ai muốn một hồ cá Koi chuyên nghiệp")
                    .price(25000000.0)
                    .features(Arrays.asList(
                            "Thiết kế 3D",
                            "Thi công trọn gói",
                            "Bảo hành 2 năm",
                            "Tư vấn chuyên sâu",
                            "Bảo trì 6 tháng"))
                    .isPopular(true)
                    .build());
        }

        // Check and create maintenance service
        if (!serviceMap.containsKey("Gói Bảo trì Định kỳ")) {
            servicesToCreate.add(Services.builder()
                    .name("Gói Bảo trì Định kỳ")
                    .description("Dịch vụ bảo trì và chăm sóc hồ cá Koi định kỳ")
                    .price(2000000.0)
                    .features(Arrays.asList(
                            "Kiểm tra định kỳ",
                            "Vệ sinh hệ thống",
                            "Kiểm tra chất lượng nước",
                            "Tư vấn dinh dưỡng"))
                    .isPopular(false)
                    .build());
        }

        if (!servicesToCreate.isEmpty()) {
            servicesRepository.saveAll(servicesToCreate);
            existingServices = servicesRepository.findAll();
        }

        return existingServices;
    }

    private User ensureAdmin() {
        log.info("Checking and creating admin...");
        String adminEmail = "admin@gmail.com";
        return userRepository.findByEmail(adminEmail)
                .orElseGet(() -> userRepository.save(User.builder()
                        .email(adminEmail)
                        .password(passwordEncoder.encode("admin123"))
                        .fullName("System Admin")
                        .phone("0123456789")
                        .address("123 Đường Admin, Quận 1, TP.HCM")
                        .role(Role.ADMIN)
                        .status("ACTIVE")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()));
    }

    private List<Staff> ensureStaffMembers() {
        log.info("Checking and creating staff members...");
        List<Staff> existingStaff = staffRepository.findAll();

        if (existingStaff.size() >= DESIRED_STAFF) {
            return existingStaff;
        }

        String[] staffNames = {
                "Nguyễn Văn A", "Trần Thị B", "Lê Văn C",
                "Phạm Thị D", "Hoàng Văn E"
        };

        String[] departments = {
                "Tư vấn", "Thiết kế", "Thi công",
                "Bảo trì", "Chăm sóc khách hàng"
        };

        Set<String> existingEmails = new HashSet<>();
        existingStaff.forEach(staff -> existingEmails.add(staff.getEmail()));

        List<Staff> newStaff = new ArrayList<>();
        for (int i = existingStaff.size(); i < DESIRED_STAFF; i++) {
            String email = "staff" + (i + 1) + "@gmail.com";
            if (!existingEmails.contains(email)) {
                Staff staff = Staff.builder()
                        .email(email)
                        .password(passwordEncoder.encode("staff123"))
                        .fullName(staffNames[i])
                        .phone("098765432" + i)
                        .address("456 Đường Staff " + (i + 1) + ", TP.HCM")
                        .role(Role.STAFF)
                        .department(departments[i])
                        .status("ACTIVE")
                        .joinDate(LocalDateTime.now().minusMonths(random.nextInt(12) + 1))
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                newStaff.add(staff);
            }
        }

        if (!newStaff.isEmpty()) {
            staffRepository.saveAll(newStaff);
            existingStaff = staffRepository.findAll();
        }

        return existingStaff;
    }

    private List<User> ensureCustomers() {
        log.info("Checking and creating customers...");
        List<User> existingCustomers = userRepository.findAllByRole(Role.CUSTOMER);

        if (existingCustomers.size() >= DESIRED_CUSTOMERS) {
            return existingCustomers;
        }

        String[] districts = { "Quận 1", "Quận 2", "Quận 3", "Quận 7", "Thủ Đức" };
        Set<String> existingEmails = new HashSet<>();
        existingCustomers.forEach(customer -> existingEmails.add(customer.getEmail()));

        List<User> newCustomers = new ArrayList<>();
        for (int i = existingCustomers.size() + 1; i <= DESIRED_CUSTOMERS; i++) {
            String email = "customer" + i + "@gmail.com";
            if (!existingEmails.contains(email)) {
                String district = districts[random.nextInt(districts.length)];
                User customer = User.builder()
                        .email(email)
                        .password(passwordEncoder.encode("customer123"))
                        .fullName("Khách hàng " + i)
                        .phone("091234567" + i)
                        .address("789 Đường Customer " + i + ", " + district + ", TP.HCM")
                        .role(Role.CUSTOMER)
                        .status("ACTIVE")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                newCustomers.add(customer);
            }
        }

        if (!newCustomers.isEmpty()) {
            userRepository.saveAll(newCustomers);
            existingCustomers = userRepository.findAllByRole(Role.CUSTOMER);
        }

        return existingCustomers;
    }

    private void ensureOrders(List<User> customers, List<Services> services, List<Staff> staffMembers) {
        log.info("Checking and creating orders...");
        long existingOrderCount = orderRepository.count();

        if (existingOrderCount >= DESIRED_ORDERS) {
            return;
        }

        int ordersToCreate = DESIRED_ORDERS - (int) existingOrderCount;
        LocalDateTime startDate = LocalDateTime.now().minusMonths(6);

        List<Order> newOrders = new ArrayList<>();
        for (int i = 0; i < ordersToCreate; i++) {
            User customer = customers.get(random.nextInt(customers.size()));
            Services service = services.get(random.nextInt(services.size()));
            Staff staff = staffMembers.get(random.nextInt(staffMembers.size()));

            LocalDateTime orderDate = randomDateBetween(startDate, LocalDateTime.now());
            OrderStatus status = determineStatus(orderDate);

            Order order = Order.builder()
                    .customer(customer)
                    .service(service)
                    .assignedStaff(staff)
                    .status(status)
                    .totalAmount(calculateTotalAmount(service.getPrice()))
                    .requirements(generateRequirements())
                    .location(customer.getAddress())
                    .createdAt(orderDate)
                    .updatedAt(orderDate)
                    .build();

            if (status == OrderStatus.COMPLETED) {
                order.setCompletionDate(orderDate.plusDays(random.nextInt(14) + 1));
            } else if (status == OrderStatus.CANCELLED) {
                order.setCancelledDate(orderDate.plusDays(random.nextInt(3) + 1));
                order.setCancellationReason("Khách hàng thay đổi kế hoạch");
            }

            newOrders.add(order);
        }

        orderRepository.saveAll(newOrders);
    }

    private LocalDateTime randomDateBetween(LocalDateTime start, LocalDateTime end) {
        long startEpochDay = start.toLocalDate().toEpochDay();
        long endEpochDay = end.toLocalDate().toEpochDay();
        long randomDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay));

        return LocalDateTime.of(
                LocalDateTime.ofEpochSecond(randomDay * 86400, 0, java.time.ZoneOffset.UTC).toLocalDate(),
                java.time.LocalTime.of(
                        random.nextInt(9) + 8, // 8 AM to 5 PM
                        random.nextInt(60)));
    }

    private OrderStatus determineStatus(LocalDateTime orderDate) {
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(orderDate, LocalDateTime.now());

        if (daysBetween < 7) {
            return OrderStatus.values()[random.nextInt(3)]; // PENDING, CONSULTING, or DESIGNING
        } else if (daysBetween < 14) {
            return OrderStatus.values()[random.nextInt(5)]; // Up to CONSTRUCTION
        } else {
            // Older orders are more likely to be COMPLETED or CANCELLED
            return random.nextDouble() < 0.8 ? OrderStatus.COMPLETED : OrderStatus.CANCELLED;
        }
    }

    private double calculateTotalAmount(double basePrice) {
        // Add random variations to base price (±10%)
        double variation = (random.nextDouble() * 0.2) - 0.1; // -10% to +10%
        return basePrice * (1 + variation);
    }

    private String generateRequirements() {
        String[] requirements = {
                "Yêu cầu thiết kế theo phong cách Nhật Bản",
                "Cần tối ưu không gian và dễ bảo trì",
                "Ưu tiên sử dụng vật liệu cao cấp",
                "Yêu cầu tích hợp hệ thống lọc tự động",
                "Thiết kế phù hợp với không gian sân vườn"
        };

        return requirements[random.nextInt(requirements.length)];
    }
}
