package com.ayan.demos.microservices.movies.movieauthors.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "author")
public class Author {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false, length = 110)
    @NotBlank(message = "Full name is required")
    @Size(min = 7, max = 110, message = "Full name should be between 7 and 110 characters")
    private String fullName;

    @Override
    public int hashCode() {
        int hc = id == null ? 0 : id.hashCode();
        return hc;
    }

    @Override
    public boolean equals(Object obj) {
        boolean ie = this == obj;
        if (!ie && obj instanceof Author) {
            Author m = (Author) obj;
            ie = id != null ? id.equals(m.getId()) : m.getId() == null;
        }
        return ie;
    }

}
