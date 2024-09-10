package com.netflix.OTT.application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "watchlist")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int watchId;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonBackReference("watchlist")
    private UserProfile userProfile;

    @ManyToMany
    @JoinTable(
            name = "watchlist_movies",
            joinColumns = @JoinColumn(name = "watch_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    @JsonIgnoreProperties("movies")
    private List<Movie> movies = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "watchlist_episodes",
            joinColumns = @JoinColumn(name = "watch_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id")
    )
    @JsonIgnoreProperties("episodes")
    private List<Episodes> episodes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "watchlist_seasons",
            joinColumns = @JoinColumn(name = "watch_id"),
            inverseJoinColumns = @JoinColumn(name = "season_id")
    )
    @JsonIgnoreProperties("seasons")
    private List<Seasons> seasons = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "watchlist_tvshows",
            joinColumns = @JoinColumn(name = "watch_id"),
            inverseJoinColumns = @JoinColumn(name = "show_id")
    )
    @JsonIgnoreProperties("shows")
    private List<TVShow> tvShows = new ArrayList<>();

    private LocalDateTime lastWatched;


    public enum WatchStatus {
        started,
        ended,
        playing,
        paused,
        buffering
    }

    @Enumerated(EnumType.STRING)
    private WatchStatus status;

    private LocalTime watched_time_stamp;

    @PrePersist
    protected void onCreate() {
        this.lastWatched = LocalDateTime.now();

    }

    @PreUpdate
    protected void onUpdate() {
        this.lastWatched = LocalDateTime.now();

    }
}
