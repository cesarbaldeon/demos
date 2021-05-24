package me.challenge.bcp.repository;

import me.challenge.bcp.domain.CambioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioRepository extends JpaRepository<CambioEntity, Long> {
}
