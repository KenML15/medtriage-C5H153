package com.medtriage.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CitaMedicaDTO {

    private Long id;
    private String codigoCita;

    private Long pacienteId;
    private String pacienteNombre;

    private Long doctorId;
    private String doctorNombre;

    private String nivelPrioridad;
    private String motivoConsulta;
    private String estado;
    private BigDecimal montoConsulta;
    private LocalDateTime fechaCita;

    public CitaMedicaDTO() {
    }

    public CitaMedicaDTO(
            Long id,
            String codigoCita,
            Long pacienteId,
            String pacienteNombre,
            Long doctorId,
            String doctorNombre,
            String nivelPrioridad,
            String motivoConsulta,
            String estado,
            BigDecimal montoConsulta,
            LocalDateTime fechaCita
    ) {
        this.id = id;
        this.codigoCita = codigoCita;
        this.pacienteId = pacienteId;
        this.pacienteNombre = pacienteNombre;
        this.doctorId = doctorId;
        this.doctorNombre = doctorNombre;
        this.nivelPrioridad = nivelPrioridad;
        this.motivoConsulta = motivoConsulta;
        this.estado = estado;
        this.montoConsulta = montoConsulta;
        this.fechaCita = fechaCita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorNombre() {
        return doctorNombre;
    }

    public void setDoctorNombre(String doctorNombre) {
        this.doctorNombre = doctorNombre;
    }

    public String getNivelPrioridad() {
        return nivelPrioridad;
    }

    public void setNivelPrioridad(String nivelPrioridad) {
        this.nivelPrioridad = nivelPrioridad;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getMontoConsulta() {
        return montoConsulta;
    }

    public void setMontoConsulta(BigDecimal montoConsulta) {
        this.montoConsulta = montoConsulta;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }
}