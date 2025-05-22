package modelos.dao.contratos;

import java.util.List;
import modelos.Administrativo;

public interface AdministrativoDAO {
    public void crear(Administrativo administrativo);
    public Administrativo buscarPorId(String correoElectronico);
    public List<Administrativo> listarTodas();
    public void actualizar(Administrativo administrativo);
    public void eliminar(String correoElectronico);
}
