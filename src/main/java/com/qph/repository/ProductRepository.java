package com.qph.repository;

import java.util.List;

import com.qph.entities.CatalogProductVO;
import com.qph.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT productoId, codigo, nombre FROM public.productos", nativeQuery = true)
    List<CatalogProductVO> findCatalogProducts();

    @Query(value = "SELECT productoId, codigo, nombre FROM public.productos WHERE productos.productoid NOT IN (SELECT productoid FROM inventario)", nativeQuery = true)
    List<CatalogProductVO> findAllCatalogProducts();


}
