package com.ayan.interviews.transactionpropagation.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "origin_account", referencedColumnName = "id", nullable = false)
    private Account originAccount;
    @ManyToOne
    @JoinColumn(name = "destination_account", referencedColumnName = "id", nullable = false)
    private Account destinationAccount;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "send_datetime", nullable = false)
    private LocalDateTime sendDatetime;

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean ie = this == obj;
        if (!ie && obj != null && obj.getClass().equals(getClass())) {
            Transfer t = (Transfer) obj;
            ie = t.getId() != null ? t.getId().equals(id) : id == null;
        }
        return ie;
    }

}
