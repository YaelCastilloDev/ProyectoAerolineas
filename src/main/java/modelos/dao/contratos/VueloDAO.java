package modelos.dao.contratos;

import java.time.LocalDate;
import java.util.List;
import modelos.Vuelo;

public interface VueloDAO {
    void crear(Vuelo vuelo);
    Vuelo buscarPorId(String id); // Considerando ciudadSalida+fechaSalida+horaSalida como ID único
    List<Vuelo> listarTodos();
    List<Vuelo> listarPorCiudadSalida(String ciudadSalida);
    List<Vuelo> listarPorCiudadLlegada(String ciudadLlegada);
    List<Vuelo> listarPorFechas(LocalDate fechaInicio, LocalDate fechaFin);
    void actualizar(Vuelo vuelo);
    void eliminar(String id);
    boolean existeVuelo(String id);
}