package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import modelos.Clase;

public class ClaseValidacion {
    private final Clase clase;
    private final Validator validator;

    public ClaseValidacion() {
        this.clase = new Clase();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void validarCompleto(String nombre, int capacidad, double precioBase, 
                              double multiplicadorPrecio, Clase.TipoClase tipo) {
        clase.setNombre(nombre);
        clase.setCapacidad(capacidad);
        clase.setPrecioBase(precioBase);
        clase.setMultiplicadorPrecio(multiplicadorPrecio);
        clase.setTipo(tipo);

        Set<ConstraintViolation<Clase>> violations = validator.validate(clase);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        
        // Validación adicional: precio coherente con el tipo de clase
        validarPrecioTipoClase(precioBase, multiplicadorPrecio, tipo);
    }

    private void validarPrecioTipoClase(double precioBase, double multiplicador, Clase.TipoClase tipo) {
        switch(tipo) {
            case ECONOMICA:
                if (precioBase > 800 || multiplicador > 1.2) {
                    throw new IllegalArgumentException(
                        "Precio no coherente con clase económica. Máx: $800 y 1.2x");
                }
                break;
            case EJECUTIVA:
                if (precioBase < 800 || precioBase > 2000 || multiplicador < 1.2 || multiplicador > 2.0) {
                    throw new IllegalArgumentException(
                        "Precio no coherente con clase ejecutiva. Rango: $800-2000 y 1.2-2.0x");
                }
                break;
            case PRIMERA_CLASE:
                if (precioBase < 2000 || multiplicador < 2.0) {
                    throw new IllegalArgumentException(
                        "Precio no coherente con primera clase. Mín: $2000 y 2.0x");
                }
                break;
        }
    }

    public Clase getClaseValidada() {
        return this.clase;
    }
}