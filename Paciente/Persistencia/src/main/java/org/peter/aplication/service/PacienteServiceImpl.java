package org.peter.aplication.service;

import org.peter.domain.Paciente;
import org.peter.interfaces.PacienteRepository;

import java.util.List;

public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente findById(int id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public void save(Paciente paciente) {
        validarPaciente(paciente);
        pacienteRepository.save(paciente);
    }

    @Override
    public void update(Paciente paciente) {
        validarPaciente(paciente);
        pacienteRepository.update(paciente);
    }

    @Override
    public void delete(int id) {
        pacienteRepository.delete(id);
    }

    private void validarPaciente(Paciente paciente) {
        if (paciente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El Nombre del paciente no puede estar vacío");
        }
        if (paciente.getEdad() <= 0) {
            throw new IllegalArgumentException("La Edad del paciente debe ser mayor a cero");
        }
    }
}
