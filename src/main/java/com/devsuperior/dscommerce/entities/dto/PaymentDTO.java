package com.devsuperior.dscommerce.entities.dto;

import com.devsuperior.dscommerce.entities.Payment;

import java.time.Instant;

public class PaymentDTO {

    private Long id;
    private Instant moment;

    public PaymentDTO(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDTO(Payment p) {
        this.id = p.getId();
        this.moment = p.getMoment();
    }

    public PaymentDTO() {
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
