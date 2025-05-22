package controladores;

import controladores.dao.implementaciones.AzafataDAOimpl;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import modelos.Azafata;
import modelos.utiles.validaciones.AzafataValidacion;

public class AzafataControlador {
    private AzafataDAOimpl azafataDAOimpl = new AzafataDAOimpl();
    
    public void crear(Azafata azafata) throws IllegalArgumentException {
        try {
            new AzafataValidacion().validarCompleto(
                    azafata.getNombre(),
                    azafata.getDireccion(),
                    azafata.getFechaNacimiento(),
                    azafata.getGenero(),
                    azafata.getSalario(),
                    azafata.getCorreoElectronico(),
                    azafata.getContrasena(),
                    azafata.getNumIdiomas(),
                    azafata.getAnoInicio()
            );

            if (azafataDAOimpl.buscarPorId(azafata.getCorreoElectronico()) != null) {
                throw new IllegalArgumentException("Ya existe una azafata con el correo: " + azafata.getCorreoElectronico());
            }
            azafataDAOimpl.crear(azafata);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }
    
    public Azafata buscarPorId(String correoElectronico) throws IllegalArgumentException {
        Azafata azafata = azafataDAOimpl.buscarPorId(correoElectronico);
        if (azafata == null) {
            throw new IllegalArgumentException("No se encontró la azafata con el correo: " + correoElectronico);
        }
        return azafata;
    }
    
    public List<Azafata> listarTodas() throws NoSuchElementException {
        List<Azafata> azafatas = azafataDAOimpl.listarTodas();
        if (azafatas.isEmpty()) {
            throw new NoSuchElementException("No hay azafatas registradas.");
        }
        return azafatas;
    }
    
    public void actualizar(Azafata azafata) throws IllegalArgumentException {
        try {
            new AzafataValidacion().validarCompleto(
                    azafata.getNombre(),
                    azafata.getDireccion(),
                    azafata.getFechaNacimiento(),
                    azafata.getGenero(),
                    azafata.getSalario(),
                    azafata.getCorreoElectronico(),
                    azafata.getContrasena(),
                    azafata.getNumIdiomas(),
                    azafata.getAnoInicio()
            );

            if (azafataDAOimpl.buscarPorId(azafata.getCorreoElectronico()) == null) {
                throw new IllegalArgumentException("No existe azafata con el correo: " + azafata.getCorreoElectronico());
            }
            azafataDAOimpl.actualizar(azafata);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }
    
    public void eliminar(String correoElectronico) throws IllegalArgumentException {
        Azafata azafata = azafataDAOimpl.buscarPorId(correoElectronico);
        if (azafata == null) {
            throw new IllegalArgumentException("No existe azafata con el correo: " + correoElectronico);
        }
        azafataDAOimpl.eliminar(correoElectronico);
    }
}
