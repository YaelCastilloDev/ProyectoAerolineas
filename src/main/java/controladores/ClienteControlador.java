package controladores;

import jakarta.validation.ConstraintViolationException;
import modelos.dao.implementaciones.ClienteDAOimpl;
import java.util.List;
import java.util.NoSuchElementException;
import modelos.Cliente;
import modelos.utiles.validaciones.ClienteValidacion;

public class ClienteControlador {
    private ClienteDAOimpl clienteDAOimpl = new ClienteDAOimpl();
    
    public void crear(Cliente cliente) throws IllegalArgumentException {
        try {
            new ClienteValidacion().validarCompleto(
                    cliente.getNombre(),
                    cliente.getNacionalidad(),
                    cliente.getFechaNacimiento(),
                    cliente.getCorreoElectronico(),
                    cliente.getTelefono(),
                    cliente.getPasaportes()
            );

            if (clienteDAOimpl.buscarPorId(cliente.getCorreoElectronico()) != null) {
                throw new IllegalArgumentException("Ya existe un cliente con el correo: " + cliente.getCorreoElectronico());
            }
            clienteDAOimpl.crear(cliente);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }
    
    public Cliente buscarPorId(String correoElectronico) throws IllegalArgumentException {
        Cliente cliente = clienteDAOimpl.buscarPorId(correoElectronico);
        if (cliente == null) {
            throw new IllegalArgumentException("No se encontró el cliente con el correo: " + correoElectronico);
        }
        return cliente;
    }
    
    public List<Cliente> listarTodas() throws NoSuchElementException {
        List<Cliente> clientes = clienteDAOimpl.listarTodas();
        if (clientes.isEmpty()) {
            throw new NoSuchElementException("No hay clientes registrados.");
        }
        return clientes;
    }
    
    public void actualizar(Cliente cliente) throws IllegalArgumentException {
        try {
            new ClienteValidacion().validarCompleto(cliente.getNombre(),
                    cliente.getNacionalidad(),
                    cliente.getFechaNacimiento(),
                    cliente.getCorreoElectronico(),
                    cliente.getTelefono(),
                    cliente.getPasaportes()
            );

            if (clienteDAOimpl.buscarPorId(cliente.getCorreoElectronico()) == null) {
                throw new IllegalArgumentException("No existe cliente con el correo: " + cliente.getCorreoElectronico());
            }
            clienteDAOimpl.actualizar(cliente);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(e.getConstraintViolations().iterator().next().getMessage());
        }
    }
    
    public void eliminar(String correoElectronico) throws IllegalArgumentException {
        Cliente cliente = clienteDAOimpl.buscarPorId(correoElectronico);
        if (cliente == null) {
            throw new IllegalArgumentException("No existe cliente con el correo: " + correoElectronico);
        }
        clienteDAOimpl.eliminar(correoElectronico);
    }
}
