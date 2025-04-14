package modelos.utiles.validaciones;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.Set;
import modelos.Avion;

public class AvionValidacion {
    private final Avion avion;

    public AvionValidacion() {
        this.avion = new Avion();
    }

    public void validarCompleto(
            String nombre,
            int capacidad,
            String modelo,
            int peso,
            String matricula,
            LocalDate fechaFabricacion) {
        
        avion.setNombre(nombre);
        avion.setCapacidad(capacidad);
        avion.setModelo(modelo);
        avion.setPeso(peso);
        avion.setMatricula(matricula);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Avion>> violations = validator.validate(avion);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public Avion getAvionValidado() {
        return this.avion;
    }
}