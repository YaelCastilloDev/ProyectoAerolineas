package controladores.dao.implementaciones;

import controladores.dao.contratos.AerolineaDAO;
import modelos.ConexionDB;
import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.Aerolinea;

public class AerolineaDAOimpl implements AerolineaDAO {
    private final JsonDBTemplate db;
    
    public AerolineaDAOimpl() {
        this.db = ConexionDB.getConnection();
        if (!this.db.collectionExists(Aerolinea.class)) {
            this.db.createCollection(Aerolinea.class);
        }
    }
    
    @Override
    public void crear(Aerolinea aerolinea) {
        db.insert(aerolinea);
    }

    @Override
    public Aerolinea buscarPorId(String id) {
        return db.findById(id, Aerolinea.class);
    }

    @Override
    public List<Aerolinea> listarTodas() {
        return db.findAll(Aerolinea.class);
    }

    @Override
    public void actualizar(Aerolinea aerolinea) {
        db.upsert(aerolinea);
    }

    @Override
    public void eliminar(String id) {
        Aerolinea aerolinea = db.findById(id, Aerolinea.class);
        if (aerolinea != null) {
            db.remove(aerolinea, Aerolinea.class);
        }
    }
}
