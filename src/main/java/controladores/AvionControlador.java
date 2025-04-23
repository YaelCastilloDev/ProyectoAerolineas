package controladores;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import modelos.Avion;
import modelos.utiles.validaciones.AvionValidacion;
import controladores.dao.implementaciones.AvionDAOimpl;

public class AvionControlador {
    AvionDAOimpl avionDAOimpl = new AvionDAOimpl();

    public void crear(Avion avion) throws IllegalArgumentException {
        try {
            
            new AvionValidacion().validarCompleto(
                avion.getNombre(),
                avion.getCapacidad(),
                avion.getModelo(),
                avion.getPeso(),
                avion.getMatricula(),
                avion.getAerolineaPropietaria()
            );
            
            if (avionDAOimpl.buscarPorId(avion.getMatricula()) != null) {
                throw new IllegalArgumentException("Ya existe un avión con la matrícula: " + avion.getMatricula());
            }

            avionDAOimpl.crear(avion);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public Avion buscarPorId(String matricula) throws IllegalArgumentException {
        Avion avion = avionDAOimpl.buscarPorId(matricula);
        if (avion == null) {
            throw new IllegalArgumentException("No se encontró avión con matrícula: " + matricula);
        }
        return avion;
    }

    public List<Avion> listarTodos() {
        List<Avion> aviones = avionDAOimpl.listarTodos();
        if (aviones.isEmpty()) {
            System.out.println("No hay aviones registrados");
        }
        return aviones;
    }

    public void actualizar(Avion avion) throws IllegalArgumentException {
        try {
            new AvionValidacion().validarCompleto(
                avion.getNombre(),
                avion.getCapacidad(),
                avion.getModelo(),
                avion.getPeso(),
                avion.getMatricula(),
                avion.getAerolineaPropietaria()
            );

            if (avionDAOimpl.buscarPorId(avion.getMatricula()) == null) {
                throw new IllegalArgumentException("No existe avión con matrícula: " + avion.getMatricula());
            }

            avionDAOimpl.actualizar(avion);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public void eliminar(String matricula) throws IllegalArgumentException {
        Avion avion = avionDAOimpl.buscarPorId(matricula);
        if (avion == null) {
            throw new IllegalArgumentException("No existe avión con matrícula: " + matricula);
        }

        avionDAOimpl.eliminar(matricula);
    }
}