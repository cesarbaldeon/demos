package me.challenge.bcp.repository;

import me.challenge.bcp.domain.MonedaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonedaRepository extends JpaRepository<MonedaEntity, String> {

}
