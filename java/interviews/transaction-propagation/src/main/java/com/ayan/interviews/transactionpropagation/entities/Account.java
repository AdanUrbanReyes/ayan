package com.ayan.interviews.transactionpropagation.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;
    @Column(name = "balance", nullable = false)
    private Double balance;


    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean ie = this == obj;
        if (!ie && obj != null && obj.getClass().equals(getClass())) {
            Account e = (Account) obj;
            ie = e.getId() != null ? e.getId().equals(id) : id == null;
        }
        return ie;
    }

}
