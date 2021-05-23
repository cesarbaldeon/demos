package me.challenge.bcp.repository;


import me.challenge.bcp.domain.TipoCambioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambioEntity, Long> {

    @Query("SELECT e FROM TipoCambioEntity e WHERE e.monedaOrigen = ?1  and " +
            "e.monedaDestino = ?2")
    TipoCambioEntity findByMoneda(String monedaOrigen, String monedaDestino);

    @Query("SELECT e.id FROM TipoCambioEntity e WHERE e.monedaOrigen = ?1  and " +
            "e.monedaDestino = ?2")
    long findByIdxMoneda(String monedaOrigen, String monedaDestino);

    @Query("SELECT e FROM TipoCambioEntity e WHERE e.monedaOrigen = ?1  and e.monedaDestino = ?2 ORDER BY e.fechaCarga Desc")
    List<TipoCambioEntity> findByMonedas(String monedaOrigen, String monedaDestino);
}
