package com.ayan.interviews.transactionpropagation.controllers;

import com.ayan.interviews.transactionpropagation.entities.Transfer;
import com.ayan.interviews.transactionpropagation.exceptions.InsufficientFundsException;
import com.ayan.interviews.transactionpropagation.exceptions.NoSuchAccountException;
import com.ayan.interviews.transactionpropagation.models.ResponseBody;
import com.ayan.interviews.transactionpropagation.services.TransfersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("transfers")
public class TransfersController {

    @Autowired
    private TransfersService transfersService;

    @GetMapping("{id}")
    public ResponseEntity<Transfer> findById(@PathVariable Long id) {
        Optional<Transfer> ot = transfersService.findById(id);
        return ot.isPresent()
                ? ResponseEntity.ok(ot.get())
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<ResponseBody<Transfer>> send(@RequestBody Transfer transfer) {
        HttpStatus hs;
        String em;
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseBody<>(transfersService.send(transfer)));
        } catch (NoSuchAccountException nae) {
            hs = HttpStatus.NOT_FOUND;
            em = nae.getMessage();
        } catch (InsufficientFundsException ife) {
            hs = HttpStatus.CONFLICT;
            em = ife.getMessage();
        }
        return ResponseEntity.status(hs).body(new ResponseBody<>(em));
    }

}
