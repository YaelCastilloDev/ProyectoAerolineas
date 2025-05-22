package modelos.dao.implementaciones;

import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.ConexionDB;
import modelos.Piloto;
import modelos.dao.contratos.PilotoDAO;

public class PilotoDAOimpl implements PilotoDAO {
    private final JsonDBTemplate db;
    
    public PilotoDAOimpl() {
        this.db = ConexionDB.getConnection();
        if (!this.db.collectionExists(Piloto.class)) {
            this.db.createCollection(Piloto.class);
        }
    }

    @Override
    public void crear(Piloto piloto) {
        db.insert(piloto);
    }

    @Override
    public Piloto buscarPorId(String correoElectronico) {
        return db.findById(correoElectronico, Piloto.class);
    }

    @Override
    public List<Piloto> listarTodos() {
        return db.findAll(Piloto.class);
    }

    @Override
    public void actualizar(Piloto piloto) {
        db.upsert(piloto);
    }

    @Override
    public void eliminar(String correoElectronico) {
        Piloto piloto = buscarPorId(correoElectronico);
        if (piloto != null) {
            db.remove(piloto, Piloto.class);
        }
    }
}
