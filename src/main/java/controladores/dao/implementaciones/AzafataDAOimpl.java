package controladores.dao.implementaciones;

import controladores.dao.contratos.AzafataDAO;
import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.Azafata;
import modelos.ConexionDB;

public class AzafataDAOimpl implements AzafataDAO {
    private final JsonDBTemplate db;
    
    public AzafataDAOimpl() {
        this.db = ConexionDB.getConnection();
        if (!this.db.collectionExists(Azafata.class)) {
            this.db.createCollection(Azafata.class);
        }
    }

    @Override
    public void crear(Azafata azafata) {
        db.insert(azafata);
    }

    @Override
    public Azafata buscarPorId(String correoElectronico) {
        return db.findById(correoElectronico, Azafata.class);
    }

    @Override
    public List<Azafata> listarTodas() {
        return db.findAll(Azafata.class);
    }

    @Override
    public void actualizar(Azafata azafata) {
        db.upsert(azafata);
    }

    @Override
    public void eliminar(String correoElectronico) {
        Azafata azafata = buscarPorId(correoElectronico);
        if (azafata != null) {
            db.remove(azafata, Azafata.class);
        }
    }
}
