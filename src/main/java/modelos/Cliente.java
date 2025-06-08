package modelos;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "clientes", schemaVersion= "1.0")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]*$", message = "Debe comenzar con mayúscula")
    private String nombre;
    
    @NotBlank(message = "La nacionalidad no puede estar vacía")
    @Size(max = 30, message = "La nacionalidad no puede exceder 30 caracteres")
    private String nacionalidad;
    
    @Past(message = "La fecha de nacimiento debe ser pasada")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
    
    @Id
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe ser un correo válido")
    @Size(max = 60, message = "El correo no puede exceder 60 caracteres")
    private String correoElectronico;
    
  //  @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Teléfono inválido")
    private String telefono;
    
    @Size(min = 1, max = 3, message = "Máximo 3 pasaportes")
    private List<@Size(min = 1, max = 20) String> pasaportes;

    // Getters and Setters
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public List<String> getPasaportes() {
        return pasaportes;
    }

    public void setPasaportes(List<String> pasaportes) {
        this.pasaportes = pasaportes;
    }
}