package modelos.dao.contratos;

import java.util.List;
import modelos.Boleto;
import modelos.Cliente;
import modelos.Vuelo;

public interface BoletoDAO {
    void crear(Boleto boleto);
    Boleto buscarPorId(String id);
    List<Boleto> listarTodos();
    List<Boleto> listarPorCliente(Cliente cliente);
    List<Boleto> listarPorVuelo(Vuelo vuelo);
    void actualizar(Boleto boleto);
    void eliminar(String id);
    boolean existe(String id);
    boolean asientoOcupado(Vuelo vuelo, String asiento);
}