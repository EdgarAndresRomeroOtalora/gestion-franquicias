package com.accenture.gestion_franquicias.repositories;

import com.accenture.gestion_franquicias.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
