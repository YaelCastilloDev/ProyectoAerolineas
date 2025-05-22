package modelos.dao.implementaciones;

import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.Administrativo;
import modelos.ConexionDB;
import modelos.dao.contratos.AdministrativoDAO;

public class AdministrativoDAOimpl implements AdministrativoDAO {
    private final JsonDBTemplate db;
    
    public AdministrativoDAOimpl() {
        this.db = ConexionDB.getConnection();
        if (!this.db.collectionExists(Administrativo.class)) {
            this.db.createCollection(Administrativo.class);
        }
    }

    @Override
    public void crear(Administrativo administrativo) {
        db.insert(administrativo);
    }

    @Override
    public Administrativo buscarPorId(String correoElectronico) {
        return db.findById(correoElectronico, Administrativo.class);
    }

    @Override
    public List<Administrativo> listarTodas() {
        return db.findAll(Administrativo.class);
    }

    @Override
    public void actualizar(Administrativo administrativo) {
        db.upsert(administrativo);
    }

    @Override
    public void eliminar(String correoElectronico) {
        Administrativo administrativo = buscarPorId(correoElectronico);
        if (administrativo != null) {
            db.remove(administrativo, Administrativo.class);
        }
    }
}
