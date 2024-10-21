package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.mapper.CustomerMapper;
import org.group.koipondbackend.mapper.OrderMapper;
import org.group.koipondbackend.mapper.ProjectMapper;
import org.group.koipondbackend.repository.CustomerRepository;
import org.group.koipondbackend.repository.OrderRepository;
import org.group.koipondbackend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final ProjectRepository projectRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProjectMapper projectMapper;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;

    @Autowired
    public DashboardService(ProjectRepository projectRepository,
                            OrderRepository orderRepository,
                            CustomerRepository customerRepository,
                            ProjectMapper projectMapper,
                            OrderMapper orderMapper,
                            CustomerMapper customerMapper) {
        this.projectRepository = projectRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.projectMapper = projectMapper;
        this.orderMapper = orderMapper;
        this.customerMapper = customerMapper;
    }

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProjects", projectRepository.count());
        stats.put("totalOrders", orderRepository.count());
        stats.put("totalCustomers", customerRepository.count());
        return stats;
    }

    public List<ProjectDTO> getRecentProjects() {
        return projectMapper.toDtoList(projectRepository.findTop5ByOrderByCreatedDateDesc());
    }

    public List<OrderDTO> getRecentOrders() {
        return orderMapper.toDtoList(orderRepository.findTop5ByOrderByCreatedDateDesc());
    }

    public List<CustomerDTO> getTopCustomers() {
        return customerMapper.toDtoList(customerRepository.findTop5ByOrderByTotalSpentDesc());
    }
}
