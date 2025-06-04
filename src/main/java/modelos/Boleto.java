package modelos;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "boletos", schemaVersion= "1.0")
public class Boleto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    @NotNull(message = "El cliente no puede ser nulo")
    private Cliente cliente;
    
    @NotNull(message = "El vuelo no puede ser nulo")
    private Vuelo vuelo;
    
    @NotNull(message = "La clase no puede ser nula")
    private Clase clase;
    
    @Positive(message = "El costo debe ser positivo")
    @Digits(integer = 6, fraction = 2, message = "Costo inválido")
    private double costo;
    
    @NotBlank(message = "El asiento no puede estar vacío")
    @Pattern(regexp = "^[A-Z][1-9][0-9]?$", message = "Formato de asiento inválido (Ej: A1, B12)")
    private String asiento;
    
    @NotNull(message = "La fecha de emisión no puede estar vacía")
    private LocalDateTime fechaEmision;
    
    @NotNull(message = "El estado no puede ser nulo")
    private EstadoBoleto estado;
    
    // Enums para estados y tipos
    public enum EstadoBoleto {
        RESERVADO, PAGADO, CANCELADO, UTILIZADO
    }

    // Constructor que genera ID automático
    public Boleto() {
        this.id = "BLT-" + UUID.randomUUID().toString().substring(0, 8);
        this.fechaEmision = LocalDateTime.now();
        this.estado = EstadoBoleto.RESERVADO;
    }

    // Métodos de negocio
    public void pagar() {
        if (this.estado != EstadoBoleto.RESERVADO) {
            throw new IllegalStateException("Solo se pueden pagar boletos reservados");
        }
        this.estado = EstadoBoleto.PAGADO;
    }
    
    public void cancelar() {
        if (this.estado == EstadoBoleto.UTILIZADO) {
            throw new IllegalStateException("No se puede cancelar un boleto utilizado");
        }
        this.estado = EstadoBoleto.CANCELADO;
    }
    
    public void utilizar() {
        if (this.estado != EstadoBoleto.PAGADO) {
            throw new IllegalStateException("Solo se pueden utilizar boletos pagados");
        }
        this.estado = EstadoBoleto.UTILIZADO;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Vuelo getVuelo() { return vuelo; }
    public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; }

    public Clase getClase() { return clase; }
    public void setClase(Clase clase) { this.clase = clase; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public String getAsiento() { return asiento; }
    public void setAsiento(String asiento) { this.asiento = asiento; }

    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }

    public EstadoBoleto getEstado() { return estado; }
    public void setEstado(EstadoBoleto estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Boleto #" + id + " - " + vuelo.getCiudadSalida() + " a " + 
               vuelo.getCiudadLlegada() + " (" + clase.getNombre() + ")";
    }
}