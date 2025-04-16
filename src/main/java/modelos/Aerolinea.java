package modelos;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.List;


@Document(collection = "aerolineas", schemaVersion= "1.0")
public class Aerolinea implements Serializable {
    
    @Id
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "El país no puede estar vacío")
    @Size(max = 30, message = "El país no puede exceder 30 caracteres")
    private String pais;
    
    @NotBlank(message = "El centro de operación no puede estar vacío")
    @Size(max = 50, message = "El centro de operación no puede exceder 50 caracteres")
    private String centroOperacionPrincipal;
    
    @NotEmpty(message = "Debe especificar al menos una base")
    private List<@Size(max = 30, message = "Cada base no puede exceder 30 caracteres") String> bases;
    
    @NotBlank(message = "El sitio web no puede estar vacío")
    @Size(max = 100, message = "El sitio web no puede exceder 100 caracteres")
    @Pattern(regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$", 
             message = "Debe ser una URL válida")
    private String sitioOficial;
    
    @NotBlank(message = "El nombre de contacto no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombreContacto;
    
    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Debe ser un número de teléfono válido")
    private String telefono;


    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public String getCentroOperacionPrincipal() { return centroOperacionPrincipal; }
    public void setCentroOperacionPrincipal(String centroOperacionPrincipal) { this.centroOperacionPrincipal = centroOperacionPrincipal; }
    public List<String> getBases() { return bases; }
    public void setBases(List<String> bases) { this.bases = bases; }
    public String getSitioOficial() { return sitioOficial; }
    public void setSitioOficial(String sitioOficial) { this.sitioOficial = sitioOficial; }
    public String getNombreContacto() { return nombreContacto; }
    public void setNombreContacto(String nombreContacto) { this.nombreContacto = nombreContacto; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}