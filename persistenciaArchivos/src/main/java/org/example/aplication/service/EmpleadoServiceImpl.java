package org.example.aplication.service;

import org.example.domain.Empleado;
import org.example.interfaces.EmpleadoRepository;
import java.util.List;

public class EmpleadoServiceImpl implements EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepositoryRepository) {
        this.empleadoRepository = empleadoRepositoryRepository;
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado findByNombre(String nombre) {
        return empleadoRepository.findByNombre(nombre);
    }

    @Override
    public void save(Empleado empleado) {
        validarEmpleado(empleado);
        empleadoRepository.save(empleado);
    }

    @Override
    public void update(Empleado empleado) {
        validarEmpleado(empleado);
        empleadoRepository.update(empleado);
    }

    @Override
    public void delete(String nombre) {
        empleadoRepository.delete(nombre);
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacío");
        }
        if (empleado.getSalario() <= 0) {
            throw new IllegalArgumentException("El precio del empleado debe ser mayor a cero");
        }
    }
}
