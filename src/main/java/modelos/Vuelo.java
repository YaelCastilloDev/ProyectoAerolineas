package modelos;

import io.jsondb.annotation.Document;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Document(collection = "vuelos", schemaVersion= "1.0")
public class Vuelo implements Serializable {
    private static final long serialVersionUID = 1L;
    
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

    // Getters y Setters
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

    // Validación adicional para asegurar que la hora de llegada sea después de la de salida
    @AssertTrue(message = "La hora de llegada debe ser posterior a la hora de salida")
    private boolean isHorarioValido() {
        if (horaSalida == null || horaLlegada == null) {
            return true;
        }
        return horaLlegada.isAfter(horaSalida);
    }
}