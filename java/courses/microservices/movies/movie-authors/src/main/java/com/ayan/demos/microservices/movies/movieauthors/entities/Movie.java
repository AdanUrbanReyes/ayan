package com.ayan.demos.microservices.movies.movieauthors.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "movie_author",
            joinColumns = @JoinColumn(name = "movie"),
            inverseJoinColumns = @JoinColumn(name = "author"))
    private Set<Author> authors;

    @Override
    public int hashCode() {
        int hc = id == null ? 0 : id.hashCode();
        return hc;
    }

    @Override
    public boolean equals(Object obj) {
        boolean ie = this == obj;
        if (!ie && obj instanceof Movie) {
            Movie m = (Movie) obj;
            ie = id != null ? id.equals(m.getId()) : m.getId() == null;
        }
        return ie;
    }

}
