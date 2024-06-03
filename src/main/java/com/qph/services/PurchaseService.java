package com.qph.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.qph.entities.Purchase;
import com.qph.repository.PurchaseRepository;
import com.qph.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PurchaseService {

    @Autowired
    public PurchaseRepository purchaseRepository;

    @Autowired
    public StockRepository stockRepository;

    public List<Purchase> findAllPurchases(){
        return purchaseRepository.findAll();
    }

    public Purchase create(Purchase purchase) {
        purchase.setFechaRegistro(new Date());
        Purchase response=purchaseRepository.save(purchase);

        if(response.getCompraId()!=null){
            stockRepository.updateStockPurchase(response.getProductoId(),response.getCantidad());
            stockRepository.updateAvailableStock(response.getProductoId());
        }
        return response;
    }

    public Optional<Purchase> findById(Long id){
        return purchaseRepository.findById(id);
    }

}
