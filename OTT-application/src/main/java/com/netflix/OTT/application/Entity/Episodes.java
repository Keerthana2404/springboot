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
@Table(name = "episodes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Episodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "episode_id")
    private int episode_id;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    @JsonBackReference
    private Seasons seasons;

    @Column(name = "episode_name")
    private String episode_name;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "rating")
    private float rating;

    @Column(name = "runningtime")
    private int runningtime;

    @Column(name="created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date created_at;

    @OneToMany(mappedBy = "episode")
    @JsonManagedReference("episode-cast-crew")
    private List<TVCastCrew> episodecastcrews ;

    @ManyToMany(mappedBy = "episodes")
    @JsonBackReference("episodes")
    private List<WatchList> watchLists = new ArrayList<>();

    @ManyToMany(mappedBy = "episodes_notif")
    @JsonBackReference("episodes_notif")
    private List<Notifications> episodes_notif = new ArrayList<>();
}