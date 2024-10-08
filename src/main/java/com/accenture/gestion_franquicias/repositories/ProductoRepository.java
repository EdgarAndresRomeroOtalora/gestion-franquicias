package com.accenture.gestion_franquicias.repositories;

import com.accenture.gestion_franquicias.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
