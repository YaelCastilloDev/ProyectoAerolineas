package controladores.dao.contratos;

import java.util.List;
import modelos.Aerolinea;

public interface AerolineaDAO {
    public void crear(Aerolinea aerolinea);
    public Aerolinea buscarPorId(String id);
    public List<Aerolinea> listarTodas();
    public void actualizar(Aerolinea aerolinea);
    public void eliminar(String id);
}