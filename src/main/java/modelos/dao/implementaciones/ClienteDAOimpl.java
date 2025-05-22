package modelos.dao.implementaciones;

import controladores.dao.contratos.ClienteDAO;
import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.Cliente;
import modelos.ConexionDB;

public class ClienteDAOimpl implements ClienteDAO {
    private final JsonDBTemplate db;

    public ClienteDAOimpl() {
        this.db = ConexionDB.getConnection();
        if (!this.db.collectionExists(Cliente.class)) {
            this.db.createCollection(Cliente.class);
        }
    }
    
    @Override
    public void crear(Cliente cliente) {
        db.insert(cliente);
    }

    @Override
    public Cliente buscarPorId(String correoElectronico) {
        return db.findById(correoElectronico, Cliente.class);
    }

    @Override
    public List<Cliente> listarTodas() {
        return db.findAll(Cliente.class);
    }

    @Override
    public void actualizar(Cliente cliente) {
        db.upsert(cliente);
    }

    @Override
    public void eliminar(String correoElectronico) {
        Cliente cliente = buscarPorId(correoElectronico);
        if (cliente != null) {
            db.remove(cliente, Cliente.class);
        }
    }
}
