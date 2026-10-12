package com.medtriage.dto;

import java.time.LocalDate;

public class CitaMedicaDto {
    private int id;
    private int codigoCita;
    private String nombrePaciente;
    private String nombreDoctor;
    private String nivelPrioridad;
    private String estado;
    private double montoConsulta;
    private LocalDate fechaCita;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCodigoCita() {
        return codigoCita;
    }
    public void setCodigoCita(int codigoCita) {
        this.codigoCita = codigoCita;
    }
    public String getNombrePaciente() {
        return nombrePaciente;
    }
    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
    public String getNombreDoctor() {
        return nombreDoctor;
    }
    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }
    public String getNivelPrioridad() {
        return nivelPrioridad;
    }
    public void setNivelPrioridad(String nivelPrioridad) {
        this.nivelPrioridad = nivelPrioridad;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public double getMontoConsulta() {
        return montoConsulta;
    }
    public void setMontoConsulta(double montoConsulta) {
        this.montoConsulta = montoConsulta;
    }
    public LocalDate getFechaCita() {
        return fechaCita;
    }
    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }


    
}
