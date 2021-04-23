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

@Service
@Log4j2
public class TransactionPropagationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public void required(Transfer transfer) {
        Double sbo = transfer.getOriginAccount().getBalance();
        Double fbo = transfer.getOriginAccount().getBalance() - transfer.getAmount();
        transfer.getOriginAccount().setBalance(fbo);
        transfer.getDestinationAccount().setBalance(transfer.getDestinationAccount().getBalance() + transfer.getAmount());
        accountRepository.save(transfer.getOriginAccount());
        accountRepository.save(transfer.getDestinationAccount());
        if (fbo < 0) {
            throw new InsufficientFundsException(transfer.getAmount(), sbo);
        }
    }

    @Transactional
    public void requiredUsingParent(Transfer transfer) {
        Long ai = transfer.getOriginAccount().getId();
        try {
            transfer.setOriginAccount(accountRepository.findById(ai).get());
            ai = transfer.getDestinationAccount().getId();
            transfer.setDestinationAccount(accountRepository.findById(ai).get());
            transfer.setSendDatetime(LocalDateTime.now());
            transferRepository.save(transfer);
            required(transfer);
        } catch (NoSuchElementException nee) {
            throw new NoSuchAccountException(ai);
        }
    }

    public void requiredUsingEachOwner(Transfer transfer) {
        Long ai = transfer.getOriginAccount().getId();
        try {
            transfer.setOriginAccount(accountRepository.findById(ai).get());
            ai = transfer.getDestinationAccount().getId();
            transfer.setDestinationAccount(accountRepository.findById(ai).get());
            transfer.setSendDatetime(LocalDateTime.now());
            transferRepository.save(transfer);
            required(transfer);
        } catch (NoSuchElementException nee) {
            throw new NoSuchAccountException(ai);
        }
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public void supports(Transfer transfer) {
        Double sbo = transfer.getOriginAccount().getBalance();
        Double fbo = transfer.getOriginAccount().getBalance() - transfer.getAmount();
        transfer.getOriginAccount().setBalance(fbo);
        transfer.getDestinationAccount().setBalance(transfer.getDestinationAccount().getBalance() + transfer.getAmount());
        accountRepository.save(transfer.getOriginAccount());
        accountRepository.save(transfer.getDestinationAccount());
        if (fbo < 0) {
            throw new InsufficientFundsException(transfer.getAmount(), sbo);
        }
    }

    @Transactional
    public void supportsUsingParent(Transfer transfer) {
        Long ai = transfer.getOriginAccount().getId();
        try {
            transfer.setOriginAccount(accountRepository.findById(ai).get());
            ai = transfer.getDestinationAccount().getId();
            transfer.setDestinationAccount(accountRepository.findById(ai).get());
            transfer.setSendDatetime(LocalDateTime.now());
            transferRepository.save(transfer);
            supports(transfer);
        } catch (NoSuchElementException nee) {
            throw new NoSuchAccountException(ai);
        }
    }

    public void supportsAny(Transfer transfer) {
        Long ai = transfer.getOriginAccount().getId();
        try {
            transfer.setOriginAccount(accountRepository.findById(ai).get());
            ai = transfer.getDestinationAccount().getId();
            transfer.setDestinationAccount(accountRepository.findById(ai).get());
            transfer.setSendDatetime(LocalDateTime.now());
            transferRepository.save(transfer);
            supports(transfer);
        } catch (NoSuchElementException nee) {
            throw new NoSuchAccountException(ai);
        }
    }

}
