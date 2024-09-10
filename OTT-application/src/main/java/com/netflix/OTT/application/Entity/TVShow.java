package com.netflix.OTT.application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Table(name = "tvshow")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class TVShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id")
    private int show_id;

    @Column(name = "show_name")
    private String show_name;

    @Column(name = "language")
    private String language;

    @Column(name = "start_year")
    private int year;

    @Column(name = "genre")
    private String genre;

    @Column(name = "description")
    private String description;

    @Column(name="created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date created_at;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Seasons> seasons;

    @OneToMany(mappedBy = "show")
    @JsonManagedReference("show-cast-crew")
    private List<TVCastCrew> showcastcrew;

    @ManyToMany(mappedBy = "favoriteShows")
    @JsonIgnoreProperties("favshows")
    @JsonBackReference("profiles")
    private List<UserProfile> userProfiles = new ArrayList<>();

    @ManyToMany(mappedBy = "tvShows")
    @JsonBackReference("shows")
    private List<WatchList> watchLists = new ArrayList<>();

    @ManyToMany(mappedBy = "shows_notif")
    @JsonBackReference("shows_notif")
    private List<Notifications> shows_notif = new ArrayList<>();
}
