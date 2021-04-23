package com.ayan.interviews.transactionpropagation.repositories;

import com.ayan.interviews.transactionpropagation.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
