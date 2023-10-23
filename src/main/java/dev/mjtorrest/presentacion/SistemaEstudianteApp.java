package dev.mjtorrest.presentacion;

import dev.mjtorrest.datos.EstudianteDAO;
import dev.mjtorrest.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudianteApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        // SE CREA UNA INSTANCIA CLASE SERVICIO
        var estudianteDao = new EstudianteDAO();
        while (!salir){
            try {
                mostrarMenu();
                salir = ejecutarOpciones(consola, estudianteDao);
            } catch (Exception e) {
                System.out.println("Ocurrio un error al ejecutar operacion: " + e.getMessage());
            }
            System.out.println();
        } // FIN WHILE
    }

    // FUNCION MOSTAR MENU
    private static void mostrarMenu(){
        System.out.print("""
                *** Sistema de Estudiante ***
                1. Agregar Estudiantes 
                2. Listar Estudiantes
                3. Buscar Estudiantes
                4. Modificar Estudiantes
                5. Eliminar Estudiantes
                6. Salir 
                Elige una opcion: 
                """);
    }

    // FUNCION EJECUTAR OPCIONES
    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){

            case 1 -> { // AGREGAR ESTUDIANTES
                System.out.println("Agregar Estudiantes..");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                // CREAR EL OBJETO ESTUDIANTE (SIN EL ID)
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDAO.agregarEstudiante(estudiante);
                if(agregado)
                    System.out.println("Estudiante agregado: " + estudiante);
                else
                    System.out.println("Estudiante no agregado: " + estudiante);
            }

            case 2 -> { // LISTAR ESTUDIANTES
                System.out.println("Listar Estudiantes..");
                var estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }

            case 3 -> { // BUSCAR ESTUDIANTES
                System.out.println("Introduce el id_estudiante a buscar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if(encontrado)
                    System.out.println("Estudiante encontrad: " + estudiante);
                else
                    System.out.println("Estudiante no encontrado: " + estudiante);
            }

            case 4 -> { // MODIFICAR ESTUDIANTE
                System.out.println("Modificar estudiante: ");
                System.out.println("Id Estudiante: ");
                var idEStudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                // CREAR EL OBJETO ESTUDIANTE A MODIFICAR
                var estudiante = new Estudiante(idEStudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDAO.modificarEstudiante(estudiante);
                if(modificado)
                    System.out.println("Estudiante modificado: " + estudiante);
                else
                    System.out.println("Estudiante no modificado: " + estudiante);
            }

            case 5 -> { // ELIMINAR ESTUDIANTE
                System.out.println("Eliminar Estudiante");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if(eliminado)
                    System.out.println("Estudiante Eliminado: " + estudiante);
                else
                    System.out.println("Estudiante no Eliminado: " + estudiante);
            }

            case 6 -> { // SALIR
                System.out.println("Hasta Pronto!");
                salir = true;
            }

            default -> System.out.println("Opcion no Reconocida");
        }
        return salir;
    }

}