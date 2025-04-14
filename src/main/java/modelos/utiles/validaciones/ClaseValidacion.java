package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import modelos.Clase;

public class ClaseValidacion {
    private final Clase clase;

    public ClaseValidacion() {
        this.clase = new Clase();
    }

    public void validarCompleto(String nombre, int capacidad) {
        clase.setNombre(nombre);
        clase.setCapacidad(capacidad);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Clase>> violations = validator.validate(clase);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Clase getClaseValidada() {
        return this.clase;
    }
}