package com.qph.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.qph.entities.Catalog;
import com.qph.entities.CatalogProductVO;
import com.qph.services.CatalogServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CatalogControllerTest {

    @Mock
    private CatalogServiceImpl catalogService;

    @InjectMocks
    private CatalogController catalogController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {

        Optional<Catalog> catalogOptional = Optional.of(new Catalog());
        when(catalogService.findById(anyLong())).thenReturn(catalogOptional);
        ResponseEntity<Optional<Catalog>> response = catalogController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(catalogOptional, response.getBody());
    }

    @Test
    public void testFindAllCatalogs() {

        List<Catalog> catalogList = new ArrayList<>();
        when(catalogService.findAllCatalogs()).thenReturn(catalogList);

        ResponseEntity<List<Catalog>> response = catalogController.findAllCatalogs();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(catalogList, response.getBody());
    }

    @Test
    public void testFindCatalogsByType() {

        List<Catalog> catalogList = new ArrayList<>();
        when(catalogService.findCatalogsByType(anyInt())).thenReturn(catalogList);
        ResponseEntity<List<Catalog>> response = catalogController.findCatalogsByType(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(catalogList, response.getBody());
    }

    @Test
    public void testCatalogProduct() {

        List<CatalogProductVO> productList = new ArrayList<>();
        when(catalogService.findAllProducts()).thenReturn(productList);
        ResponseEntity<List<CatalogProductVO>> response = catalogController.catalogProduct();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    public void testCatalogAllProduct() {

        List<CatalogProductVO> productList = new ArrayList<>();
        when(catalogService.findAllCatalogProducts()).thenReturn(productList);
        ResponseEntity<List<CatalogProductVO>> response = catalogController.catalogAllProduct();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }
}