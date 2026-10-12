package com.medtriage.service;

import com.medtriage.dto.CitaMedicaDTO;
import com.medtriage.dto.CrearCitaDTO;
import com.medtriage.model.CitaMedica;
import com.medtriage.model.Doctor;
import com.medtriage.model.Paciente;
import com.medtriage.repository.CitaMedicaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CitaMedicaService {

    private final CitaMedicaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public CitaMedicaService(CitaMedicaRepository repository) {
        this.repository = repository;
    }

    public List<CitaMedicaDTO> obtenerTodas() {
        return repository
                .findAll(Sort.by(Sort.Direction.DESC, "fechaCita"))
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<CitaMedicaDTO> obtenerPorPrioridad(String prioridad) {
        return repository
                .BusquedaPorNivelOrdenFecha(prioridad)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CitaMedicaDTO crearCita(CrearCitaDTO dto) {

        Doctor doctor = entityManager.find(
                Doctor.class,
                dto.getDoctorId()
        );

        if (doctor == null) {
            throw new RuntimeException("Doctor no encontrado");
        }

        if (!doctor.getDisponible()) {
            throw new RuntimeException(
                    "El doctor no está disponible"
            );
        }

        Paciente paciente = entityManager.find(
                Paciente.class,
                dto.getPacienteId()
        );

        if (paciente == null) {
            throw new RuntimeException("Paciente no encontrado");
        }

        CitaMedica cita = new CitaMedica();

        cita.setCodigoCita(generarCodigo());
        cita.setDoctor(doctor);
        cita.setPaciente(paciente);
        cita.setNivelPrioridad(dto.getNivelPrioridad());
        cita.setMotivoConsulta(dto.getMotivoConsulta());
        cita.setMontoConsulta(dto.getMontoConsulta());
        cita.setFechaCita(dto.getFechaCita());
        cita.setEstado("PENDIENTE");

        return convertirADTO(repository.save(cita));
    }

    public CitaMedicaDTO actualizarEstado(
            Long id,
            String nuevoEstado
    ) {
        if (!nuevoEstado.equals("EN_ATENCION")
                && !nuevoEstado.equals("ATENDIDA")
                && !nuevoEstado.equals("CANCELADA")) {
            throw new RuntimeException("Estado no válido");
        }

        CitaMedica cita = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cita no encontrada")
                );

        cita.setEstado(nuevoEstado);

        return convertirADTO(repository.save(cita));
    }

    private String generarCodigo() {
        return "CIT-2026-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 4)
                        .toUpperCase();
    }

    private CitaMedicaDTO convertirADTO(CitaMedica cita) {
        CitaMedicaDTO dto = new CitaMedicaDTO();

        dto.setId(cita.getId());
        dto.setCodigoCita(cita.getCodigoCita());

        dto.setPacienteId(cita.getPaciente().getId());
        dto.setPacienteNombre(
                cita.getPaciente().getNombreCompleto()
        );

        dto.setDoctorId(cita.getDoctor().getId());
        dto.setDoctorNombre(
                cita.getDoctor().getNombre()
        );

        dto.setNivelPrioridad(cita.getNivelPrioridad());
        dto.setMotivoConsulta(cita.getMotivoConsulta());
        dto.setEstado(cita.getEstado());
        dto.setMontoConsulta(cita.getMontoConsulta());
        dto.setFechaCita(cita.getFechaCita());

        return dto;
    }
}