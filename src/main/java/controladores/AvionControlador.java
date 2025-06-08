package controladores;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import modelos.Avion;
import modelos.utiles.validaciones.AvionValidacion;
import modelos.dao.implementaciones.AvionDAOimpl;

public class AvionControlador {
    private final AvionDAOimpl avionDAO;
    private final AvionValidacion validador;

    public AvionControlador() {
        this.avionDAO = new AvionDAOimpl();
        this.validador = new AvionValidacion();
    }

    public void crearAvion(
            String nombre,
            int capacidad,
            String modelo,
            int peso,
            String matricula,
            String aerolineaPropietaria) {

        try {

            validador.validarCompleto(nombre, capacidad, modelo, peso, matricula, aerolineaPropietaria);
            // Create new Avion object and set all values
            Avion avion = new Avion();
            avion.setNombre(nombre);
            avion.setCapacidad(capacidad);
            avion.setModelo(modelo);
            avion.setPeso(peso);
            avion.setMatricula(matricula);
            avion.setAerolineaPropietaria(aerolineaPropietaria);

            // If validation passes, save to DAO
            avionDAO.crear(avion);

        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(
                    e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public Avion buscarAvion(String matricula) {
        Avion avion = avionDAO.buscarPorId(matricula);
        if (avion == null) {
            throw new IllegalArgumentException("No existe avión con matrícula: " + matricula);
        }
        return avion;
    }

    public List<Avion> listarAviones() {
        List<Avion> aviones = avionDAO.listarTodos();
        if (aviones.isEmpty()) {
            throw new IllegalStateException("No hay aviones registrados");
        }
        return aviones;
    }

    public List<Avion> listarAvionesPorAerolinea(String aerolinea) {
        List<Avion> aviones = avionDAO.listarPorAerolinea(aerolinea);
        if (aviones.isEmpty()) {
            throw new IllegalStateException("No hay aviones registrados para la aerolínea: " + aerolinea);
        }
        return aviones;
    }

    public void actualizarAvion(
            String nombre,
            int capacidad,
            String modelo,
            int peso,
            String matricula,
            String aerolineaPropietaria) {
        
        try {
            validador.validarCompleto(nombre, capacidad, modelo, peso, matricula, aerolineaPropietaria);
            
            Avion avion = validador.getAvionValidado();
            avionDAO.actualizar(avion);
            
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(
                e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public void eliminarAvion(String matricula) {
        avionDAO.eliminar(matricula);
    }

    public boolean existeAvion(String matricula) {
        return avionDAO.buscarPorId(matricula) != null;
    }
}