package com.qph.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.qph.entities.Purchase;
import com.qph.services.PurchaseService;
import com.qph.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET,
    RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private StockService stockService;

    @GetMapping("/findall")
    public ResponseEntity<List<Purchase>> findAllPurchases() {
        List<Purchase> purchases = purchaseService.findAllPurchases();
        try {
            return ResponseEntity.ok(purchases);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

     @PostMapping("/save")
    @ResponseBody
     public ResponseEntity<Purchase> saveProduct(@RequestBody Purchase purchase) {
        Purchase obj = purchaseService.create(purchase);

        try {
            return ResponseEntity.created(new URI("/api/qph/save" + obj.getCompraId()))
                .body(obj);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(name = "/getbyid/{id}", value = "{id}")
    public ResponseEntity<Optional<Purchase>> findById(@PathVariable Long id) {
        Optional<Purchase> purchase = purchaseService.findById(id);
        try {
            return ResponseEntity.ok(purchase);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
