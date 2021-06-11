package com.ayan.demos.microservices.movies.moviecatalog.models;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie {

    private Long id;
    private String name;
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