package controladores;

import java.util.List;
import modelos.Aerolinea;
import modelos.utiles.validaciones.AerolineaValidacion;
import jakarta.validation.ConstraintViolationException;
import java.util.NoSuchElementException;
import javax.swing.JTable;
import modelos.Administrativo;
import modelos.dao.implementaciones.AerolineaDAOimpl;

public class AerolineaControlador {
    private AerolineaDAOimpl aerolineaDAOimpl = new AerolineaDAOimpl();

    public void crear(Aerolinea aerolinea) throws IllegalArgumentException {
        try {
            new AerolineaValidacion().validarCompleto(
                aerolinea.getNombre(),
                aerolinea.getPais(),
                aerolinea.getCentroOperacionPrincipal(),
                aerolinea.getBases(),
                aerolinea.getSitioOficial(),
                aerolinea.getNombreContacto(),
                aerolinea.getTelefono()
            );

            if (aerolineaDAOimpl.buscarPorId(aerolinea.getNombre()) != null) {
                throw new IllegalArgumentException("Ya existe una aerolínea con el nombre: " + aerolinea.getNombre());
            }

            aerolineaDAOimpl.crear(aerolinea);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public Aerolinea buscarPorId(String id) throws IllegalArgumentException {
        Aerolinea aerolinea = aerolineaDAOimpl.buscarPorId(id);
        if (aerolinea == null) {
            throw new IllegalArgumentException("No se encontró aerolínea con ID: " + id);
        }
        return aerolinea;
    }

    public List<Aerolinea> listarTodas() throws NoSuchElementException {
        List<Aerolinea> aerolineas = aerolineaDAOimpl.listarTodas();
        if (aerolineas.isEmpty()) {
            throw new NoSuchElementException("No hay aerolíneas registradas.");
        }
        return aerolineas;
    }

    public void actualizar(Aerolinea aerolinea) throws IllegalArgumentException {
        try {
            new AerolineaValidacion().validarCompleto(
                aerolinea.getNombre(),
                aerolinea.getPais(),
                aerolinea.getCentroOperacionPrincipal(),
                aerolinea.getBases(),
                aerolinea.getSitioOficial(),
                aerolinea.getNombreContacto(),
                aerolinea.getTelefono()
            );

            if (aerolineaDAOimpl.buscarPorId(aerolinea.getNombre()) == null) {
                throw new IllegalArgumentException("No existe aerolínea con ID: " + aerolinea.getNombre());
            }

            aerolineaDAOimpl.actualizar(aerolinea);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public void eliminar(String id) throws IllegalArgumentException {
        Aerolinea aerolinea = aerolineaDAOimpl.buscarPorId(id);
        if (aerolinea == null) {
            throw new IllegalArgumentException("No existe aerolínea con ID: " + id);
        }
        aerolineaDAOimpl.eliminar(id);
    }
    
    public void mostrarEnTabla(JTable tabla) {
        List<Aerolinea> aerolineas = listarTodas();
            
        int posFila = 0;
        for(Aerolinea aerolinea: aerolineas) {
            tabla.setValueAt(aerolinea.getNombre(), posFila, 0);
            tabla.setValueAt(aerolinea.getPais(), posFila, 1);
            tabla.setValueAt(aerolinea.getTelefono(), posFila, 2);
            tabla.setValueAt(aerolinea.getSitioOficial(), posFila, 3);
            
            posFila++;
        }
        
        tabla.setVisible(true);
    }
}
