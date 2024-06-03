package com.qph.controller;

import java.util.List;

import com.qph.entities.Stock;
import com.qph.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET,
    RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/findall")
    public ResponseEntity<List<Stock>> findAllStock() {
        List<Stock> purchases = stockService.findAllStock();
        try {
            return ResponseEntity.ok(purchases);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/findAvailableStockByProductId/{productoId}")
    public ResponseEntity<Long> findAvailableStockByProductId(@PathVariable Long productoId) {
        Long available = stockService.findAvailableStockByProductId(productoId);
        try {
            return ResponseEntity.ok(available);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
