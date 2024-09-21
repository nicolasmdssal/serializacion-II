package org.example.aplication.service;
import org.example.domain.Empleado;
import java.util.List;

public interface EmpleadoService {
    List<Empleado> findAll();
    Empleado findByNombre(String nombre);
    void save(Empleado empleado);
    void update(Empleado empleado);
    void delete(String nombre);
}