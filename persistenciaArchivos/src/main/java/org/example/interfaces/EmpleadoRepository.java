package org.example.interfaces;

import org.example.domain.Empleado;
import java.util.List;

public interface EmpleadoRepository {
    List<Empleado> findAll();
    Empleado findByNombre(String nombre);
    void save(Empleado empleado);
    void update(Empleado empleado);
    void delete(String nombre);
}