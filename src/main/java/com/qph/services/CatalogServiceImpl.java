package com.qph.services;

import java.util.List;
import java.util.Optional;

import com.qph.entities.Catalog;
import com.qph.entities.CatalogProductVO;
import com.qph.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl {

    @Autowired
    public CatalogRepository catalogRepository;

    public Optional<Catalog> findById(Long id) {
        return catalogRepository.findById(id);
    }

    public List<Catalog> findAllCatalogs() {
        return catalogRepository.findAllCatalogs();
    }

    public List<Catalog> findCatalogsByType(Integer tipo) {
        return catalogRepository.findCatalogsByType(tipo);
    }

    public List<CatalogProductVO> findAllProducts() {
        return catalogRepository.findAllProducts();
    }

    public List<CatalogProductVO> findAllCatalogProducts() {
        return catalogRepository.findAllCatalogProducts();
    }

}
