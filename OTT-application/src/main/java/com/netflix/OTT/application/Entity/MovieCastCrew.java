package com.netflix.OTT.application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MovieCastCrew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference("person-cast-crew")
    private CastCrew person;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonBackReference("movie-cast-crew")
    private Movie movie;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "profession", nullable = false)
    private String profession;

}
