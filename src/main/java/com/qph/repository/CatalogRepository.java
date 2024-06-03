package com.qph.repository;

import java.util.List;

import com.qph.entities.Catalog;
import com.qph.entities.CatalogProductVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CatalogRepository extends JpaRepository<Catalog,Long> {

    @Query(value = "SELECT productoId, codigo, nombre FROM public.productos", nativeQuery = true)
    List<CatalogProductVO> findAllProducts();

    @Query(value = "SELECT productoId, codigo, nombre,precioUnidad FROM public.productos ", nativeQuery = true)
    List<CatalogProductVO> findAllCatalogProducts();

    @Query(value = "SELECT catalogoId, tipo, nombre,estado,documentoId FROM public.catalogos", nativeQuery = true)
    List<Catalog> findAllCatalogs();

    @Query(value = "SELECT catalogoId, tipo, nombre, estado,documentoId FROM public.catalogos WHERE catalogos.tipo = :tipo", nativeQuery = true)
    List<Catalog> findCatalogsByType(Integer tipo);

}
