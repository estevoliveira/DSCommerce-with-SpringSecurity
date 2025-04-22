package com.devsuperior.dscommerce.entities.dto;

import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;

    private ClientMinDTO client;
    private PaymentDTO payment;

    @NotEmpty(message = "Deve ter pelo menos um item!")
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientMinDTO client, PaymentDTO paymentDTO) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = paymentDTO;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.moment = order.getMoment();
        this.status = order.getStatus();
        this.client = new ClientMinDTO(order.getClient());
        this.payment = (order.getPayment() == null)? null : new PaymentDTO(order.getPayment());
        for(OrderItem item: order.getItems()){
            this.items.add(new OrderItemDTO(item));
        }
    }

    public OrderDTO() {
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientMinDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public Double getTotal(){
        double sum =0.0;
        for(OrderItemDTO dto : items){
            sum+=dto.getSubTotal();
        }
        return sum;
    }
}
