package com.qph.repository;

import java.util.List;

import com.qph.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock,Long> {

    @Query(value = "SELECT * FROM public.stock where productoId=:productoId", nativeQuery = true)
    List<Stock> findStockByProductId(Long productoId);

    @Query(value = "SELECT disponible FROM public.stock where productoId=:productoId", nativeQuery = true)
    Long findAvailableStockByProductId(Long productoId);

    @Modifying
    @Query(value = "update public.stock set entradas=entradas+:cantidad where productoId=:productoId", nativeQuery = true)
    void updateStockPurchase(Long productoId, Long cantidad );

    @Modifying
    @Query(value = "update public.stock set salidas=salidas+:cantidad where productoId=:productoId", nativeQuery = true)
    void updateStocksale(Long productoId, Long cantidad );

    @Modifying
    @Query(value = "update public.stock set disponible=entradas-salidas where productoId=:productoId", nativeQuery = true)
    void updateAvailableStock(Long productoId);
}
