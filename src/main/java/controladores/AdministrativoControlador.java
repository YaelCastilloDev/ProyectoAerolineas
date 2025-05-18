package controladores;

import controladores.dao.implementaciones.AdministrativoDAOimpl;
import java.util.List;
import java.util.NoSuchElementException;
import modelos.Administrativo;
import modelos.utiles.validaciones.AdministrativoValidacion;

public class AdministrativoControlador {
    private AdministrativoDAOimpl administrativoDAOimpl = new AdministrativoDAOimpl();
    
    public void crear(Administrativo administrativo) throws IllegalArgumentException {
        new AdministrativoValidacion().validarCompleto(
                administrativo.getDeptoTrabajo(), 
                administrativo.getHorarioEntrada(), 
                administrativo.getHorarioSalida(), 
                administrativo.getPuesto(), 
                administrativo.getTipoContrato(), 
                administrativo.getAnosExperiencia(), 
                administrativo.getCorreoElectronico(), 
                administrativo.getContrasena()
        );
        
        if (administrativoDAOimpl.buscarPorId(administrativo.getCorreoElectronico()) != null) {
            throw new IllegalArgumentException("Ya existe un administrativo con el correo: " + administrativo.getCorreoElectronico());
        }
        administrativoDAOimpl.crear(administrativo);
    }
    
    public Administrativo buscarPorId(String correoElectronico) throws IllegalArgumentException {
        Administrativo administrativo = administrativoDAOimpl.buscarPorId(correoElectronico);
        if (administrativo == null) {
            throw new IllegalArgumentException("No se encontró el administrativo con el correo: " + correoElectronico);
        }
        return administrativo;
    }
    
    public List<Administrativo> listarTodas() throws NoSuchElementException {
        List<Administrativo> administrativos = administrativoDAOimpl.listarTodas();
        if (administrativos.isEmpty()) {
            throw new NoSuchElementException("No hay clientes registrados.");
        }
        return administrativos;
    }
    
    public void actualizar(Administrativo administrativo) throws IllegalArgumentException {
        new AdministrativoValidacion().validarCompleto(
                administrativo.getDeptoTrabajo(), 
                administrativo.getHorarioEntrada(), 
                administrativo.getHorarioSalida(), 
                administrativo.getPuesto(), 
                administrativo.getTipoContrato(), 
                administrativo.getAnosExperiencia(), 
                administrativo.getCorreoElectronico(), 
                administrativo.getContrasena()
        );
        
        if (administrativoDAOimpl.buscarPorId(administrativo.getCorreoElectronico()) == null) {
            throw new IllegalArgumentException("No existe administrativo con el correo: " + administrativo.getCorreoElectronico());
        }
        administrativoDAOimpl.actualizar(administrativo);
    }
    
    public void eliminar(String correoElectronico) throws IllegalArgumentException {
        Administrativo administrativo = administrativoDAOimpl.buscarPorId(correoElectronico);
        if (administrativo == null) {
            throw new IllegalArgumentException("No existe cliente con el correo: " + correoElectronico);
        }
        administrativoDAOimpl.eliminar(correoElectronico);
    }
}
