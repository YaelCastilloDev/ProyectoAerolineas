package modelos;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalTime;


@Document(collection = "boletos", schemaVersion= "1.0")
public class Boleto implements Serializable {
    
    @Id
    private String id;


    
    
    @Positive(message = "El costo debe ser positivo")
    @Digits(integer = 6, fraction = 2, message = "Costo inválido")
    private double costoBoleto;
    
    @Min(value = 1, message = "Número de sala inválido")
    @Max(value = 50, message = "Número de sala inválido")
    private int sala;
    
    @Min(value = 1, message = "Número de control inválido")
    @Max(value = 999, message = "Número de control inválido")
    private int control;
    
    @NotBlank(message = "La clase no puede estar vacía")
    @Pattern(regexp = "Primera|Ejecutiva|Turista", message = "Clase no válida")
    private String clase;
    
    @Min(value = 1, message = "Zona inválida")
    @Max(value = 10, message = "Zona inválida")
    private int zona;
    
    @NotNull(message = "La hora de expedición no puede estar vacía")
    private LocalTime horaExpedicion;
    
    @NotNull(message = "La hora de salida no puede estar vacía")
    @FutureOrPresent(message = "La hora de salida debe ser en el futuro o presente")
    private LocalTime horaSalida;

    // Getters y Setters...
    public double getCostoBoleto() { return costoBoleto; }
    public void setCostoBoleto(double costoBoleto) { this.costoBoleto = costoBoleto; }

    public int getSala() { return sala; }
    public void setSala(int sala) { this.sala = sala; }

    public int getControl() { return control; }
    public void setControl(int control) { this.control = control; }

    public String getClase() { return clase; }
    public void setClase(String clase) { this.clase = clase; }

    public int getZona() { return zona; }
    public void setZona(int zona) { this.zona = zona; }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalTime getHoraExpedicion() { return horaExpedicion; }
    public void setHoraExpedicion(LocalTime horaExpedicion) { this.horaExpedicion = horaExpedicion; }

    public LocalTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalTime horaSalida) { this.horaSalida = horaSalida; }
}