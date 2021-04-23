package com.ayan.interviews.transactionpropagation.controllers;

import com.ayan.interviews.transactionpropagation.entities.Transfer;
import com.ayan.interviews.transactionpropagation.exceptions.InsufficientFundsException;
import com.ayan.interviews.transactionpropagation.models.ResponseBody;
import com.ayan.interviews.transactionpropagation.services.TransactionPropagationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("transaction-propagation")
public class TransactionPropagationController {

    @Autowired
    private TransactionPropagationService transactionPropagationService;

    /**
     * This is the FIRST path if you are using transactional required type.
     * In this case this method will call some other method that
     * for commodity we call TransactionPropagationService.requiredUsingParent,
     * that method is mark with <code>@Transactional</code> so it will create a transaction
     * and pass it to other method that we call TransactionPropagationService.required
     * as that method is mark as <code>@Transactional(Transactional.TxType.REQUIRED)</code>
     * It will take TransactionPropagationService.requiredUsingParent transaction and use it.
     * To better understand this you could send a request where the transfer amount
     * is greater than the origin account balance.
     * If you do that and review how this code work (
     * TransactionPropagationService.requiredUsingParent method; who create the transaction insert over transfer table first
     * and then TransactionPropagationService.required method; who use TransactionPropagationService.requiredUsingParent transaction update origin and destination account balance
     * ), you will notice that the expected behavior is that as we will get and exception on
     * TransactionPropagationService.required method the transaction will do the roll back of the operation inside this method
     * and TransactionPropagationService.requiredUsingParent method. So at the end you will have any new register over transfer table
     * and have not modifications over origin and destination account.
     * Follow a example request to test:
     * <code>
     * {
     * "amount": 50000,
     * "originAccount": {
     * "id": 1
     * },
     * "destinationAccount": {
     * "id": 2
     * },
     * "description": "Happy birthday!"
     * }
     * </code>
     * Note: we must be sure that origin account has not 50000 of funds so we will get an error, and
     * then we will notice the data base get not alter (which is correct)
     *
     * @param transfer Transfer
     */
    @PostMapping("required-using-parent")
    public ResponseEntity<ResponseBody<Transfer>> requiredUsingParent(@RequestBody Transfer transfer) {
        HttpStatus hs;
        String em;
        try {
            transactionPropagationService.requiredUsingParent(transfer);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBody<>(transfer));
        } catch (NoSuchElementException nee) {
            hs = HttpStatus.NOT_FOUND;
            em = nee.getMessage();
        } catch (InsufficientFundsException ife) {
            hs = HttpStatus.CONFLICT;
            em = ife.getMessage();
        }
        return ResponseEntity.status(hs).body(new ResponseBody<>(em));
    }

    /**
     * This is the SECOND path if you are using transactional required type.
     * In this case this method will call some other method that
     * for commodity we call TransactionPropagationService.requiredUsingParent,
     * that method IS NOT MARK with <code>@Transactional</code> so it WILL NOT CREATE A TRANSACTION
     * and then will call other method that we call TransactionPropagationService.required
     * as that method is mark as <code>@Transactional(Transactional.TxType.REQUIRED)</code>
     * It will create its own transaction and use it.
     * To better understand this you could send a request where the transfer amount
     * is greater than the origin account balance.
     * If you do that and review how this code work (
     * TransactionPropagationService.requiredUsingParent method; who NOT CREATE a transaction insert over transfer table first
     * and then TransactionPropagationService.required method; who CREATE ITS OWN transaction update origin and destination account balance
     * ), you will notice that the expected behavior is that as we will get and exception on
     * TransactionPropagationService.required method the transaction will do the roll back JUST IN  the operations inside this method.
     * So at the end you will have THE NEW register over transfer table BUT have not modifications over origin and destination account.
     * Follow a example request to test:
     * <code>
     * {
     * "amount": 50000,
     * "originAccount": {
     * "id": 1
     * },
     * "destinationAccount": {
     * "id": 2
     * },
     * "description": "Happy birthday!"
     * }
     * </code>
     * Note: we must be sure that origin account has not 50000 of funds so we will get an error, and
     * then we will notice the data base has a new transfer record but the origin and destination balance wasn't updated
     *
     * @param transfer Transfer
     */
    @PostMapping("required-using-each-owner")
    public ResponseEntity<ResponseBody<Transfer>> requiredUsingEachOwner(@RequestBody Transfer transfer) {
        HttpStatus hs;
        String em;
        try {
            transactionPropagationService.requiredUsingEachOwner(transfer);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBody<>(transfer));
        } catch (NoSuchElementException nee) {
            hs = HttpStatus.NOT_FOUND;
            em = nee.getMessage();
        } catch (InsufficientFundsException ife) {
            hs = HttpStatus.CONFLICT;
            em = ife.getMessage();
        }
        return ResponseEntity.status(hs).body(new ResponseBody<>(em));
    }

    /**
     * This is the FIRST path if you are using transactional supports type.
     * In this case this method will call some other method that
     * for commodity we call TransactionPropagationService.supportsUsingParent,
     * that method is mark with <code>@Transactional</code> so it will create a transaction
     * and pass it to other method that we call TransactionPropagationService.supports
     * as that method is mark as <code>@Transactional(Transactional.TxType.SUPPORTS)</code>
     * It will take TransactionPropagationService.supportsUsingParent transaction and use it.
     * To better understand this you could send a request where the transfer amount
     * is greater than the origin account balance.
     * If you do that and review how this code work (
     * TransactionPropagationService.supportsUsingParent method; who create the transaction insert over transfer table first
     * and then TransactionPropagationService.supports method; who use TransactionPropagationService.supportsUsingParent transaction update origin and destination account balance
     * ), you will notice that the expected behavior is that as we will get and exception on
     * TransactionPropagationService.supports method the transaction will do the roll back of the operation inside this method
     * and TransactionPropagationService.supportsUsingParent method. So at the end you will have any new register over transfer table
     * and have not modifications over origin and destination account.
     * Follow a example request to test:
     * <code>
     * {
     * "amount": 50000,
     * "originAccount": {
     * "id": 1
     * },
     * "destinationAccount": {
     * "id": 2
     * },
     * "description": "Happy birthday!"
     * }
     * </code>
     * Note: we must be sure that origin account has not 50000 of funds so we will get an error, and
     * then we will notice the data base get not alter (which is correct)
     *
     * @param transfer Transfer
     */
    @PostMapping("support-using-parent")
    public ResponseEntity<ResponseBody<Transfer>> supportsUsingParent(@RequestBody Transfer transfer) {
        HttpStatus hs;
        String em;
        try {
            transactionPropagationService.supportsUsingParent(transfer);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBody<>(transfer));
        } catch (NoSuchElementException nee) {
            hs = HttpStatus.NOT_FOUND;
            em = nee.getMessage();
        } catch (InsufficientFundsException ife) {
            hs = HttpStatus.CONFLICT;
            em = ife.getMessage();
        }
        return ResponseEntity.status(hs).body(new ResponseBody<>(em));
    }

    /**
     * This is the SECOND path if you are using transactional supports type.
     * In this case this method will call some other method that
     * for commodity we call TransactionPropagationService.supportsAny,
     * that method IS NOT MARK with <code>@Transactional</code> so it WONT CREATE a transaction
     * and then call  other method that we call TransactionPropagationService.supports
     * as that method is mark as <code>@Transactional(Transactional.TxType.SUPPORTS)</code>
     * It will notice there is no transaction from TransactionPropagationService.supportsAny so it runs without any transaction.
     * To better understand this you could send a request where the transfer amount
     * is greater than the origin account balance.
     * If you do that and review how this code work (
     * TransactionPropagationService.supportsAny method insert over transfer table first
     * and then TransactionPropagationService.supports method update origin and destination account balance
     * ), you will notice that the expected behavior is that as we will get and exception on
     * TransactionPropagationService.supports method and neither method is inside of a transaction you will have any new register
     * over transfer table and modifications over origin and destination account.
     * Follow a example request to test:
     * <code>
     * {
     * "amount": 50000,
     * "originAccount": {
     * "id": 1
     * },
     * "destinationAccount": {
     * "id": 2
     * },
     * "description": "Happy birthday!"
     * }
     * </code>
     * Note: we must be sure that origin account has not 50000 of funds so we will get an error, and
     * then we will notice the data base has new transfer record and modifications over account table
     *
     * @param transfer Transfer
     */
    @PostMapping("supports-any")
    public ResponseEntity<ResponseBody<Transfer>> supportsAny(@RequestBody Transfer transfer) {
        HttpStatus hs;
        String em;
        try {
            transactionPropagationService.supportsAny(transfer);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBody<>(transfer));
        } catch (NoSuchElementException nee) {
            hs = HttpStatus.NOT_FOUND;
            em = nee.getMessage();
        } catch (InsufficientFundsException ife) {
            hs = HttpStatus.CONFLICT;
            em = ife.getMessage();
        }
        return ResponseEntity.status(hs).body(new ResponseBody<>(em));
    }

}
