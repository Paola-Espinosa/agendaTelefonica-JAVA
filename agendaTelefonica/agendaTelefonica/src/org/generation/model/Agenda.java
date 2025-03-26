package org.generation.model;


import org.generation.model.Contacto;

import java.util.Scanner;

public class Agenda {
    private static final int CAPACIDAD_MAXIMA = 10;
    private Contacto[] contactos;
    private int cantidadContactos;

    public Agenda() {
        contactos = new Contacto[CAPACIDAD_MAXIMA];
        cantidadContactos = 0;
    }

    public boolean validacion(Contacto valido) {
        // Comprobamos si el nombre o el apellido están vacíos o son null
        if ((valido.getNombre() == null || valido.getNombre().isEmpty()) ||
                (valido.getApellido() == null || valido.getApellido().isEmpty())) {
            return false; // Devuelve false si alguno de los campos está vacío
        }
        return true; // Devuelve true si ambos campos están llenos
    }


    public boolean añadirContacto(Contacto nuevo) {

        if (agendaLlena()) return false;
        if (existeContacto(nuevo)) return false;
        if (!validacion(nuevo)) return false;

        contactos[cantidadContactos++] = nuevo;
        return true;
    }

    public boolean existeContacto(Contacto c) {
        for (int i = 0; i < cantidadContactos; i++) {
            if (contactos[i].equals(c)) return true;
        }
        return false;
    }

    public void listarContactos() {
        if (cantidadContactos != 0){
            for (int i = 0; i < cantidadContactos; i++) {
                System.out.println(contactos[i].getNombre() + " " + contactos[i].getApellido() + " : " +
                        contactos[i].getTelefono());
            }
        }else {
            System.out.println("Agenda Vacia");
        }
    }

    public String buscaContacto(String nombre, String apellido) {
        for (int i = 0; i < cantidadContactos; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre) && contactos[i].getApellido().equalsIgnoreCase(apellido))  {
                return contactos[i].getTelefono();
            }
        }
        return null;
    }

    public boolean eliminarContacto(Contacto c) {
        for (int i = 0; i < cantidadContactos; i++) {
            if (contactos[i].equals(c)) {
                System.arraycopy(contactos, i+1, contactos, i,
                        cantidadContactos - i - 1);
                cantidadContactos--;
                return true;
            }
        }
        return false;
    }

    public boolean agendaLlena() {
        return cantidadContactos >= CAPACIDAD_MAXIMA;
    }

    public int espacioLibres() {
        return CAPACIDAD_MAXIMA - cantidadContactos;
    }

    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        for (int i = 0; i < cantidadContactos; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre) && contactos[i].getApellido().equalsIgnoreCase(apellido)) {
                contactos[i].setTelefono(nuevoTelefono);
                return true;
            }
        }
        return false;
    }

}