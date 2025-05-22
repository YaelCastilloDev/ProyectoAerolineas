package modelos.dao.contratos;

import java.util.List;
import modelos.Piloto;

public interface PilotoDAO {
    public void crear(Piloto piloto);
    public Piloto buscarPorId(String correoElectronico);
    public List<Piloto> listarTodos();
    public void actualizar(Piloto piloto);
    public void eliminar(String correoElectronico);
}
