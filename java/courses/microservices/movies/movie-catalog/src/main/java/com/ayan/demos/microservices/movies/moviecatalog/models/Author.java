package com.ayan.demos.microservices.movies.moviecatalog.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {

    private Long id;
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
            Author a = (Author) obj;
            ie = id != null ? id.equals(a.getId()) : a.getId() == null;
        }
        return ie;
    }

}