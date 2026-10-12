package com.medtriage.repository;

import com.medtriage.model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaMedicaRepository
        extends JpaRepository<CitaMedica, Long> {

    @Query("""
        SELECT c
        FROM CitaMedica c
        WHERE UPPER(c.nivelPrioridad) = UPPER(:nivelPrioridad)
        ORDER BY c.fechaCita DESC
    """)
    List<CitaMedica> BusquedaPorNivelOrdenFecha(
            @Param("nivelPrioridad") String nivelPrioridad
    );
}