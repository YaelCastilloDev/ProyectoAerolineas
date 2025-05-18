package controladores;

import controladores.dao.implementaciones.ClienteDAOimpl;
import java.util.List;
import java.util.NoSuchElementException;
import modelos.Cliente;
import modelos.utiles.validaciones.ClienteValidacion;

public class ClienteControlador {
    ClienteDAOimpl clienteDAOimpl = new ClienteDAOimpl();
    
    public void crear(Cliente cliente) throws IllegalArgumentException {
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
    }
    
    public Cliente buscarPorId(String id) throws IllegalArgumentException {
        Cliente cliente = clienteDAOimpl.buscarPorId(id);
        if (cliente == null) {
            throw new IllegalArgumentException("No se encontró el cliente con el ID: " + id);
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
        new ClienteValidacion().validarCompleto(cliente.getNombre(),
                cliente.getNacionalidad(),
                cliente.getFechaNacimiento(),
                cliente.getCorreoElectronico(),
                cliente.getTelefono(),
                cliente.getPasaportes()
        );
        
        if (clienteDAOimpl.buscarPorId(cliente.getCorreoElectronico()) == null) {
            throw new IllegalArgumentException("No existe cliente con ID: " + cliente.getCorreoElectronico());
        }
        clienteDAOimpl.actualizar(cliente);
    }
    
    public void eliminar(String id) throws IllegalArgumentException {
        Cliente cliente = clienteDAOimpl.buscarPorId(id);
        if (cliente == null) {
            throw new IllegalArgumentException("No existe cliente con ID: " + id);
        }
        clienteDAOimpl.eliminar(id);
    }
}
