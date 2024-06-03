package com.qph.controller;

import java.util.List;
import java.util.Optional;

import com.qph.entities.Catalog;
import com.qph.entities.CatalogProductVO;
import com.qph.services.CatalogServiceImpl;
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
@CrossOrigin(origins = "*", methods= {RequestMethod.OPTIONS,RequestMethod.GET,
    RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    public CatalogServiceImpl catalogService;

    @GetMapping(name = "/getbyid/{id}", value = "{id}")
    public ResponseEntity<Optional<Catalog>> findById(@PathVariable Long id) {
        Optional<Catalog> productResponse = catalogService.findById(id);
        try {
            return ResponseEntity.ok(productResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/findAllCatalogs")
    public ResponseEntity<List<Catalog>> findAllCatalogs() {
        List<Catalog> productListAll = catalogService.findAllCatalogs();
        try {
            return ResponseEntity.ok(productListAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/findCatalogsByType/{type}")
    public ResponseEntity<List<Catalog>> findCatalogsByType(@PathVariable Integer type) {
        List<Catalog> productListAll = catalogService.findCatalogsByType(type);
        try {
            return ResponseEntity.ok(productListAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/catalogProduct")
    public ResponseEntity<List<CatalogProductVO>> catalogProduct(){
        List<CatalogProductVO> productListAll = catalogService.findAllProducts();
        try {
            return ResponseEntity.ok(productListAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/catalogAllProduct")
    public ResponseEntity<List<CatalogProductVO>> catalogAllProduct(){
        List<CatalogProductVO> productListAll = catalogService.findAllCatalogProducts();
        try {
            return ResponseEntity.ok(productListAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

}
