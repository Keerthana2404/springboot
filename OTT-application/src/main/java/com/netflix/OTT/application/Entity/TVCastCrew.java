package com.netflix.OTT.application.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TVCastCrew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference("person-cast-crew")
    private CastCrew person;

    @ManyToOne
    @JoinColumn(name = "show_id")
    @JsonBackReference("show-cast-crew")
    private TVShow show;

    @ManyToOne
    @JoinColumn(name = "season_id")
    @JsonBackReference("season-cast-crew")
    private Seasons season;

    @ManyToOne
    @JoinColumn(name = "episode_id")
    @JsonBackReference("episode-cast-crew")
    private Episodes episode;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "profession", nullable = false)
    private String profession;

}
