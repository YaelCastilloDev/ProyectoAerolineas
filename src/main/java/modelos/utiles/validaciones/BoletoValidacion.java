package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import modelos.Avion;
import modelos.Boleto;
import modelos.Clase;
import modelos.Cliente;
import modelos.Vuelo;

public class BoletoValidacion {
    private final Boleto boleto;
    private final Validator validator;

    public BoletoValidacion() {
        this.boleto = new Boleto();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void validarCompleto(
            Cliente cliente,
            Vuelo vuelo,
            Clase clase,
            double costo,
            String asiento) {
        
        boleto.setCliente(cliente);
        boleto.setVuelo(vuelo);
        boleto.setClase(clase);
        boleto.setCosto(costo);
        boleto.setAsiento(asiento);

        Set<ConstraintViolation<Boleto>> violations = validator.validate(boleto);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        
        // Validaciones adicionales
        validarCapacidadVuelo(vuelo, clase);
        validarPrecioClase(clase, costo);
    }

    private void validarCapacidadVuelo(Vuelo vuelo, Clase clase) {
        if (!vuelo.tieneDisponibilidad(clase)) {
            throw new IllegalArgumentException(
                "No hay disponibilidad en clase " + clase.getNombre() + 
                ". Capacidad: " + clase.getCapacidad() +
                ". Boletos vendidos: " + vuelo.getBoletosActivos().stream()
                    .filter(b -> b.getClase().equals(clase))
                    .count());
        }
    }

    private void validarPrecioClase(Clase clase, double costo) {
        double precioEsperado = clase.calcularPrecio();
        double margenError = precioEsperado * 0.1; // 10% de margen
        
        if (Math.abs(costo - precioEsperado) > margenError) {
            throw new IllegalArgumentException(
                "El costo $" + costo + " no coincide con el precio esperado $" + 
                precioEsperado + " para clase " + clase.getNombre());
        }
    }
    
    public List<String> asientosDisponibles(Vuelo vuelo, Clase clase) {
    // Obtener todos los asientos posibles para esta clase
    List<String> todosAsientos = obtenerAsientosPosibles(vuelo.getAvion(), clase);
    
    // Obtener asientos ocupados
    Set<String> asientosOcupados = vuelo.getBoletosActivos().stream()
        .filter(b -> b.getClase().equals(clase))
        .map(Boleto::getAsiento)
        .collect(Collectors.toSet());
    
    // Filtrar disponibles
    return todosAsientos.stream()
        .filter(asiento -> !asientosOcupados.contains(asiento))
        .collect(Collectors.toList());
}

    private List<String> obtenerAsientosPosibles(Avion avion, Clase clase) {
        // Lógica para generar asientos según configuración del avión y clase
        List<String> asientos = new ArrayList<>();

        // Usar clase.getTipo() en lugar de clase directamente
        char filaInicio = clase.getTipo() == Clase.TipoClase.PRIMERA_CLASE ? 'A' : 
                          clase.getTipo() == Clase.TipoClase.EJECUTIVA ? 'D' : 'G';

        int limiteFilas = clase.getTipo() == Clase.TipoClase.PRIMERA_CLASE ? 3 : 
                          clase.getTipo() == Clase.TipoClase.EJECUTIVA ? 6 : 20;

        for (char fila = filaInicio; fila < filaInicio + limiteFilas; fila++) {
            for (int numero = 1; numero <= 6; numero++) { // 6 asientos por fila
                asientos.add(fila + String.valueOf(numero));
            }
        }
        return asientos;
    }
    
    public boolean asientoOcupado(Vuelo vuelo, String asiento) {
        // Verificar que el asiento sea válido para la clase
        if (!esAsientoValido(vuelo, asiento)) {
            throw new IllegalArgumentException("Asiento no válido para esta clase");
        }

        return vuelo.getBoletosActivos().stream()
            .anyMatch(b -> b.getAsiento().equalsIgnoreCase(asiento));
    }

    private boolean esAsientoValido(Vuelo vuelo, String asiento) {
        // Implementar lógica para validar formato y ubicación del asiento
        // según la clase del boleto y configuración del avión
        return asiento.matches("^[A-Z][1-9][0-9]?$"); // Validación básica
    }

    public Boleto getBoletoValidado() {
        return this.boleto;
    }
}