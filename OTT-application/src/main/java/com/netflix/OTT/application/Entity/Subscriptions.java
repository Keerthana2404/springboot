package com.netflix.OTT.application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "subscriptions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subs_id")
    private int subs_id;

    @Column(name = "plan_name")
    private String plan_name;

    @Column(name = "max_profiles")
    private int max_profile;

    @Column(name = "price")
    private int price;

    @CreationTimestamp
    @Column(name = "valid_from", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime valid_from;

    @Column(name = "valid_till", columnDefinition = "TIMESTAMP")
    private LocalDateTime valid_till;

    @OneToMany(mappedBy = "subscriptions", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("subscriptions")
    private List<User> users;

    private int type;

}