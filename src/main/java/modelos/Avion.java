package modelos;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import jakarta.validation.constraints.*;
import java.io.Serializable;

@Document(collection = "aviones", schemaVersion= "1.0")
public class Avion implements Serializable {
    
    @Id
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 30, message = "El nombre no puede exceder 30 caracteres")
    private String nombre;
    
    @Min(value = 10, message = "La capacidad mínima es 6") 
    @Max(value = 500, message = "La capacidad máxima es 500")
    private int capacidad;
    
    @NotBlank(message = "El modelo no puede estar vacío")
    @Size(max = 20, message = "El modelo no puede exceder 20 caracteres")
    private String modelo;
    
    @Positive(message = "El peso debe ser positivo")
    @Max(value = 500000, message = "El peso máximo es 500,000 kg")
    private int peso;
    
    @NotBlank(message = "La matrícula no puede estar vacía")
    @Pattern(regexp = "^[A-Z0-9]{2,10}$", message = "Formato de matrícula inválido")
    private String matricula;
    
    @NotBlank(message = "La aerolínea propietaria no puede estar vacía")
    @Size(min = 2, max = 50, message = "La aerolínea propietaria debe tener entre 2 y 50 caracteres")
    private String aerolineaPropietaria;

    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getMatricula() { return matricula; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }

    public String getAerolineaPropietaria() { return aerolineaPropietaria; }
    public void setAerolineaPropietaria(String aerolineaPropietaria) { 
        this.aerolineaPropietaria = aerolineaPropietaria; 
    }
}