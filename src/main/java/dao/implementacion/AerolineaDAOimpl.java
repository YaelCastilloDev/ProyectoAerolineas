package dao.implementacion;

import dao.contrato.AerolineaDAO;
import db.dbConeccion;
import io.jsondb.JsonDBTemplate;
import modelos.Aerolinea;
import java.util.List;

public class AerolineaDAOimpl implements AerolineaDAO {
    private final JsonDBTemplate db;
    
    public AerolineaDAOimpl() {
        this.db = dbConeccion.getConnection();
        // Crear la colección si no existe
        if (!db.collectionExists(Aerolinea.class)) {
            db.createCollection(Aerolinea.class);
        }
    }
    
    @Override
    public void crear(Aerolinea aerolinea) {
        try {
            // Verificar si ya existe una aerolínea con ese nombre
            if (db.findById(aerolinea.getNombre(), Aerolinea.class) != null) {
                throw new IllegalArgumentException("Ya existe una aerolínea con ese nombre");
            }
            
            // Insertar la nueva aerolínea
            db.insert(aerolinea);
            System.out.println("Aerolínea creada exitosamente: " + aerolinea.getNombre());
        } catch (Exception e) {
            System.err.println("Error al crear aerolínea: " + e.getMessage());
            throw e;
        }
    }
    
    
    public List<Aerolinea> listarTodos() {
        return db.findAll(Aerolinea.class);
    }
    
    
    public Aerolinea buscarPorNombre(String nombre) {
        return db.findById(nombre, Aerolinea.class);
    }
    
    
    public void actualizar(Aerolinea aerolinea) {
        db.save(aerolinea, Aerolinea.class);
    }
    
    public void eliminar(String nombre) {
        Aerolinea a = db.findById(nombre, Aerolinea.class);
        if (a != null) {
            db.remove(a, Aerolinea.class);
        }
    }
}