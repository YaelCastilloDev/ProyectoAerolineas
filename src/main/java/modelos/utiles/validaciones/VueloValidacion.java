package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import modelos.Avion;
import modelos.Azafata;
import modelos.Clase;
import modelos.Piloto;
import modelos.Vuelo;
import modelos.dao.contratos.VueloDAO;
import modelos.dao.implementaciones.VueloDAOimpl;

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
    
    private void validarDisponibilidadAvion(Avion avion, LocalDate inicio, LocalDate fin) {
    // Obtener todos los vuelos para este avión
    VueloDAO vueloDAO = new VueloDAOimpl();
    List<Vuelo> vuelosDelAvion = vueloDAO.listarTodos().stream().filter(v -> v.getAvion().equals(avion)).toList();
        
        // Verificar superposición de horarios
    for (Vuelo v : vuelosDelAvion) {
        if (haySuperposicion(v.getFechaSalida(), v.getFechaLlegada(), inicio, fin)) {
                throw new IllegalArgumentException(
                    "El avión ya tiene un vuelo programado en ese horario: " + 
                    v.getCiudadSalida() + " -> " + v.getCiudadLlegada() + 
                    " (" + v.getFechaSalida() + " - " + v.getFechaLlegada() + ")");
            }
        }
    }
    
    private boolean haySuperposicion(LocalDate inicio1, LocalDate fin1, LocalDate inicio2, LocalDate fin2) {
        return !inicio1.isAfter(fin2) && !inicio2.isAfter(fin1);
    }
    
    private void validarCapacidadAvion(Avion avion) {
        if (avion.getCapacidad() <= 0) {
            throw new IllegalArgumentException("La capacidad del avión debe ser mayor a cero");
        }
    }
    
    public Vuelo getVueloValidado() {
        return this.vuelo;
    }
}