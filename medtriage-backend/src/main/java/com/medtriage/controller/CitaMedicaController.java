package com.medtriage.controller;

import com.medtriage.dto.CitaMedicaDTO;
import com.medtriage.dto.CrearCitaDTO;
import com.medtriage.service.CitaMedicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
@CrossOrigin(origins = "http://localhost:4200")
public class CitaMedicaController {

    private final CitaMedicaService citaMedicaService;

    public CitaMedicaController(
            CitaMedicaService citaMedicaService
    ) {
        this.citaMedicaService = citaMedicaService;
    }


    @GetMapping
    public ResponseEntity<List<CitaMedicaDTO>> obtenerTodas() {
        List<CitaMedicaDTO> citas =
                citaMedicaService.obtenerTodas();

        return ResponseEntity.ok(citas);
    }


    @GetMapping("/prioridad/{prioridad}")
    public ResponseEntity<List<CitaMedicaDTO>> obtenerPorPrioridad(
            @PathVariable String prioridad
    ) {
        List<CitaMedicaDTO> citas =
                citaMedicaService.obtenerPorPrioridad(prioridad);

        return ResponseEntity.ok(citas);
    }


    @PostMapping
    public ResponseEntity<CitaMedicaDTO> crearCita(
            @RequestBody CrearCitaDTO dto
    ) {
        CitaMedicaDTO citaCreada =
                citaMedicaService.crearCita(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(citaCreada);
    }


    @PatchMapping("/{id}/estado")
    public ResponseEntity<CitaMedicaDTO> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado
    ) {
        CitaMedicaDTO citaActualizada =
                citaMedicaService.actualizarEstado(
                        id,
                        nuevoEstado
                );

        return ResponseEntity.ok(citaActualizada);
    }
}