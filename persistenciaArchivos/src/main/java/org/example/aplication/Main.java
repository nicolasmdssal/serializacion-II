package org.example.aplication;

import org.example.aplication.service.EmpleadoService;
import org.example.aplication.service.EmpleadoServiceImpl;
import org.example.domain.Empleado;
import org.example.domain.Tarea;
import org.example.infraestructure.repository.EmpleadoRepositoryImpl;
import org.example.interfaces.EmpleadoRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmpleadoService empleadoService;

    static {
        EmpleadoRepository empleadoRepository = new EmpleadoRepositoryImpl();
        empleadoService = new EmpleadoServiceImpl(empleadoRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. crear un empleado");
            System.out.println("2. actualizar satos de un empleado");
            System.out.println("3. asignar una tarea a un empleado");
            System.out.println("4. eliminar un empleado");
            System.out.println("5. mostrar una lista de los empleados");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    actualizarEmpleado();
                    break;
                case 3:
                    asignarTarea();
                    break;
                case 4:
                    eliminarEmpleado();
                    break;
                case 5:
                    listarEmpleados();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private static void listarEmpleados() {
        List<Empleado> empleados = empleadoService.findAll();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("Listado de empleados:");
            for (Empleado empleado : empleados) {
                System.out.println(empleados);
            }
        }
    }

    private static void crearEmpleado() {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre  = scanner.nextLine();
        System.out.print("Ingrese el apellido del empleado: ");
        String apellido = scanner.nextLine();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("ingrese la edad del empleado: ");
        int edad = scanner.nextInt();
        System.out.println("ingrese el cargo del empleado: ");
        String cargo = scanner.nextLine();
        System.out.println("ingrese el departamento del empleado: ");
        String departamento = scanner.nextLine();
        System.out.println("ingrese el salario del empleado: ");
        double salario  = scanner.nextDouble();

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setEdad(edad);
        empleado.setCargo(cargo);
        empleado.setDepartamento(departamento);
        empleado.setSalario(salario);

        try {
            empleadoService.save(empleado);
            System.out.println("Empleado creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarEmpleado() {
        System.out.print("Ingrese el nombre del empleado a actualizar: ");
        String nombre = scanner.nextLine();
        scanner.nextLine(); // Consumir el salto de línea

        Empleado empleado = empleadoService.findByNombre(nombre);
        if (empleado == null) {
            System.out.println("No se encontró el empleado con nombre " + nombre);
            return;
        }

        System.out.print("Ingrese el nuevo nombre del empleado (dejar en blanco para no cambiar): ");
        nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            empleado.setNombre(nombre);
        }

        System.out.print("Ingrese el nuevo apellido del empleado (dejar en blanco para no cambiar): ");
        String apellido = scanner.nextLine();
        scanner.nextLine(); // Consumir el salto de línea
        if (!apellido.isEmpty()) {
            empleado.setApellido(apellido);
        }

        System.out.print("Ingrese la nueva edad del empleado (0 para no cambiar): ");
        int edad = scanner.nextInt();
        if (edad > 0) {
            empleado.setEdad(edad);
        }

        System.out.print("Ingrese el nuevo cargo del empleado (dejar en blanco para no cambiar): ");
        String cargo = scanner.nextLine();
        if (!cargo.isEmpty()) {
            empleado.setCargo(cargo);
        }

        System.out.print("Ingrese el nuevo departamento del empleado (dejar en blanco para no cambiar): ");
        String departamento = scanner.nextLine();
        if (!departamento.isEmpty()) {
            empleado.setDepartamento(departamento);
        }

        System.out.print("Ingrese el nuevo salario del empleado (0 para no cambiar): ");
        double salario = scanner.nextDouble();
        if (salario > 0) {
            empleado.setSalario(salario);
        }

        try {
            empleadoService.update(empleado);
            System.out.println("Empleado actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void asignarTarea(){
        System.out.println("escriba el nombre del empleado al que desea asignar una tarea: ");
        String nombre = scanner.nextLine();
        System.out.println("ingrese el titulo de la tarea:" );
        String titulo = scanner.nextLine();
        System.out.print("Ingrese una descripcion de la tarea: ");
        String descripcion = scanner.nextLine();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("ingrese la fecha de inicio: ");
        String fechaInicio = scanner.nextLine();
        System.out.println("ingrese la fecha de finalizacion: ");
        String fechaFin = scanner.nextLine();
        System.out.println("ingrese el estado de la tarea: ");
        String estado = scanner.nextLine();

        Tarea tarea = new Tarea();
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setFechaInicio(fechaInicio);
        tarea.setFechaFin(fechaFin);
        tarea.setEstado(estado);
    }

    private static void eliminarEmpleado() {
        System.out.print("Ingrese el nombre del empleado a eliminar: ");
        String nombre = scanner.nextLine();
        scanner.nextLine(); // Consumir el salto de línea
        Empleado empleado = empleadoService.findByNombre(nombre);
        if (empleado == null) {
            System.out.println("No se encontró el empleado " + nombre);
            return;
        }

        empleadoService.delete(nombre);
        System.out.println("Empleado eliminado exitosamente.");
    }
}
