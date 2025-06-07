package controladores;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JTable;
import modelos.Administrativo;
import modelos.dao.implementaciones.AdministrativoDAOimpl;
import modelos.utiles.validaciones.AdministrativoValidacion;
import vistas.administrativo.VentanaMenuAdmin;

public class AdministrativoControlador {
    private AdministrativoDAOimpl administrativoDAOimpl = new AdministrativoDAOimpl();
    
    public void crear(Administrativo administrativo) throws IllegalArgumentException {
        try {
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
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
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
            throw new NoSuchElementException("No hay administrativos registrados.");
        }
        return administrativos;
    }
    
    public void actualizar(Administrativo administrativo) throws IllegalArgumentException {
        try {
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
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }
    
    public void eliminar(String correoElectronico) throws IllegalArgumentException {
        Administrativo administrativo = administrativoDAOimpl.buscarPorId(correoElectronico);
        if (administrativo == null) {
            throw new IllegalArgumentException("No existe administrativo con el correo: " + correoElectronico);
        }
        administrativoDAOimpl.eliminar(correoElectronico);
    }
    
    public void mostrarEnTabla(JTable tabla) {
        List<Administrativo> admins = listarTodas();
            
        int posFila = 0;
        for(Administrativo admin: admins) {
            tabla.setValueAt(admin.getCorreoElectronico(), posFila, 0);
            tabla.setValueAt(admin.getDeptoTrabajo(), posFila, 1);

            String horario = admin.getHorarioEntrada() + " - " + admin.getHorarioSalida();

            tabla.setValueAt(horario, posFila, 2);
            posFila++;
        }
        
        tabla.setVisible(true);
    }  
    
    public void validarCredenciales(String correo, String contraseña){
        Administrativo admin = administrativoDAOimpl.verificarContraseña(correo, contraseña);
        if(admin != null){
            VentanaMenuAdmin nuevoMenu = new VentanaMenuAdmin();
            nuevoMenu.setVisible(true);
        }
    }
}
