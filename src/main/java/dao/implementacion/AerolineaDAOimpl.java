package dao.implementacion;

import dao.contrato.AerolineaDAO;
import db.dbConeccion;
import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.Aerolinea;

public class AerolineaDAOimpl implements AerolineaDAO {
    private final JsonDBTemplate db;
    
    public AerolineaDAOimpl() {
        this.db = dbConeccion.getConnection();
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
        else {
            System.out.println("aqui va una excepcion (o en controlador)");
        }
    }
}
