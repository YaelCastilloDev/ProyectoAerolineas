package controladores;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import modelos.Clase;
import modelos.utiles.validaciones.ClaseValidacion;
import modelos.dao.implementaciones.ClaseDAOimpl;

public class ClaseControlador {
    private final ClaseDAOimpl claseDAO;
    private final ClaseValidacion validador;

    public ClaseControlador() {
        this.claseDAO = new ClaseDAOimpl();
        this.validador = new ClaseValidacion();
    }

    public void crearClase(String nombre, int capacidad, double precioBase, 
                         double multiplicador, Clase.TipoClase tipo) {
        try {
            validador.validarCompleto(nombre, capacidad, precioBase, multiplicador, tipo);
            Clase clase = validador.getClaseValidada();
            claseDAO.crear(clase);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(
                e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public Clase buscarClase(String nombre) {
        Clase clase = claseDAO.buscarPorNombre(nombre);
        if (clase == null) {
            throw new IllegalArgumentException("No existe la clase especificada");
        }
        return clase;
    }

    public List<Clase> listarTodasClases() {
        List<Clase> clases = claseDAO.listarTodos();
        if (clases.isEmpty()) {
            throw new IllegalStateException("No hay clases registradas");
        }
        return clases;
    }

    public List<Clase> listarClasesPorTipo(Clase.TipoClase tipo) {
        List<Clase> clases = claseDAO.listarPorTipo(tipo);
        if (clases.isEmpty()) {
            throw new IllegalStateException("No hay clases del tipo " + tipo);
        }
        return clases;
    }

    public void actualizarClase(String nombre, int capacidad, double precioBase, 
                              double multiplicador, Clase.TipoClase tipo) {
        try {
            validador.validarCompleto(nombre, capacidad, precioBase, multiplicador, tipo);
            Clase clase = validador.getClaseValidada();
            claseDAO.actualizar(clase);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(
                e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public void eliminarClase(String nombre) {
        claseDAO.eliminar(nombre);
    }

    public boolean existeClase(String nombre) {
        return claseDAO.existe(nombre);
    }

    public double calcularPrecioClase(String nombre) {
        Clase clase = buscarClase(nombre);
        return clase.calcularPrecio();
    }
}