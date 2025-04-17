package modelos;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import jakarta.validation.constraints.*;
import java.io.Serializable;


@Document(collection = "clases", schemaVersion= "1.0")
public class Clase implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]*$", message = "Debe comenzar con mayúscula y contener solo letras")
    private String nombre;
    
    @Min(value = 1, message = "La capacidad mínima es 1")
    @Max(value = 500, message = "La capacidad máxima es 500")
    private int capacidad;

    // Getters y Setters...
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
}
