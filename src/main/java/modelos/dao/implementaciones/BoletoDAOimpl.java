package modelos.dao.implementaciones;

import modelos.dao.contratos.BoletoDAO;
import modelos.conexion.dbConexion;
import io.jsondb.JsonDBTemplate;
import java.util.List;
import modelos.Boleto;
import modelos.Cliente;
import modelos.Vuelo;

public class BoletoDAOimpl implements BoletoDAO {
    private final JsonDBTemplate db;
    
    public BoletoDAOimpl() {
        this.db = dbConexion.getConnection();
        if (!this.db.collectionExists(Boleto.class)) {
            this.db.createCollection(Boleto.class);
        }
    }
    
    @Override
    public void crear(Boleto boleto) {
    /*    if (asientoOcupado(boleto.getVuelo(), boleto.getAsiento())) {
            throw new IllegalArgumentException("El asiento " + boleto.getAsiento() + 
               " ya está ocupado en este vuelo");
        }*/

        // Validar capacidad
/*        if (!boleto.getVuelo().tieneDisponibilidad(boleto.getClase())) {
            throw new IllegalArgumentException("No hay disponibilidad en la clase seleccionada");
        }*/

        db.insert(boleto);

        // Actualizar la relación en el vuelo
        Vuelo vuelo = boleto.getVuelo();
        vuelo.agregarBoleto(boleto);
    }

    @Override
    public Boleto buscarPorId(String id) {
        return db.findById(id, Boleto.class);
    }

    @Override
    public List<Boleto> listarTodos() {
        return db.findAll(Boleto.class);
    }

    @Override
    public List<Boleto> listarPorCliente(Cliente cliente) {
        return db.find("cliente.id = '" + cliente.getId() + "'", Boleto.class);
    }

    @Override
    public List<Boleto> listarPorVuelo(Vuelo vuelo) {
        return db.find("vuelo.id = '" + vuelo.getId() + "'", Boleto.class);
    }

    @Override
    public void actualizar(Boleto boleto) {
        if (db.findById(boleto.getId(), Boleto.class) == null) {
            throw new IllegalArgumentException("No existe el boleto a actualizar");
        }
        db.upsert(boleto);
    }

    @Override
    public void eliminar(String id) {
        Boleto boleto = db.findById(id, Boleto.class);
        if (boleto == null) {
            throw new IllegalArgumentException("No existe el boleto a eliminar");
        }
        db.remove(boleto, Boleto.class);
    }

    @Override
    public boolean existe(String id) {
        return db.findById(id, Boleto.class) != null;
    }

    @Override
    public boolean asientoOcupado(Vuelo vuelo, String asiento) {
        return db.find("vuelo.id = '" + vuelo.getId() + "' and asiento = '" + asiento + "'", Boleto.class)
               .stream()
               .anyMatch(b -> b.getEstado() != Boleto.EstadoBoleto.CANCELADO);
    }
}