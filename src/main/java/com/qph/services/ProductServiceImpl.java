package com.qph.services;

import java.util.List;
import java.util.Optional;

import com.qph.entities.CatalogProductVO;
import com.qph.entities.Product;
import com.qph.entities.Stock;
import com.qph.repository.CatalogRepository;
import com.qph.repository.ProductRepository;
import com.qph.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    public Product create(Product product) {
        Product productCreated = productRepository.save(product);
        List<Stock> stocks = stockRepository.findStockByProductId(productCreated.getProductoId());
        if (stocks.isEmpty()) {
            Stock stockCreated = new Stock();
            stockCreated.setProductoId(productCreated.getProductoId());
            stockCreated.setDisponible(0L);
            stockCreated.setEntradas(0L);
            stockCreated.setSalidas(0L);

            stockRepository.save(stockCreated);
        }

        return productCreated;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "productoId"));
    }

    public Optional<Product> getById (Long id) {
        return productRepository.findById(id);
    }


    public List<CatalogProductVO> findCatalogProducts() {
        return productRepository.findCatalogProducts();
    }

    public List<CatalogProductVO> findAllCatalogProducts() {
        return productRepository.findAllCatalogProducts();
    }

    public void deleteById (Long id) {
        productRepository.deleteById(id);
    }



}
