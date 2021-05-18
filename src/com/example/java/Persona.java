package com.example.java;

import java.util.Objects;

public class Persona implements Comparable<Persona>{

    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;

    public Persona() {
    }

    public Persona(String nombre, String apellido1, String apellido2, int edad) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", edad=" + edad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return edad == persona.edad &&
                Objects.equals(nombre, persona.nombre) &&
                Objects.equals(apellido1, persona.apellido1) &&
                Objects.equals(apellido2, persona.apellido2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido1, apellido2, edad);
    }


    @Override
    public int compareTo(Persona o) {
        if(o.getNombre().compareTo(nombre) == 0){
            if(o.getApellido1().compareTo(apellido1) == 0){
               if(o.getApellido2().compareTo(apellido2) == 0){
                   return Integer.compare(o.getEdad(),edad);
               }
               else {
                   return o.getApellido2().compareTo(apellido2);
               }
            }
            else{
                return o.getApellido1().compareTo(apellido1);
            }
        }
        else{
            return o.getNombre().compareTo(nombre);
        }
    }
}
