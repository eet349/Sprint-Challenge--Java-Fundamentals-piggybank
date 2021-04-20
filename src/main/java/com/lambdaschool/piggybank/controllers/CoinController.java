package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {
    @Autowired
    CoinRepository coinRepository;

    @GetMapping(value = "/total", produces = "application/json")
    public ResponseEntity<?> getTotal() {
        List<Coin> allCoins = new ArrayList<>();
        coinRepository.findAll().iterator().forEachRemaining(allCoins::add);

        double total = 0.0;

        for (Coin c : allCoins) {
            int quant = c.getQuantity();
            double val = c.getValue();
            System.out.println(quant + " " + (quant == 1 ? c.getName() : c.getNameplural()));
            total += c.getQuantity() * c.getValue();
        }

        System.out.println("The piggy bank holds " + total);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
