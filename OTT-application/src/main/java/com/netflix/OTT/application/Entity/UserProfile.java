package com.netflix.OTT.application.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Table(name = "userprofile")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfile {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profile_id;

    @Setter
    @Getter
    @Column(name="name", nullable = false)
    private String name;

    @Getter
    @Column(name="country", nullable = false)
    private String country;

    @Setter
    @Getter
    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user")
    private User user;

    @Setter
    @Getter
    @Column(name="created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date created_at;

    public enum ProfileType {
        ADULT,
        CHILD
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ProfileType type;


    @Setter
    @Getter
    @Column(name = "pref_lang")
    private String pref_lang;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "user_fav_movies",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    @JsonIgnoreProperties("userProfiles")
    private List<Movie> favoriteMovies = new ArrayList<>();

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "user_fav_shows",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "show_id")
    )
    @JsonIgnoreProperties("userProfiles")
    private List<TVShow> favoriteShows= new ArrayList<>();


    @OneToMany(mappedBy = "userProfile")
    @JsonManagedReference("watchlist")
    private List<WatchList> watchLists = new ArrayList<>();

    @OneToMany(mappedBy = "notifprofile")
    @JsonManagedReference("notifications")
    private List<Notifications> notifications = new ArrayList<>();


}
