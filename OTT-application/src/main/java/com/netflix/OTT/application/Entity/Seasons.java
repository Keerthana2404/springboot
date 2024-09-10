package com.netflix.OTT.application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "season")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seasons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_id")
    private int season_id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    @JsonBackReference
    private TVShow show;

    @Column(name = "season_number")
    private int season_number;

    @Column(name = "start_year")
    private int year;

    @Column(name = "director")
    private String director;

    @Column(name = "producer")
    private String producer;

    @Column(name="created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date created_at;

    @OneToMany(mappedBy = "seasons", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Episodes> episodes;

    @OneToMany(mappedBy = "season")
    @JsonManagedReference("season-cast-crew")
    private List<TVCastCrew> seasoncastcrews;

    @ManyToMany(mappedBy = "seasons")
    @JsonBackReference("seasons")
    private List<WatchList> watchLists = new ArrayList<>();

    @ManyToMany(mappedBy = "seasons_notif")
    @JsonBackReference("seasons_notif")
    private List<Notifications> seasons_notif = new ArrayList<>();
}
