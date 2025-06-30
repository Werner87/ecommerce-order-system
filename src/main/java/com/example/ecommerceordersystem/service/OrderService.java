package com.example.ecommerceordersystem.service;

import com.example.ecommerceordersystem.dto.OrderItemRequestDto;
import com.example.ecommerceordersystem.dto.OrderItemResponseDto;
import com.example.ecommerceordersystem.dto.OrderRequestDto;
import com.example.ecommerceordersystem.dto.OrderResponseDto;
import com.example.ecommerceordersystem.model.Customer;
import com.example.ecommerceordersystem.model.Order;
import com.example.ecommerceordersystem.model.OrderItem;
import com.example.ecommerceordersystem.model.Product;
import com.example.ecommerceordersystem.repository.CustomerRepository;
import com.example.ecommerceordersystem.repository.OrderRepository;
import com.example.ecommerceordersystem.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public OrderResponseDto placeOrder(OrderRequestDto request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);

        double totalNet = 0;
        double totalGross = 0;

        for (OrderItemRequestDto itemDto : request.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setPriceNet(product.getPriceNet());
            item.setVatRate(product.getVatRate());
            item.setOrder(order);

            double itemNet = product.getPriceNet() * itemDto.getQuantity();
            double itemGross = itemNet * (1 + product.getVatRate());

            totalNet += itemNet;
            totalGross += itemGross;

            order.addOrderItem(item);
        }

        order.setTotalNet(totalNet);
        order.setTotalGross(totalGross);

        Order savedOrder = orderRepository.save(order);

        return mapToResponseDto(savedOrder);
    }

    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return mapToResponseDto(order);
    }

    private OrderResponseDto mapToResponseDto(Order order) {
        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(order.getId());
        response.setCustomerFullName(order.getCustomer().getName() + " " + order.getCustomer().getSurname());
        response.setTotalNet(order.getTotalNet());
        response.setTotalGross(order.getTotalGross());

        List<OrderItemResponseDto> items = order.getOrderItems().stream().map(item -> {
            OrderItemResponseDto dto = new OrderItemResponseDto();
            dto.setProductName(item.getProduct().getName());
            dto.setQuantity(item.getQuantity());
            dto.setPriceNet(item.getPriceNet());
            dto.setVatRate(item.getVatRate());
            dto.setPriceGross(item.getPriceNet() * (1 + item.getVatRate()));
            return dto;
        }).collect(Collectors.toList());

        response.setItems(items);
        return response;
    }
}
