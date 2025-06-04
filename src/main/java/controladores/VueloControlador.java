package controladores;

import jakarta.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import modelos.Avion;
import modelos.Azafata;
import modelos.Clase;
import modelos.Piloto;
import modelos.Vuelo;
import modelos.utiles.validaciones.VueloValidacion;
import modelos.dao.implementaciones.VueloDAOimpl;

public class VueloControlador {
    private final VueloDAOimpl vueloDAO;
    private final VueloValidacion validador;

    public VueloControlador() {
        this.vueloDAO = new VueloDAOimpl();
        this.validador = new VueloValidacion();
    }

    public void crearVuelo(
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
        
        try {
            validador.validarCompleto(
                ciudadSalida, ciudadLlegada,
                fechaSalida, fechaLlegada,
                horaSalida, horaLlegada,
                clase, avion, pilotos, azafatas);
            
            Vuelo vuelo = validador.getVueloValidado();
            vueloDAO.crear(vuelo);
            
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(
                e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public Vuelo buscarVuelo(String ciudadSalida, LocalDate fechaSalida, LocalTime horaSalida) {
        String id = generarIdVuelo(ciudadSalida, fechaSalida, horaSalida);
        Vuelo vuelo = vueloDAO.buscarPorId(id);
        if (vuelo == null) {
            throw new IllegalArgumentException("No existe el vuelo especificado");
        }
        return vuelo;
    }

    public List<Vuelo> listarTodosVuelos() {
        List<Vuelo> vuelos = vueloDAO.listarTodos();
        if (vuelos.isEmpty()) {
            throw new IllegalStateException("No hay vuelos registrados");
        }
        return vuelos;
    }

    public List<Vuelo> listarVuelosPorCiudadSalida(String ciudadSalida) {
        List<Vuelo> vuelos = vueloDAO.listarPorCiudadSalida(ciudadSalida);
        if (vuelos.isEmpty()) {
            throw new IllegalStateException("No hay vuelos con esa ciudad de salida");
        }
        return vuelos;
    }

    public List<Vuelo> listarVuelosPorCiudadLlegada(String ciudadLlegada) {
        List<Vuelo> vuelos = vueloDAO.listarPorCiudadLlegada(ciudadLlegada);
        if (vuelos.isEmpty()) {
            throw new IllegalStateException("No hay vuelos con esa ciudad de llegada");
        }
        return vuelos;
    }

    public List<Vuelo> listarVuelosPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha final");
        }
        
        List<Vuelo> vuelos = vueloDAO.listarPorFechas(fechaInicio, fechaFin);
        if (vuelos.isEmpty()) {
            throw new IllegalStateException("No hay vuelos en ese rango de fechas");
        }
        return vuelos;
    }

    public void actualizarVuelo(
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
        
        try {
            validador.validarCompleto(
                ciudadSalida, ciudadLlegada,
                fechaSalida, fechaLlegada,
                horaSalida, horaLlegada,
                clase, avion, pilotos, azafatas);
            
            Vuelo vuelo = validador.getVueloValidado();
            vueloDAO.actualizar(vuelo);
            
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(
                e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public void eliminarVuelo(String ciudadSalida, LocalDate fechaSalida, LocalTime horaSalida) {
        String id = generarIdVuelo(ciudadSalida, fechaSalida, horaSalida);
        vueloDAO.eliminar(id);
    }

    public boolean existeVuelo(String ciudadSalida, LocalDate fechaSalida, LocalTime horaSalida) {
        String id = generarIdVuelo(ciudadSalida, fechaSalida, horaSalida);
        return vueloDAO.existeVuelo(id);
    }
    
    private String generarIdVuelo(String ciudadSalida, LocalDate fechaSalida, LocalTime horaSalida) {
        return ciudadSalida + "_" + fechaSalida.toString() + "_" + horaSalida.toString();
    }
}