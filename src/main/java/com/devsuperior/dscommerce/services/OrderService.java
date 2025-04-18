package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.dto.OrderItemDTO;
import com.devsuperior.dscommerce.entities.*;
import com.devsuperior.dscommerce.repositories.OrderItemRepository;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO save(OrderDTO dto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        User user = userService.getUserLogged();
        order.setClient(user);
        for(OrderItemDTO i: dto.getItems()){
            Product product =  productRepository.getReferenceById(i.getProductId());
            OrderItem orderItem = new OrderItem(order,product,i.getQuantity(),product.getPrice());
            order.getItems().add(orderItem);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());
        return new OrderDTO(order);

    }
}
