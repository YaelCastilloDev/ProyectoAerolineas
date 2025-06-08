package modelos;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;


@Document(collection = "azafatas", schemaVersion= "1.0")
public class Azafata implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 100, message = "La dirección no puede exceder 100 caracteres")
    private String direccion;
    
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;
    
    @NotBlank(message = "El género no puede estar vacío")
    //@Pattern(regexp = "Masculino|Femenino|Otro", message = "Género no válido")
    private String genero;
    
    @Positive(message = "El salario debe ser positivo")
    @Digits(integer = 6, fraction = 2, message = "Salario inválido")
    private double salario;
    
    
    @Id
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe ser un correo electrónico válido")
    @Size(max = 60, message = "El correo no puede exceder 60 caracteres")
    private String correoElectronico;
    
    // @Secret 
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", 
             message = "La contraseña debe contener al menos un número, una mayúscula y una minúscula")
    private String contrasena;
    
    @Min(value = 1, message = "Debe hablar al menos 1 idioma")
    @Max(value = 10, message = "No puede hablar más de 10 idiomas")
    private int numIdiomas;
    
    @PastOrPresent(message = "La fecha de inicio debe ser en el pasado o presente")
    private LocalDate anoInicio;

    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getNumIdiomas() {
        return numIdiomas;
    }

    public void setNumIdiomas(int numIdiomas) {
        this.numIdiomas = numIdiomas;
    }

    public LocalDate getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(LocalDate anoInicio) {
        this.anoInicio = anoInicio;
    }
}