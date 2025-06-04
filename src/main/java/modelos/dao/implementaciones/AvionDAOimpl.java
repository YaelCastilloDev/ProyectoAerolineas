package modelos.dao.implementaciones;

import modelos.dao.contratos.AvionDAO;
import modelos.conexion.dbConexion;
import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.Avion;

public class AvionDAOimpl implements AvionDAO {
    private final JsonDBTemplate db;
    
    public AvionDAOimpl() {
        this.db = dbConexion.getConnection();
        if (!this.db.collectionExists(Avion.class)) {
            this.db.createCollection(Avion.class);
        }
    }
    
    @Override
    public void crear(Avion avion) {
        if (buscarPorId(avion.getMatricula()) != null) {
            throw new IllegalArgumentException("Avión con matrícula " + avion.getMatricula() + " ya existe");
        }
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
    public List<Avion> listarPorAerolinea(String aerolinea) {
        return db.find("aerolineaPropietaria = '" + aerolinea + "'", Avion.class);
    }

    @Override
    public void actualizar(Avion avion) {
        if (buscarPorId(avion.getMatricula()) == null) {
            throw new IllegalArgumentException("Avión con matrícula " + avion.getMatricula() + " no existe");
        }
        db.upsert(avion);
    }

    @Override
    public void eliminar(String matricula) {
        Avion avion = buscarPorId(matricula);
        if (avion == null) {
            throw new IllegalArgumentException("Avión con matrícula " + matricula + " no existe");
        }
        db.remove(avion, Avion.class);
    }
}