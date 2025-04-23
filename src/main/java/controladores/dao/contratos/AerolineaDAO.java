package controladores.dao.contratos;

import java.util.List;
import modelos.Aerolinea;

public interface AerolineaDAO {
    void crear(Aerolinea aerolinea);
    Aerolinea buscarPorId(String id);
    List<Aerolinea> listarTodas();
    void actualizar(Aerolinea aerolinea);
    void eliminar(String id);
}