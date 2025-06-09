package modelos.dao.implementaciones;

import modelos.dao.contratos.VueloDAO;
import modelos.conexion.dbConexion;
import io.jsondb.JsonDBTemplate;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Avion;
import modelos.Azafata;
import modelos.Boleto;
import modelos.Piloto;
import modelos.Vuelo;

public class VueloDAOimpl implements VueloDAO {
    private final JsonDBTemplate db;

    public VueloDAOimpl() {
        this.db = dbConexion.getConnection();
        if (!this.db.collectionExists(Vuelo.class)) {
            this.db.createCollection(Vuelo.class);
        }
    }

    private String generarIdVuelo(Vuelo vuelo) {
        return vuelo.getCiudadSalida() + "_" +
               vuelo.getFechaSalida().toString() + "_" +
               vuelo.getHoraSalida().toString();
    }

    @Override
    public void crear(Vuelo vuelo) {
        String id = generarIdVuelo(vuelo);
        if (db.findById(id, Vuelo.class) != null) {
            throw new IllegalArgumentException("Ya existe un vuelo con estas características");
        }
        vuelo.setId(id); // Set the generated ID on the object
        db.insert(vuelo);
    }

    @Override
    public Vuelo buscarPorId(String id) {
        Vuelo vuelo = db.findById(id, Vuelo.class);
        if (vuelo != null) {
            // Cargar boletos asociados
            List<Boleto> boletos = db.find("vuelo.id = '" + id + "'", Boleto.class);
            vuelo.setBoletos(boletos);
        }
        return vuelo;
    }

    @Override
    public List<Vuelo> listarTodos() {
        return db.findAll(Vuelo.class);
    }

    @Override
    public List<Vuelo> listarPorCiudadSalida(String ciudadSalida) {
        return db.find("ciudadSalida = '" + ciudadSalida + "'", Vuelo.class);
    }

    @Override
    public List<Vuelo> listarPorCiudadLlegada(String ciudadLlegada) {
        return db.find("ciudadLlegada = '" + ciudadLlegada + "'", Vuelo.class);
    }

    @Override
    public List<Vuelo> listarPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return db.findAll(Vuelo.class).stream()
            .filter(v -> !v.getFechaSalida().isBefore(fechaInicio) && 
                        !v.getFechaSalida().isAfter(fechaFin))
            .collect(Collectors.toList());
    }

    @Override
    public void actualizar(Vuelo vuelo) {
        String id = generarIdVuelo(vuelo);
        if (db.findById(id, Vuelo.class) == null) {
            throw new IllegalArgumentException("No existe el vuelo a actualizar");
        }
        db.upsert(vuelo);
    }

    @Override
    public void eliminar(String id) {
        Vuelo vuelo = db.findById(id, Vuelo.class);
        if (vuelo == null) {
            throw new IllegalArgumentException("No existe el vuelo a eliminar");
        }
        db.remove(vuelo, Vuelo.class);
    }

    @Override
    public boolean existeVuelo(String id) {
        return db.findById(id, Vuelo.class) != null;
    }
    
    @Override
    public List<Vuelo> listarPorAvion(Avion avion) {
        return db.find("avion.matricula = '" + avion.getMatricula() + "'", Vuelo.class);
    }

    @Override
    public List<Vuelo> listarPorPiloto(Piloto piloto) {
        return db.findAll(Vuelo.class).stream()
            .filter(v -> v.getPilotos() != null && 
                        v.getPilotos().stream()
                         .anyMatch(p -> p.getCorreoElectronico().equals(piloto.getCorreoElectronico())))
            .collect(Collectors.toList());
    }

    @Override
    public List<Vuelo> listarPorAzafata(Azafata azafata) {
        return db.findAll(Vuelo.class).stream()
            .filter(v -> v.getAzafatas() != null && 
                        v.getAzafatas().stream()
                         .anyMatch(a -> a.getCorreoElectronico().equals(azafata.getCorreoElectronico())))
            .collect(Collectors.toList());
    }
}