package com.qph.services;

import java.util.List;
import com.qph.entities.Stock;
import com.qph.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    public StockRepository stockRepository;

    public List<Stock> findAllStock() {
        return stockRepository.findAll();
    }

    public void updateStockPurchase(Long productoId, Long cantidad) {
        stockRepository.updateStockPurchase(productoId, cantidad);
    }

    public Long findAvailableStockByProductId(Long productoId){
        return stockRepository.findAvailableStockByProductId(productoId);
    }
}
