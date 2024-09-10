package com.netflix.OTT.application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Getter
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Setter
    @Getter
    @Column(name="email", nullable = false)
    private String email;

    @Setter
    @Getter
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Column(name="phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user")
    private List<UserProfile> userProfiles;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("device")
    private List<Devices> device;

    @OneToMany(mappedBy = "user_payments", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("payments")
    private List<Payments> payments;

    @ManyToOne
    @JoinColumn(name = "subs_id", nullable = false)
    @JsonBackReference("subscriptions")
    private Subscriptions subscriptions;

    @Setter
    @Getter
    @Column(name="created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date created_at;

}
