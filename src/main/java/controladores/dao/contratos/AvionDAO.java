package controladores.dao.contratos;

import java.util.List;
import modelos.Avion;

public interface AvionDAO {
    public void crear(Avion avion);
    public Avion buscarPorId(String matricula);
    public List<Avion> listarTodos();
    public void actualizar(Avion avion);
    public void eliminar(String matricula);
}