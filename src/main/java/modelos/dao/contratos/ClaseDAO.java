package modelos.dao.contratos;

import java.util.List;
import modelos.Clase;

public interface ClaseDAO {
    void crear(Clase clase);
    Clase buscarPorNombre(String nombre);
    List<Clase> listarTodos();
    List<Clase> listarPorTipo(Clase.TipoClase tipo);
    void actualizar(Clase clase);
    void eliminar(String nombre);
    boolean existe(String nombre);
}