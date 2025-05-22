package modelos.dao.contratos;

import java.util.List;
import modelos.Azafata;

public interface AzafataDAO {
    public void crear(Azafata azafata);
    public Azafata buscarPorId(String correoElectronico);
    public List<Azafata> listarTodas();
    public void actualizar(Azafata azafata);
    public void eliminar(String correoElectronico);
}
