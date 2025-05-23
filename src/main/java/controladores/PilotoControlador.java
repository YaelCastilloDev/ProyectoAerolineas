package controladores;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import modelos.Piloto;
import modelos.dao.implementaciones.PilotoDAOimpl;
import modelos.utiles.validaciones.PilotoValidacion;

public class PilotoControlador {
    private PilotoDAOimpl pilotoDAOimpl = new PilotoDAOimpl();
    
    public void crear(Piloto piloto) throws IllegalArgumentException {
        try {
            new PilotoValidacion().validarCompleto(
                    piloto.getNombre(),
                    piloto.getDireccion(),
                    piloto.getFechaNacimiento(),
                    piloto.getGenero(),
                    piloto.getSalario(),
                    piloto.getCorreoElectronico(),
                    piloto.getContrasena(),
                    piloto.getAnoInicio(),
                    piloto.getTipoLicencia()
            );
            
            if (pilotoDAOimpl.buscarPorId(piloto.getCorreoElectronico()) != null) {
                throw new IllegalArgumentException("Ya existe un piloto con el correo: " + piloto.getCorreoElectronico());
            }
            pilotoDAOimpl.crear(piloto);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }
    
    public Piloto buscarPorId(String correoElectronico) throws IllegalArgumentException {
        Piloto piloto = pilotoDAOimpl.buscarPorId(correoElectronico);
        if (piloto == null) {
            throw new IllegalArgumentException("No se encontró el piloto con el correo: " + correoElectronico);
        }
        return piloto;
    }
    
    public List<Piloto> listarTodos() throws NoSuchElementException {
        List<Piloto> pilotos = pilotoDAOimpl.listarTodos();
        if (pilotos.isEmpty()) {
            throw new NoSuchElementException("No hay pilotos registrados.");
        }
        return pilotos;
    }
    
    public void actualizar(Piloto piloto) throws IllegalArgumentException {
        try {
            new PilotoValidacion().validarCompleto(
                    piloto.getNombre(),
                    piloto.getDireccion(),
                    piloto.getFechaNacimiento(),
                    piloto.getGenero(),
                    piloto.getSalario(),
                    piloto.getCorreoElectronico(),
                    piloto.getContrasena(),
                    piloto.getAnoInicio(),
                    piloto.getTipoLicencia()
            );
            
            if (pilotoDAOimpl.buscarPorId(piloto.getCorreoElectronico()) == null) {
                throw new IllegalArgumentException("No existe piloto con el correo: " + piloto.getCorreoElectronico());
            }
            pilotoDAOimpl.actualizar(piloto);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }
    
    public void eliminar(String correoElectronico) throws IllegalArgumentException {
        Piloto piloto = pilotoDAOimpl.buscarPorId(correoElectronico);
        if (piloto == null) {
            throw new IllegalArgumentException("No existe el piloto con el correo: " + correoElectronico);
        }
        pilotoDAOimpl.eliminar(correoElectronico);
    }
}
