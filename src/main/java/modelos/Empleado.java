/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Secret;
import jakarta.validation.constraints.*;
import java.time.LocalDate;


@Document(collection = "empleados", schemaVersion= "1.0")
public abstract class Empleado {
    @NotBlank 
    private String nombre;
    
    @NotBlank 
    private String direccion;
    
    @Past 
    private LocalDate fechaNacimiento;
    
    @NotBlank 
    private String genero;
    
    @Positive 
    private double salario;
    
    @Secret 
    @Email 
    private String correoElectronico;
    
    @Size(min = 6) 
    private String contrasena;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}