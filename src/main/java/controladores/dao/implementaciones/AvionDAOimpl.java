package controladores.dao.implementaciones;

import controladores.dao.contratos.AvionDAO;
import modelos.ConexionDB;
import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.Avion;

public class AvionDAOimpl implements AvionDAO {
    private final JsonDBTemplate db;
    
    public AvionDAOimpl() {
        this.db = ConexionDB.getConnection();
        if (!this.db.collectionExists(Avion.class)) {
            this.db.createCollection(Avion.class);
        }
    }
    
    @Override
    public void crear(Avion avion) {
        db.insert(avion);
    }

    @Override
    public Avion buscarPorId(String matricula) {
        return db.findById(matricula, Avion.class);
    }

    @Override
    public List<Avion> listarTodos() {
        return db.findAll(Avion.class);
    }

    @Override
    public void actualizar(Avion avion) {
        db.upsert(avion);
    }

    @Override
    public void eliminar(String matricula) {
        Avion avion = db.findById(matricula, Avion.class);
        if (avion != null) {
            db.remove(avion, Avion.class);
        }
    }
}