package com.veterinaria.vetturno.repository;

import com.veterinaria.vetturno.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {
    boolean existsByVeterinarioIdAndFechaHora( Long idVeterinario, LocalDateTime fechaHora);
    List<Cita> findByVeterinarioId(Long idVeterinario);
}
