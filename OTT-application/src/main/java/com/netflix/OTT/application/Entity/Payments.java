package com.netflix.OTT.application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Payments {

    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;

    public enum PaymentType {
        netbanking,
        creditcard,
        debitcard,
        upi
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_type")
    private PaymentType pay_type;

    @Column(name = "amount")
    private int amount;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("payments")
    private User user_payments;

    @OneToOne
    @JoinColumn(name = "subs_id")
    @JsonBackReference("subscriptions")
    private Subscriptions subscriptions;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_at;

    @PrePersist
    public void onCreate()
    {
        this.created_at=LocalDateTime.now();
    }

}
