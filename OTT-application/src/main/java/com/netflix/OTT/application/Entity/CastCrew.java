package com.netflix.OTT.application.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "castcrew")
@Data
public class CastCrew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int personId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profession", nullable = false)
    private String profession;

    @OneToMany(mappedBy = "person")
    @JsonManagedReference("person-cast-crew")
    private List<MovieCastCrew> movieCastCrews;

    @OneToMany(mappedBy = "person")
    @JsonManagedReference("person-cast-crew")
    private List<TVCastCrew> tvshowcastcrew;
}
