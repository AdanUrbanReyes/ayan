package com.ayan.demos.microservices.movies.movieinformation.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;

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
