package modelos.dao.implementaciones;

import modelos.dao.contratos.ClaseDAO;
import modelos.conexion.dbConexion;
import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.Clase;

public class ClaseDAOimpl implements ClaseDAO {
    private final JsonDBTemplate db;
    
    public ClaseDAOimpl() {
        this.db = dbConexion.getConnection();
        if (!this.db.collectionExists(Clase.class)) {
            this.db.createCollection(Clase.class);
            inicializarClasesBasicas();
        }
    }
    
    private void inicializarClasesBasicas() {
        // Clases predefinidas del sistema
        Clase economica = new Clase();
        economica.setNombre("Económica");
        economica.setCapacidad(300);
        economica.setPrecioBase(500);
        economica.setMultiplicadorPrecio(1.0);
        economica.setTipo(Clase.TipoClase.ECONOMICA);
        db.insert(economica);
        
        Clase ejecutiva = new Clase();
        ejecutiva.setNombre("Ejecutiva");
        ejecutiva.setCapacidad(100);
        ejecutiva.setPrecioBase(1200);
        ejecutiva.setMultiplicadorPrecio(1.5);
        ejecutiva.setTipo(Clase.TipoClase.EJECUTIVA);
        db.insert(ejecutiva);
        
        Clase primera = new Clase();
        primera.setNombre("Primera Clase");
        primera.setCapacidad(20);
        primera.setPrecioBase(2500);
        primera.setMultiplicadorPrecio(2.5);
        primera.setTipo(Clase.TipoClase.PRIMERA_CLASE);
        db.insert(primera);
    }
    
    @Override
    public void crear(Clase clase) {
        if (db.findById(clase.getNombre(), Clase.class) != null) {
            throw new IllegalArgumentException("Ya existe una clase con ese nombre");
        }
        db.insert(clase);
    }

    @Override
    public Clase buscarPorNombre(String nombre) {
        return db.findById(nombre, Clase.class);
    }

    @Override
    public List<Clase> listarTodos() {
        return db.findAll(Clase.class);
    }

    @Override
    public List<Clase> listarPorTipo(Clase.TipoClase tipo) {
        return db.find("tipo = '" + tipo.name() + "'", Clase.class);
    }

    @Override
    public void actualizar(Clase clase) {
        if (db.findById(clase.getNombre(), Clase.class) == null) {
            throw new IllegalArgumentException("No existe la clase a actualizar");
        }
        db.upsert(clase);
    }

    @Override
    public void eliminar(String nombre) {
        Clase clase = db.findById(nombre, Clase.class);
        if (clase == null) {
            throw new IllegalArgumentException("No existe la clase a eliminar");
        }
        db.remove(clase, Clase.class);
    }

    @Override
    public boolean existe(String nombre) {
        return db.findById(nombre, Clase.class) != null;
    }
}