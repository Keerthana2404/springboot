package com.netflix.OTT.application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "devices")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Devices {

    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int device_id;

    public enum devicetype
    {
        tv,
        mobile,
        laptop,
        tablet,
        computer
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("device")
    private User device;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private devicetype device_type;

    @Column(name = "isActive")
    private Boolean isActive;

}
