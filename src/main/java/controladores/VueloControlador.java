package controladores;

import modelos.*;
import excepciones.*;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import modelos.utiles.validaciones.VueloValidacion;
import modelos.dao.implementaciones.VueloDAOimpl;
import modelos.dao.implementaciones.PilotoDAOimpl;
import modelos.dao.implementaciones.AzafataDAOimpl;
import modelos.dao.implementaciones.AvionDAOimpl;

public class VueloControlador {
    private final VueloDAOimpl vueloDAO;
    private final PilotoDAOimpl pilotoDAO;
    private final AzafataDAOimpl azafataDAO;
    private final AvionDAOimpl avionDAO;
    private final VueloValidacion validador;

    public VueloControlador() {
        this.vueloDAO = new VueloDAOimpl();
        this.pilotoDAO = new PilotoDAOimpl();
        this.azafataDAO = new AzafataDAOimpl();
        this.avionDAO = new AvionDAOimpl();
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
            // Validación básica
            validador.validarCompleto(
                ciudadSalida, ciudadLlegada,
                fechaSalida, fechaLlegada,
                horaSalida, horaLlegada,
                clase, avion, pilotos, azafatas);
            
            // Validaciones adicionales
          //  validarDisponibilidadAvion(avion, fechaSalida, fechaLlegada);
      //      validarDisponibilidadPilotos(pilotos, fechaSalida, fechaLlegada);
       //     validarDisponibilidadAzafatas(azafatas, fechaSalida, fechaLlegada);
     //       validarLicenciasPilotos(pilotos, avion.getModelo());

            Vuelo vuelo = validador.getVueloValidado();
            vueloDAO.crear(vuelo);
            
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(
                e.getConstraintViolations().iterator().next().getMessage());
        } catch (AvionNoDisponibleException | PersonalNoDisponibleException | 
                LicenciaNoValidaException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void validarDisponibilidadAvion(Avion avion, LocalDate inicio, LocalDate fin) {
        List<Vuelo> vuelosDelAvion = vueloDAO.listarPorAvion(avion);
        
        for (Vuelo v : vuelosDelAvion) {
            if (haySuperposicion(v.getFechaSalida(), v.getFechaLlegada(), inicio, fin)) {
                throw new AvionNoDisponibleException(
                    "El avión ya está asignado al vuelo " + v.getId() + 
                    " en ese horario (" + v.getFechaSalida() + " - " + 
                    v.getFechaLlegada() + ")");
            }
        }
    }

    private void validarDisponibilidadPilotos(List<Piloto> pilotos, LocalDate inicio, LocalDate fin) {
        for (Piloto piloto : pilotos) {
            List<Vuelo> vuelosDelPiloto = vueloDAO.listarPorPiloto(piloto);
            
            for (Vuelo v : vuelosDelPiloto) {
                if (haySuperposicion(v.getFechaSalida(), v.getFechaLlegada(), inicio, fin)) {
                    throw new PersonalNoDisponibleException(
                        "El piloto " + piloto.getNombre() + 
                        " ya está asignado al vuelo " + v.getId() + 
                        " en ese horario");
                }
            }
        }
    }

    private void validarDisponibilidadAzafatas(List<Azafata> azafatas, LocalDate inicio, LocalDate fin) {
        for (Azafata azafata : azafatas) {
            List<Vuelo> vuelosDeAzafata = vueloDAO.listarPorAzafata(azafata);
            
            for (Vuelo v : vuelosDeAzafata) {
                if (haySuperposicion(v.getFechaSalida(), v.getFechaLlegada(), inicio, fin)) {
                    throw new PersonalNoDisponibleException(
                        "La azafata " + azafata.getNombre() + 
                        " ya está asignada al vuelo " + v.getId() + 
                        " en ese horario");
                }
            }
        }
    }

    private void validarLicenciasPilotos(List<Piloto> pilotos, String modeloAvion) {
        for (Piloto piloto : pilotos) {
            if (!piloto.getTipoLicencia().contains(modeloAvion)) {
                throw new LicenciaNoValidaException(
                    "El piloto " + piloto.getNombre() + 
                    " no tiene licencia para el modelo " + modeloAvion);
            }
        }
    }

    private boolean haySuperposicion(LocalDate inicio1, LocalDate fin1,
                                   LocalDate inicio2, LocalDate fin2) {
        return !inicio1.isAfter(fin2) && !inicio2.isAfter(fin1);
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