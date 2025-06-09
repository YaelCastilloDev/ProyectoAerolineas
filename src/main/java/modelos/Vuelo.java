package modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Document(collection = "vuelos", schemaVersion= "1.0")
public class Vuelo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    @NotBlank(message = "La ciudad de salida no puede estar vacía")
    @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
    private String ciudadSalida;
    
    @NotBlank(message = "La ciudad de llegada no puede estar vacía")
    @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
    private String ciudadLlegada;
    
    @NotNull(message = "La fecha de salida es obligatoria")
    @Future(message = "La fecha de salida debe ser futura")
    private LocalDate fechaSalida;
    
    @NotNull(message = "La fecha de llegada es obligatoria")
    @Future(message = "La fecha de llegada debe ser futura")
    private LocalDate fechaLlegada;
    
    @NotNull(message = "La hora de salida es obligatoria")
    private LocalTime horaSalida;
    
    @NotNull(message = "La hora de llegada es obligatoria")
    private LocalTime horaLlegada;
    
    @NotNull(message = "La clase no puede ser nula")
    private Clase clase;
    
    @NotNull(message = "El avión no puede ser nulo")
    private Avion avion;
    
    @Size(min = 2, max = 2, message = "Debe haber exactamente 2 pilotos")
    private List<@NotNull Piloto> pilotos;
    
    @Size(min = 4, max = 4, message = "Debe haber exactamente 4 azafatas")
    private List<@NotNull Azafata> azafatas;

    @PositiveOrZero(message = "El número de pasajeros no puede ser negativo")
    public int getPasajerosRegistrados() {
        return pasajerosRegistrados;
    }

    public void setPasajerosRegistrados(@PositiveOrZero(message = "El número de pasajeros no puede ser negativo") int pasajerosRegistrados) {
        this.pasajerosRegistrados = pasajerosRegistrados;
    }

    @PositiveOrZero(message = "El número de pasajeros no puede ser negativo")
    private int pasajerosRegistrados = 0;
    
   // @Positive(message = "El costo del boleto debe ser positivo")
    private double costoBoleto;


    @JsonIgnore // Para evitar recursión en JSON
    private List<Boleto> boletos = new ArrayList<>();

    // Método para agregar boleto
    public void agregarBoleto(Boleto boleto) {
        if (boletos == null) {
            boletos = new ArrayList<>();
        }
        boletos.add(boleto);
    }
    @JsonIgnore
    // Método para obtener boletos no cancelados
    public List<Boleto> getBoletosActivos() {
        return boletos.stream()
            .filter(b -> b.getEstado() != Boleto.EstadoBoleto.CANCELADO)
            .collect(Collectors.toList());
    }
    
    public void setBoletos(List<Boleto> boletos) {
    this.boletos = boletos != null ? boletos : new ArrayList<>();
    }

    // Método para verificar disponibilidad por clase
    public boolean tieneDisponibilidad(Clase clase) {
        long boletosClase = getBoletosActivos().stream()
            .filter(b -> b.getClase().equals(clase))
            .count();
        return boletosClase < clase.getCapacidad();
    }
    
    // Método para verificar disponibilidad de asientos
    public boolean tieneAsientosDisponibles() {
        return pasajerosRegistrados < avion.getCapacidad();
    }
    
    // Método para registrar un pasajero
    public void registrarPasajero() {
        if (!tieneAsientosDisponibles()) {
            throw new IllegalStateException("No hay asientos disponibles en este vuelo");
        }
        pasajerosRegistrados++;
    }

    // Validación para fechas coherentes
    @AssertTrue(message = "La fecha/hora de llegada debe ser posterior a la de salida")
    private boolean isFechasValidas() {
        if (fechaSalida == null || fechaLlegada == null) {
            return false;
        }
        return fechaLlegada.isAfter(fechaSalida);
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getCiudadSalida() {
        return ciudadSalida;
    }

    public void setCiudadSalida(String ciudadSalida) {
        this.ciudadSalida = ciudadSalida;
    }

    public String getCiudadLlegada() {
        return ciudadLlegada;
    }

    public void setCiudadLlegada(String ciudadLlegada) {
        this.ciudadLlegada = ciudadLlegada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public List<Piloto> getPilotos() {
        return pilotos;
    }

    public void setPilotos(List<Piloto> pilotos) {
        this.pilotos = pilotos;
    }

    public List<Azafata> getAzafatas() {
        return azafatas;
    }

    public void setAzafatas(List<Azafata> azafatas) {
        this.azafatas = azafatas;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return Objects.equals(id, vuelo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //@Positive(message = "El costo del boleto debe ser positivo")
    public double getCostoBoleto() {
        return costoBoleto;
    }

    public void setCostoBoleto(@Positive(message = "El costo del boleto debe ser positivo") double costoBoleto) {
        this.costoBoleto = costoBoleto;
    }
}