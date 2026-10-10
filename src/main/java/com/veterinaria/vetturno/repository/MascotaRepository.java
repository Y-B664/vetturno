package com.veterinaria.vetturno.repository;

import com.veterinaria.vetturno.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota,Long> {
    List<Mascota> findByPropietarioId(Long id);
}
