package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import modelos.Avion;
import modelos.Azafata;
import modelos.Clase;
import modelos.Piloto;
import modelos.Vuelo;

public class VueloValidacion {
    private final Vuelo vuelo;
    private final Validator validator;

    public VueloValidacion() {
        this.vuelo = new Vuelo();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void validarCompleto(
            String ciudadSalida,
            String ciudadLlegada,
            LocalDate fechaSalida,
            LocalDate fechaLlegada,
            LocalTime horaSalida,
            LocalTime horaLlegada,
            Clase clase,
            Avion avion,
            List<Piloto> pilotos,
            List<Azafata> azafatas) {
        
        vuelo.setCiudadSalida(ciudadSalida);
        vuelo.setCiudadLlegada(ciudadLlegada);
        vuelo.setFechaSalida(fechaSalida);
        vuelo.setFechaLlegada(fechaLlegada);
        vuelo.setHoraSalida(horaSalida);
        vuelo.setHoraLlegada(horaLlegada);
        vuelo.setClase(clase);
        vuelo.setAvion(avion);
        vuelo.setPilotos(pilotos);
        vuelo.setAzafatas(azafatas);

        Set<ConstraintViolation<Vuelo>> violations = validator.validate(vuelo);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        
        // Validación de cantidad de personal
        if (pilotos == null || pilotos.size() != 2) {
            throw new IllegalArgumentException("Debe haber exactamente 2 pilotos");
        }
        
        if (azafatas == null || azafatas.size() != 4) {
            throw new IllegalArgumentException("Debe haber exactamente 4 azafatas");
        }
        
        // Validación básica de capacidad
        if (avion.getCapacidad() <= 0) {
            throw new IllegalArgumentException("La capacidad del avión debe ser mayor a cero");
        }
    }

    public Vuelo getVueloValidado() {
        return this.vuelo;
    }
}