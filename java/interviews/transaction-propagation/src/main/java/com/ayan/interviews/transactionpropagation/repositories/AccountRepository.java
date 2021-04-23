package com.ayan.interviews.transactionpropagation.repositories;

import com.ayan.interviews.transactionpropagation.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
