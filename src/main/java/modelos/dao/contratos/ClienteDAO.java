package controladores.dao.contratos;

import java.util.List;
import modelos.Cliente;

public interface ClienteDAO {
    public void crear(Cliente cliente);
    public Cliente buscarPorId(String correoElectronico);
    public List<Cliente> listarTodas();
    public void actualizar(Cliente cliente);
    public void eliminar(String correoElectronico);
}
