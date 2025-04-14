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

    public VueloValidacion() {
        this.vuelo = new Vuelo();
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

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Vuelo>> violations = validator.validate(vuelo);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Vuelo getVueloValidado() {
        return this.vuelo;
    }
}