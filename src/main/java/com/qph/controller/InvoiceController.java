package com.qph.controller;

import java.net.URI;
import java.util.List;

import com.qph.entities.Invoice;
import com.qph.services.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET,
    RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping("/api/invoices")
public class InvoiceController {


    @Autowired
    private InvoiceServiceImpl salesService;

    @GetMapping("/findall")
    public ResponseEntity<List<Invoice>> findAllSales() {
        List<Invoice> productList = salesService.findAllInvoices();
        try {
            return ResponseEntity.ok(productList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice) {
        Invoice obj = salesService.create(invoice);

        try {
            return ResponseEntity.created(new URI("/api/invoices/save" + obj.getId()))
                .body(obj);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
