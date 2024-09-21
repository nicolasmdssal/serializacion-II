package org.example.infraestructure.repository;

import org.example.domain.Empleado;
import org.example.interfaces.EmpleadoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmpleadoRepositoryImpl implements EmpleadoRepository {
    private static final String FILE_NAME = "productos.dat";

    @Override
    public List<Empleado> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Empleado>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Empleado findByNombre(String nombre) {
        return findAll().stream()
                .filter(p -> p.getNombre() == nombre)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Empleado empleado) {
        List<Empleado> empleados = findAll();
        empleados.add(empleado);
        saveAll(empleados);
    }

    @Override
    public void update(Empleado empleado) {
        List<Empleado> empleados = findAll();
        empleados = empleados.stream()
                .map(p -> p.getNombre() == empleado.getNombre() ? empleado : p)
                .collect(Collectors.toList());
        saveAll(empleados);
    }

    @Override
    public void delete(String nombre) {
        List<Empleado> empleados = findAll();
        empleados = empleados.stream()
                .filter(p -> p.getNombre() != nombre)
                .collect(Collectors.toList());
        saveAll(empleados);
    }

    private void saveAll(List<Empleado> empleados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(empleados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
