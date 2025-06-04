package controladores;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import modelos.Boleto;
import modelos.Clase;
import modelos.Cliente;
import modelos.Vuelo;
import modelos.utiles.validaciones.BoletoValidacion;
import modelos.dao.implementaciones.BoletoDAOimpl;

public class BoletoControlador {
    private final BoletoDAOimpl boletoDAO;
    private final BoletoValidacion validador;

    public BoletoControlador() {
        this.boletoDAO = new BoletoDAOimpl();
        this.validador = new BoletoValidacion();
    }

    public Boleto crearBoleto(
            Cliente cliente,
            Vuelo vuelo,
            Clase clase,
            double costo,
            String asiento) {
        
        try {
            validador.validarCompleto(cliente, vuelo, clase, costo, asiento);
            Boleto boleto = validador.getBoletoValidado();
            boletoDAO.crear(boleto);
            return boleto;
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException(
                e.getConstraintViolations().iterator().next().getMessage());
        }
    }

    public Boleto buscarBoleto(String id) {
        Boleto boleto = boletoDAO.buscarPorId(id);
        if (boleto == null) {
            throw new IllegalArgumentException("No existe el boleto especificado");
        }
        return boleto;
    }

    public List<Boleto> listarBoletosPorCliente(Cliente cliente) {
        List<Boleto> boletos = boletoDAO.listarPorCliente(cliente);
        if (boletos.isEmpty()) {
            throw new IllegalStateException("El cliente no tiene boletos registrados");
        }
        return boletos;
    }

    public List<Boleto> listarBoletosPorVuelo(Vuelo vuelo) {
        List<Boleto> boletos = boletoDAO.listarPorVuelo(vuelo);
        if (boletos.isEmpty()) {
            throw new IllegalStateException("El vuelo no tiene boletos emitidos");
        }
        return boletos;
    }

    public void pagarBoleto(String id) {
        Boleto boleto = buscarBoleto(id);
        boleto.pagar();
        boletoDAO.actualizar(boleto);
    }

    public void cancelarBoleto(String id) {
        Boleto boleto = buscarBoleto(id);
        boleto.cancelar();
        boletoDAO.actualizar(boleto);
    }

    public void utilizarBoleto(String id) {
        Boleto boleto = buscarBoleto(id);
        boleto.utilizar();
        boletoDAO.actualizar(boleto);
    }

    public boolean asientoDisponible(Vuelo vuelo, String asiento) {
        return !boletoDAO.asientoOcupado(vuelo, asiento);
    }

    public List<String> asientosDisponibles(Vuelo vuelo, Clase clase) {
        // Implementar lógica para obtener asientos disponibles según el mapa del avión
        // Esto podría conectarse con el modelo de Avión para obtener la configuración de asientos
        throw new UnsupportedOperationException("Funcionalidad no implementada aún");
    }
}