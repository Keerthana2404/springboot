package com.netflix.OTT.application.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notif_id;

    @Column(name = "message")
    private String message;

    @Column(name = "isWatched")
    private Boolean isWatched;

    @Column(name="created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(name = "update_at")
    private LocalDateTime updated_at;


    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonBackReference("notifications")
    private UserProfile notifprofile;


    @ManyToMany
    @JoinTable(
            name = "notif_episodes",
            joinColumns = @JoinColumn(name = "notif_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id")
    )
    @JsonIgnoreProperties("episodes_notif")
    @JsonBackReference("episodes")
    private List<Episodes> episodes_notif = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "notif_seasons",
            joinColumns = @JoinColumn(name = "notif_id"),
            inverseJoinColumns = @JoinColumn(name = "season_id")
    )
    @JsonIgnoreProperties("seasons_notif")
    @JsonBackReference("season")
    private List<Seasons> seasons_notif = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "notif_tvshows",
            joinColumns = @JoinColumn(name = "notif_id"),
            inverseJoinColumns = @JoinColumn(name = "show_id")
    )

    @JsonIgnoreProperties("shows_notif")
    @JsonBackReference("shows")
    private List<TVShow> shows_notif = new ArrayList<>();


    @PrePersist
    public void onCreate()
    {
        this.isWatched=false;
    }

    @PreUpdate
    protected void onUpdate()
    {
        if(!this.isWatched)
        {
            this.message="REMAINDER!";
        }
        this.updated_at=LocalDateTime.now();
    }

}
