package com.ayan.interviews.transactionpropagation.services;

import com.ayan.interviews.transactionpropagation.entities.Transfer;
import com.ayan.interviews.transactionpropagation.exceptions.InsufficientFundsException;
import com.ayan.interviews.transactionpropagation.exceptions.NoSuchAccountException;
import com.ayan.interviews.transactionpropagation.repositories.AccountRepository;
import com.ayan.interviews.transactionpropagation.repositories.TransferRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransfersService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Transfer> findById(Long id) {
        return transferRepository.findById(id);
    }

    @Transactional
    public Transfer send(Transfer transfer) {
        Long ai = transfer.getOriginAccount().getId();
        try {
            transfer.setOriginAccount(accountRepository.findById(ai).get());
            ai = transfer.getDestinationAccount().getId();
            transfer.setDestinationAccount(accountRepository.findById(ai).get());
            transfer.setSendDatetime(LocalDateTime.now());
            /**
             * Just for testing how transactions work we will apply follow logic
             * Basically if someone call this service by passing a amount to transfer
             * bigger than the amount on balance origin account, it will throws and exception.
             * But as you can notice this exception is throw after the transfer was save to
             * database. So by using the Transactional notation if an exception occurs
             * it will automatically do a roll back.
             */
            transferRepository.save(transfer);
            Double fbo = transfer.getOriginAccount().getBalance() - transfer.getAmount();
            if (fbo < 0) {
                throw new InsufficientFundsException(transfer.getAmount(), transfer.getOriginAccount().getBalance());
            }
            transfer.getOriginAccount().setBalance(fbo);
            transfer.getDestinationAccount().setBalance(transfer.getDestinationAccount().getBalance() + transfer.getAmount());
            accountRepository.save(transfer.getOriginAccount());
            accountRepository.save(transfer.getDestinationAccount());
            return transfer;
        } catch (NoSuchElementException nee) {
            throw new NoSuchAccountException(ai);
        }
    }

}
