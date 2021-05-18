package com.example.java;

public class Empleado extends Persona{
    String empresa;

    public Empleado(String nombre, String apellido1, String apellido2, int edad, String empresa) {
        super(nombre, apellido1, apellido2, edad);
        this.empresa = empresa;
    }

    public Empleado() {
        super();
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + this.getNombre() + '\'' +
                ", apellido1='" + this.getApellido1() + '\'' +
                ", apellido2='" + this.getApellido2() + '\'' +
                ", edad=" + this.getEdad() +
                " empresa='" + this.getEmpresa() + '\'' +
                '}';
    }
}
