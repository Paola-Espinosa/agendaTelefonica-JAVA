package org.generation.app;

import org.generation.model.Agenda;
import org.generation.model.Contacto;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);


        while(true) {
            System.out.println("\n--- AGENDA TELEFÓNICA ---");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Listar contactos");
            System.out.println("5. Espacios libres");
            System.out.println("6. Modificar Telefono");
            System.out.println("7. Salir");
            System.out.print("Seleccione opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch(opcion) {
                    case 1:
                        añadirContacto(agenda, scanner);
                        break;
                    case 2:
                        buscarContacto(agenda, scanner);
                        break;
                    case 3:
                        eliminarContacto(agenda, scanner);
                        break;
                    case 4:
                        agenda.listarContactos();
                        break;
                    case 5:
                        System.out.println("Espacios libres: " + agenda.espacioLibres());
                        break;
                    case 6:
                        modificarPhone(agenda, scanner);
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        return; // Salir del programa de manera más limpia
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar entrada inválida
            }
        }
    }

    private static void añadirContacto(Agenda agenda, Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        Contacto nuevo = new Contacto(nombre, apellido, telefono);
        if(agenda.añadirContacto(nuevo)) {
            System.out.println("Contacto añadido");
        } else if (!agenda.validacion(nuevo)){
            System.out.println("Complete todos los campos");
        } else if (agenda.existeContacto(nuevo)){
            System.out.println("Error: Contacto existente.");
        }else{
            System.out.println("Error: Agenda llena");
        }
    }

    private static void buscarContacto(Agenda agenda, Scanner scanner) {
        System.out.print("Nombre a buscar: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido a buscar: ");
        String apellido = scanner.nextLine();
        String telefono = agenda.buscaContacto(nombre, apellido);
        System.out.println(telefono != null ?
                "Teléfono: " + telefono : "Contacto no encontrado");
    }

    private static void eliminarContacto(Agenda agenda, Scanner scanner) {
        System.out.print("Nombre a eliminar: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido a eliminar: ");
        String apellido = scanner.nextLine();
        // Asegúrate de que el metodo equals en Contacto solo compara nombres
        Contacto temp = new Contacto(nombre, apellido,"");
        System.out.println(agenda.eliminarContacto(temp) ?
                "Contacto eliminado" : "Contacto no encontrado");
    }

    private static void modificarPhone(Agenda agenda, Scanner scanner) {
        System.out.print("Nombre : ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido : ");
        String apellido = scanner.nextLine();

        System.out.print("Nuevo Teléfono: ");
        String nuevoTelefono = scanner.nextLine();

        if (agenda.modificarTelefono(nombre, apellido, nuevoTelefono)) {
            System.out.println("Teléfono actualizado.");
        } else {
            System.out.println("Contacto no encontrado.");
        }

    }

}